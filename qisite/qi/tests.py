"""
This file demonstrates writing tests using the unittest module. These will pass
when you run "manage.py test".

Replace this with more appropriate tests for your application.
"""

from django.test import TestCase


class SimpleTest(TestCase):
    def test_basic_addition(self):
        """
        Tests that 1 + 1 always equals 2.
        """
        self.assertEqual(1 + 1, 2)
        
'''
from qi.models import *;
c = Catalog.objects.get(id = 44)

import sys,locale
def p(f):print '%s.%s(): %s' % (f.__module__, f.__name__, f())
p(sys.getdefaultencoding)
p(sys.getfilesystemencoding)
p(locale.getdefaultlocale)
p(locale.getdefaultlocale)
p(locale.getpreferredencoding)
 
reload(sys)
sys.setdefaultencoding('utf-8') 
'''    
