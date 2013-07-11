from django.conf.urls import patterns, url
from qi import service

urlpatterns = patterns('',
    url(r'^service/getCatalogPaper', service.getCatalogPaper, name='getCatalogPaper'),
    url(r'^service/userLogin', service.userLogin, name='userLogin'),
    url(r'^service/getCurrentUser', service.getCurrentUser, name='getCurrentUser'),
    url(r'^service/userRegister', service.userRegister, name='userRegister'),
    url(r'^service/getUserPaper', service.getUserPaper, name='getUserPaper'), 
    
) 
