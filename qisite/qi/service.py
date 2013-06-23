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

def papers2Json(papers):
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
    return json.dumps(result);

def test1(request):    
    papers = Paper.objects.filter(catalogpaper__catalog__code="headlines") 
    return HttpResponse(papers2Json(papers))

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


def getCatalogPaper(request):
    catalogCode = request.POST["CatalogCode"]
    papers = Paper.objects.filter(catalogpaper__catalog__code=catalogCode) 
    return HttpResponse(papers2Json(papers))
    
    
def userLogin(request):
    result = {}
    try:
        phone = request.POST["phone"]
        password = request.POST["password"]        
    except:
        result["errcode"] = -1;
        result["errmsg"] = "没有提供用户名或密码";        
        return HttpResponse(json.dumps(result))
    
    user = User.objects.filter(phone=phone);
    if user :
        if user[0].password == password:
            result["errcode"] = 0;
            result["errmsg"] = "登录成功";            
            request.session["user"] = user[0].id
            request.session.save()                         
            result["data"] = {"token" : request.session.session_key};
        else :
            result["errcode"] = -1;
            result["errmsg"] = "密码不正确";
    else:
        result["errcode"] = -1;
        result["errmsg"] = "用户不存在";      
    return HttpResponse(json.dumps(result))    
   

def isUserLogin(request):
    result = {}
    try:
        session_key = request.POST["token"]
    except:
        result["errcode"] = -1;
        result["errmsg"] = "没有提供令牌";        
        return HttpResponse(json.dumps(result))

    session = Session.objects.filter(pk=session_key)
    if  session :
        result["errcode"] = 0;
        result["errmsg"] = "已登录";
    else:
        result["errcode"] = -1;
        result["errmsg"] = "未登录";
    return HttpResponse(json.dumps(result))
   
    
def userRegister(request):
    result = {}
    phone = request.POST["phone"]
    password = request.POST["password"]
    
 

