# -*- coding: gb18030 -*-
'''
Created on 2013-5-19

'''

from django.contrib import admin;
from school.models import *;
# admin.site.register(People);

def upper_case_name(obj):
    return ((obj.name).upper())
upper_case_name.short_description = 'Name'

class EnglishTeacherAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    date_hierarchy = 'birthdate'
    # fields = (('name', 'birthdate', 'EnglishGrade'), 'teachclass')  
    # exclude = ()
    list_display = ('id', upper_case_name, 'name', 'birthdate', 'EnglishGrade', 'glass')    
    
    '''
    fieldsets = (
        ('group1', {'fields': ('name', 'birthdate', 'EnglishGrade')}),    
        ('group2', {'fields': ('teachclass',), 'classes': ['collapse']})    
    )
    '''
    fieldsets = (
        ('group1', {'fields': ('name', 'birthdate', 'EnglishGrade', 'glass'), 'classes':['extrapretty']}),
        ('group2', {'fields': ('teachclass',), 'classes':['wide', 'collapse']})
    )
    filter_horizontal = ['teachclass']
    # filter_vertical = ['teachclass']
    list_display_links = ('id',)
    list_editable = ('name', 'EnglishGrade');
    list_filter = ('name', 'teachclass')
    
admin.site.register(Class);
admin.site.register(Student);
admin.site.register(Teacher);
admin.site.register(EnglishTeacher, EnglishTeacherAdmin);
admin.site.register(EnglishTeacher2);







