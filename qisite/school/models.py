from django.db import models

# Create your models here.
# G:\pydev\mysite2

class People(models.Model):
    name = models.CharField(max_length=50);
    birthdate = models.DateTimeField(blank=True, null=True);
    class Meta:
        abstract = True;        

class Class(models.Model):
    name = models.CharField(max_length=50);
    def __unicode__(self):
        return self.name
    pass;     

class Student(People):
    SHIRT_SIZES = (
        ('S', 'Small'),
        ('M', 'Medium'),
        ('L', 'Large'),
    )
    shirt_size = models.CharField(max_length=2, choices=SHIRT_SIZES)   
    studyclass = models.ForeignKey(Class)


class Teacher(People):
    teachclass = models.ManyToManyField(Class, blank=True)
    glass = models.BooleanField(blank=True)     


class EnglishTeacher(Teacher):
    Grade = (
        ('H', 'High'),
        ('M', 'Medium'),
        ('N', 'Normal'),
    );
    EnglishGrade = models.CharField(max_length=2, choices=Grade)   
     


class EnglishTeacher2(Teacher):
    class Meta:
        proxy = True;
        
    
