from .models import Books, Users

from rest_framework import serializers



# 삭제
class BookSerializer(serializers.ModelSerializer):
    class Meta:
        model = Books
        fields = (
            'BookId',
            'BookName'
        )

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = Users
        fields = (
            'UserkId',
            'UserId'
        )
