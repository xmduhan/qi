# -*- coding: utf-8 -*-
"""
This file demonstrates writing tests using the unittest module. These will pass
when you run "manage.py test".

Replace this with more appropriate tests for your application.
"""
from django.test.utils import setup_test_environment
from django.test.client import Client
from django.core.urlresolvers import reverse
from django.test import TestCase
from models import User, Paper

import json

testUser = "fuck"

def createTestUser():
    global testUser
    testUser = User(phone="test", password="123456", name="test", state="A")
    testUser.save();

def processUserLogin(client, user=None):
    if user == None :
        user = testUser
    url = "/qi/service/userLogin"
    postdata = {"phone":user.phone, "password":user.password}
    response = client.post(url, postdata)      
    return response    

        
class ClientTest(TestCase):
    def setUp(self):
        createTestUser()
        pass       
    
        
    def test_userLogin(self):
        response = processUserLogin(self.client)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], 0)  # test if can login
        pass
        
        
    def test_getCurrentUser(self):        
        response = processUserLogin(self.client)
        result = json.loads(response.content)
        token = result["data"]["token"]
        response = self.client.get('/qi/service/getCurrentUser')
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)         
        self.assertEqual(result["errcode"], 0)  # test user sesssion is exist
        data = result["data"]
        self.assertEqual(data["phone"], testUser.phone)  # test if current user is test
        self.assertEqual(data["token"], token)  # test if token is the same to login respone
        #print(token)
        
    def test_getCatalogPaper(self):
        catalogCode = "society"
        postData = {"CatalogCode":catalogCode}
        url = "/qi/service/getCatalogPaper"
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        # print(response.content)
        result = eval(response.content)
        self.assertEqual(result["errcode"], -1)  # not login should return error
        # 登陆
        processUserLogin(self.client)
        # 登陆后在执行操作                 
        response = self.client.post(url, postData)
        # print(response.content)
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], 0)  # login return ok 
        data = result["data"]   
        papers = Paper.objects.filter(catalogpaper__catalog__code=catalogCode)     
        self.assertEqual(len(data), len(papers))
        # print(data[0]["description"])
        
        
        

        
    
