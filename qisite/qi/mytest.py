# -*- coding: utf-8 -*-
'''
Created on 2013-5-29
@author: 管理员
'''

from qi.models import *
from datetime import datetime;
from datetime import date;
from django.db.models import F;   
# add test 

# print(Catalog.objects.all())



# Catalog(name="测试",code="test").save();


# print(Catalog.objects.get(id=1));
# print(Catalog.objects.get(name="根目录"));
# print(Catalog.objects.get(id=1, name="根目录" , state='A'));


# print(Catalog.objects.filter(code__startswith="c", state="A"));
# print(Catalog.objects.filter(code__startswith="c", state="A").filter(id = 42));
# print(Catalog.objects.filter(code__startswith="c", state="A").exclude(id = 42));


# print(Catalog.objects.filter(create_date__year=2013));
# print(Catalog.objects.filter(create_date__gte=date.today()));
# print(Catalog.objects.filter(create_date__gte=datetime(2013, 5, 23)));



# print(Catalog.objects.filter(code__icontains="er"))
# print(Catalog.objects.filter(code__icontains="i"))

# print(Catalog.objects.filter(pk=1).exists())

# print(Catalog.objects.all())
# print(Catalog.objects.all()[:5])
# print(Catalog.objects.all()[0:5])
# print(Catalog.objects.all()[3:5])
# print(Catalog.objects.all()[1:6:2])



# print(Catalog.objects.order_by('ord')[0:1])
# print(Catalog.objects.order_by('ord')[11])


# print(Catalog.objects.filter(create_date__lte='2013-05-23'))


# print(Catalog.objects.filter(code__exact="public"));
# print(Catalog.objects.filter(code__iexact="PUBLIC"));
# print(Catalog.objects.filter(code__exact="PUBLIC"));


# print(Catalog.objects.filter(code__contains="er"));
# print(Catalog.objects.filter(code__icontains="ER"));
# print(Catalog.objects.filter(code__contains="ER"));

# print(Catalog.objects.filter(code__startswith="c"));
# print(Catalog.objects.filter(code__endswith="e"));
# print(Catalog.objects.filter(code__istartswith="C"));
# print(Catalog.objects.filter(code__iendswith="E"));


# print(Catalog.objects.filter(parent__name="根目录"))

# print(Catalog.objects.filter(papers__name__startswith="公益"))


# print(Catalog.objects.filter(parent__isnull=True))

# print(Catalog.objects.filter(catalogpaper__paper__name__startswith="公益"));


# print(Paper.objects.filter(catalogpaper__catalog__name="公益"))

# print(Catalog.objects.filter(catalogpaper__paper__name="公益问卷1" , catalogpaper__paper__name__startswith='公益问卷2'));
# print(Catalog.objects.filter(catalogpaper__paper__name="公益问卷1").filter(catalogpaper__paper__name='公益问卷2'));


# print(Catalog.objects.exclude(catalogpaper__paper__name_startswith=F('name')))
# print(Catalog.objects.exclude(catalogpaper__paper__name__startswith=F('name')))
# print(Catalog.objects.filter(catalogpaper__paper__name__startswith=F('catalogpaper__paper__name')))
# print(Catalog.objects.filter(name=F('name__startswith')))

# print(F('somefield').bitand(16))
# print(Catalog.objects.filter(name__startswith=F('name')))


# print(Catalog.objects.filter(name__in=["商业", "社会"]))

#  

# print([c.code for c in Catalog.objects.all()])
# print([c.name for c in Catalog.objects.all()])
# cl = Catalog.objects.all();
# print([c.code for c in cl])
# print([c.name for c in cl])

# cl = Catalog.objects.all();
# cl[1];  # 访问1次数据库
# cl[1];  # 在访问1次数据库


# [c for c in queryset]
# bool(queryset)
# c in queryset
# list(queryset)


'''    复杂查询Q()   '''


'''    对象比较        '''


'''    对象删除      '''


'''    对象拷贝       '''


'''    一次更新多个对象      '''



c = Catalog.objects.get(id=41);
cp = c.catalogpaper_set.all();
for p in cp :
    print(p.paper.name)













