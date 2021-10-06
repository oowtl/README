from django.urls import path
from django.urls.resolvers import URLPattern
from . import views

app_name="testRec"

urlpatterns = [
    path('test/db/', views.test_database, name="test_database")
]