# -*- coding: utf-8 -*-
'''
Created on 2013-6-3

@author: 管理员
'''
from django.http import HttpResponse
from django.core import serializers
from models import *
import json
from django.contrib.sessions.backends.db import SessionStore
from django.contrib.sessions.models import Session

def papers2Dict(papers):
    result = []
    for paper in papers:
        p = {}
        p["name"] = paper.name
        p["description"] = paper.description
        if len(paper.picture.name) <> 0 :        
            p["picture.url"] = paper.picture.url
        else:
            p["picture.url"] = ""
        result.append(p)
    return result;

def test1(request):    
    papers = Paper.objects.filter(catalogpaper__catalog__code="headlines") 
    return HttpResponse(papers2Dict(papers))

def test2(request):    
    user = request.session.get("user", False)
    if user :
        return HttpResponse(user)
    else:
        request.session["user"] = "anonymous";
        return HttpResponse("user is not exists")

def test3(request):    
    papers = Paper.objects.filter(catalogpaper__catalog__code="headlines") 
    return HttpResponse(serializers.serialize("json", papers))
    # return HttpResponse("hello")


def isLogined (request):
    user = request.session.get('user', False)
    if user: 
        return True
    else:
        return False

def packResult(errcode=0, errmsg="", data={}):
    result = {}
    result["errcode"] = errcode
    result["errmsg"] = errmsg
    result["data"] = data 
    return json.dumps(result)


def getCatalogPaper(request):
    if isLogined(request) :  
        catalogCode = request.POST["CatalogCode"]
        papers = Paper.objects.filter(catalogpaper__catalog__code=catalogCode) 
        result = packResult(data=papers2Dict(papers))
    else :
        result = packResult(-1, "user not logined");
    return HttpResponse(result)
    
def userLogin(request):
    try:
        phone = request.POST["phone"]
        password = request.POST["password"]        
    except:
        result = packResult(-1, "Phone Or Password Is Missed")
        return HttpResponse(result)
    
    user = User.objects.filter(phone=phone);
    if user :
        if user[0].password == password:                        
            request.session["user"] = user[0]
            request.session.save()
            data = {"token" : request.session.session_key};
            result = packResult(0, "Login Successfully" , data)
        else :
            result = packResult(-1, "Password Is Not Correct")
    else:
        result = packResult(-1, "The User Do Not Exist")
    return HttpResponse(result)    
 

def getCurrentUser(request):  
    user = request.session.get('user', False)
    if user :            
        result = packResult(data={"id":user.id, "phone":user.phone, "token":request.session.session_key})
    else:
        result = packResult(-1, "user not logined");   
    return HttpResponse(result)
  
    
def userRegister(request):
    result = {}
    phone = request.POST["phone"]
    password = request.POST["password"]
    

 


