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
        p["id"] = paper.id
        p["name"] = paper.name
        p["description"] = paper.description
        if len(paper.picture.name) <> 0 :        
            p["picture.url"] = paper.picture.url
        else:
            p["picture.url"] = ""
        result.append(p)
    return result;


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
        result = packResult(-1, "phone or password is missed")
        return HttpResponse(result)
    
    user = User.objects.filter(phone=phone);
    if user :
        if user[0].password == password:                        
            request.session["user"] = user[0]
            request.session.save()
            data = {"token" : request.session.session_key};
            result = packResult(0, "login successfully" , data)
        else :
            result = packResult(-1, "password is not correct")
    else:
        result = packResult(-1, "the user do not exist")
    return HttpResponse(result)    
 

def getCurrentUser(request):  
    user = request.session.get('user', False)
    if user :            
        result = packResult(data={"id":user.id, "phone":user.phone, "token":request.session.session_key})
    else:
        result = packResult(-1, "user not logined");   
    return HttpResponse(result)
  
    
def userRegister(request):
    try:
        phone = request.POST["phone"]
        password = request.POST["password"]        
    except:
        result = packResult(-1, "phone or password is missed")
        return HttpResponse(result)
    
    user = User.objects.filter(phone=phone);
    if user :
        result = packResult(-1, "the user already exist")
    else :
        user = User(phone=phone, password=password, state="A")
        user.save()
        request.session["user"] = user
        request.session.save()
        data = {"token" : request.session.session_key};
        result = packResult(0, "register successfully" , data)
    return HttpResponse(result)


def getUserPaper(request):    
    try :
        finishState = request.POST["FinishState"]
    except :
        result = packResult(-1, "FinishState is missed")
        return HttpResponse(result)
    if isLogined(request) :  
        user = request.session.get('user', False)
        papers = Paper.objects.filter(userpaper__user__id=user.id, userpaper__finish_state=finishState) 
        result = packResult(data=papers2Dict(papers))
    else :
        result = packResult(-1, "user not logined");
    return HttpResponse(result)


