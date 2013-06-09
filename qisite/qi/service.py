# -*- coding: utf-8 -*-
'''
Created on 2013-6-3

@author: 管理员
'''
from django.http import HttpResponse
from django.core import serializers
from models import *;


def test(request):    
    return HttpResponse("hello");

def getCatalogPaper(request):
    code = request.POST["CatalogCode"]     
    papers = Paper.objects.filter(catalogpaper__catalog__code=code) 
    return HttpResponse(serializers.serialize("json", papers))
    
    
