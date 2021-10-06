from django.shortcuts import render
from django.views.decorators import csrf
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser
from django.http.response import JsonResponse

from recAPI.models import Books, Users
from recAPI.serializers import BookSerializer, UserSerializer
# Create your views here.

from django.core.files.storage import default_storage

@csrf_exempt
def BookApi(request,id=0):
    if request.method=='GET':
        books = Books.objects.all()
        books_serializer=BookSerializer(books, many=True)
        return JsonResponse(books_serializer.data, safe=False)
    elif request.method=='POST':
        book_data = JSONParser().parse(request)
        books_serializer=BookSerializer(data=book_data)
        if books_serializer.is_valid():
            books_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    elif request.method=='PUT':
        book_data = JSONParser().parse(request)
        book=Books.objects.get(BookId=book_data['BookId'])
        books_serializer=BookSerializer(book, data=book_data)
        if books_serializer.is_valid():
            books_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        return JsonResponse("Failed to update")
    elif request.method=='DELETE':
        book=Books.objects.get(BookId=id)
        book.delete()
        return JsonResponse("Deleted Successfully", safe=False)


@csrf_exempt
def UserApi(request,id=0):
    if request.method=='GET':
        users = Users.objects.all()
        users_serializer=UserSerializer(users, many=True)
        return JsonResponse(users_serializer.data, safe=False)
    elif request.method=='POST':
        user_data = JSONParser().parse(request)
        users_serializer=UserSerializer(data=user_data)
        if users_serializer.is_valid():
            users_serializer.save()
            return JsonResponse("Added Successfully", safe=False)
        return JsonResponse("Failed to Add", safe=False)
    elif request.method=='PUT':
        user_data = JSONParser().parse(request)
        user=Users.objects.get(UserId=user_data['UserkId'])
        users_serializer=UserSerializer(user, data=user_data)
        if users_serializer.is_valid():
            users_serializer.save()
            return JsonResponse("Updated Successfully", safe=False)
        return JsonResponse("Failed to update")
    elif request.method=='DELETE':
        user=Users.objects.get(UserkId=id)
        user.delete()
        return JsonResponse("Deleted Successfully", safe=False)

@csrf_exempt
def SaveFile(request):
    file=request.FILES['file']
    file_name=default_storage.save(file.name, file)
    return JsonResponse(file_name, safe=False)


@csrf_exempt
def recommend(request):
    if request.method=='GET':