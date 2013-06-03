from django.conf.urls import patterns, url
from qi import service

urlpatterns = patterns('',
    url(r'^service/test', service.test, name='test'),    
)
