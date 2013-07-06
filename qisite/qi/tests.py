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
from models import User, Paper, UserPaper

import json

testUser = ""

def createTestUser():
    global testUser
    testUser = User(phone="test", password="123456", name="test", state="A")
    testUser.save();

def createTestPaper():
    for i in range(3):
        paper = Paper(name="paper" + str(i), description="paper" + str(i) + "...", state='A')
        paper.save()
        userpaper = UserPaper(user=testUser, paper=paper, finish_state="finished")        
        userpaper.save()
    for i in range(4, 7):
        paper = Paper(name="paper" + str(i), description="paper" + str(i) + "...", state='A')
        paper.save()
        userpaper = UserPaper(user=testUser, paper=paper, finish_state="unfinished")        
        userpaper.save()
        
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
        
    def test_userLogin(self):
        response = processUserLogin(self.client)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], 0)  # test if can login
        
        
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
        # print(token)
        
    def test_getCatalogPaper(self):
        catalogCode = "society"
        postData = {"CatalogCode":catalogCode}
        url = "/qi/service/getCatalogPaper"
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        # print(response.content)
        result = json.loads(response.content)
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
        
    def test_userRegister(self):
        url = "/qi/service/userRegister"        
        # try to register a exist user
        postData = {"phone":"test", "password":"123456"}
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], -1)  # user already exists should not pass        
        # try to register another user that do not exist
        postData = {"phone":"test2", "password":"123456"}
        response = self.client.post(url, postData)
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], 0)  # this will be ok 

    def test_getUserPaper_1(self):
        processUserLogin(self.client)
        url = "/qi/service/getUserPaper"
        finishState = "finished"
        postData = {"FinishState":finishState}
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], 0)  # test if call is successful
        data = result["data"]
        papers = Paper.objects.filter(userpaper__user__id=testUser.id, userpaper__finish_state=finishState)
        self.assertEqual(len(data), len(papers))  # test if get the same data

    def test_getUserPaper_2(self):
        processUserLogin(self.client)
        url = "/qi/service/getUserPaper"
        #postData = {"FinishState":finishState}
        postData = {}        
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], -1)  # test when finishState is missed,can return exactly 
                 
    def test_getUserPaper_3(self):
        #processUserLogin(self.client)
        url = "/qi/service/getUserPaper"
        finishState = "finished"
        postData = {"FinishState":finishState}
        response = self.client.post(url, postData)
        self.assertEqual(response.status_code, 200)  # test url is valid
        result = json.loads(response.content)
        self.assertEqual(result["errcode"], -1)  # test if no login will not success
        