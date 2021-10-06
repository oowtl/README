from django.shortcuts import render
from django.views.decorators.http import require_GET

# DB
from testRec.models import Book

# Create your views here.

@require_GET
def test_database(request):
    books = Book.objects.all()
    print(books[0:10])
    return 0