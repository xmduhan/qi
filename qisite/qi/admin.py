# -*- coding: utf-8 -*-
'''
Created on 2013-5-21

@author: 
'''
from django.contrib import admin;
from qi.models import *;
# add a line to test 2013-06-02 

class CatalogAmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('code', 'name', 'parent', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),  # 'classes':['collapse']
        # ('问卷信息', {'fields': ('papers',)})
    )
    list_display = ('code', 'name', 'parent', 'ord', 'state', 'create_date', 'modify_date') 
    # filter_horizontal = ['papers']
    pass

class PaperAmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    list_display = ('id', 'name', 'description', 'state', 'create_date', 'modify_date')
    fieldsets = (
        ('基本信息', {'fields': ('name', 'description')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),  # 'classes':['collapse']
    )
    pass

class CatalogPaperAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('catalog', 'paper', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),  # 'classes':['collapse']
    )
    list_display = ('getCatalogCode', 'catalog', 'paper', 'ord', 'state', 'create_date', 'modify_date')
    search_fields = ['catalog__name', "catalog__code", "paper__name"]
    list_filter = ('catalog',)    
    pass

class QuestionAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    list_display = ('code', 'title', 'type', 'paper', 'ord', 'state', 'create_date', 'modify_date')
    fieldsets = (
        ('基本信息', {'fields': ('code', 'title', 'type', 'paper', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    search_fields = ['paper__name']
    list_filter = ('paper',)    

class QuestionTypeAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('code', 'name', 'description', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('code', 'name', 'description', 'ord', 'state', 'create_date', 'modify_date')
    
class ChoiceAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('code', 'text', 'type', 'question', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('code', 'text', 'type', 'question', 'ord', 'create_date', 'modify_date')
    list_filter = ('question',)  
    pass    

class ChoiceTypeAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('code', 'name', 'description', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('code', 'name', 'description', 'ord', 'create_date', 'modify_date')
    pass    

class UserAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('phone', 'name')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('phone', 'name', 'state', 'create_date', 'modify_date')

class UserPaperAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('user', 'paper')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('user', 'paper', 'state', 'create_date', 'modify_date')

class UserQuestionAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('userpaper', 'question', 'ord')}),
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('userpaper', 'question', 'state', 'create_date', 'modify_date')

class UserChoiceAdmin(admin.ModelAdmin):
    actions_on_top = True;
    actions_on_bottom = True;
    actions_selection_counter = True
    fieldsets = (
        ('基本信息', {'fields': ('userquestion', 'choice', 'text', 'grade', 'ord')}), 
        ('状态信息', {'fields': ('state', 'create_date', 'modify_date')}),
    )
    list_display = ('userquestion', 'choice', 'text', 'grade', 'ord', 'state', 'create_date', 'modify_date')

    
admin.site.register(Catalog, CatalogAmin);
admin.site.register(Paper, PaperAmin);
admin.site.register(CatalogPaper, CatalogPaperAdmin);
admin.site.register(QuestionType, QuestionTypeAdmin);
admin.site.register(Question, QuestionAdmin);
admin.site.register(ChoiceType, ChoiceTypeAdmin);
admin.site.register(Choice, ChoiceAdmin);
admin.site.register(User, UserAdmin);
#admin.site.register(UserPaper, UserPaperAdmin);
#admin.site.register(UserQuestion, UserQuestionAdmin);
#admin.site.register(UserChoice, UserChoiceAdmin);



