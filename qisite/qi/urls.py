from django.conf.urls import patterns, url
from qi import service

urlpatterns = patterns('',
    url(r'^service/test1', service.test1, name='test1'),
    url(r'^service/test2', service.test2, name='test2'),
    url(r'^service/test3', service.test3, name='test3'),
    url(r'^service/getCatalogPaper', service.getCatalogPaper, name='getCatalogPaper'),
    url(r'^service/userLogin', service.userLogin, name='userLogin'),
    url(r'^service/userRegister', service.userRegister, name='userRegister'),
    url(r'^service/isUserLogin', service.isUserLogin, name='isUserLogin'),
    
) 
