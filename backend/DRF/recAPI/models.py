from django.db import models

# Create your models here.
class Books(models.Model):
    BookId = models.AutoField(primary_key=True)
    BookName = models.CharField(max_length=500)

class Users(models.Model):
    UserkId = models.AutoField(primary_key=True)
    UserId = models.CharField(max_length=500)