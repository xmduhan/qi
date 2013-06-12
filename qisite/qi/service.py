# -*- coding: utf-8 -*-
'''
Created on 2013-6-3

@author: 管理员
'''
from django.http import HttpResponse
from django.core import serializers
from models import Paper
import json

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
    papers = Paper.objects.filter(catalogpaper__catalog__code="headlines") 
    return HttpResponse(serializers.serialize("json", papers))
    # return HttpResponse("hello")

def test3(request):    
    papers = Paper.objects.filter(catalogpaper__catalog__code="headlines") 
    return HttpResponse(serializers.serialize("json", papers))
    # return HttpResponse("hello")


def getCatalogPaper(request):
    papers = Paper.objects.filter(catalogpaper__catalog__code=request.POST["CatalogCode"]) 
    return HttpResponse(papers2Json(papers))
    
    
