# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Book(models.Model):
    title = models.CharField(max_length=255, blank=True, null=True)
    publisher = models.CharField(max_length=255, blank=True, null=True)
    story = models.CharField(max_length=10000, blank=True, null=True)
    price = models.IntegerField(blank=True, null=True)
    img = models.CharField(max_length=255, blank=True, null=True)
    pday = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'book'


class BookAuthor(models.Model):
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')
    author_aid = models.ForeignKey('TableAuthor', models.DO_NOTHING, db_column='author_aid')

    class Meta:
        managed = False
        db_table = 'book_author'


class BookGenre(models.Model):
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')
    genre_gid = models.ForeignKey('TableGenre', models.DO_NOTHING, db_column='genre_gid')

    class Meta:
        managed = False
        db_table = 'book_genre'


class BookKeyword(models.Model):
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')
    keyword_kid = models.ForeignKey('TableKeyword', models.DO_NOTHING, db_column='keyword_kid')

    class Meta:
        managed = False
        db_table = 'book_keyword'


class BookLike(models.Model):
    user_uid = models.ForeignKey('User', models.DO_NOTHING, db_column='user_uid')
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')

    class Meta:
        managed = False
        db_table = 'book_like'


class BookReview(models.Model):
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')
    user_uid = models.ForeignKey('User', models.DO_NOTHING, db_column='user_uid')
    point = models.FloatField(blank=True, null=True)
    content = models.CharField(max_length=100, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'book_review'


class BookTendency(models.Model):
    user_uid = models.ForeignKey('User', models.DO_NOTHING, db_column='user_uid')
    book_bid = models.ForeignKey(Book, models.DO_NOTHING, db_column='book_bid')

    class Meta:
        managed = False
        db_table = 'book_tendency'


class Common(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'common'


class CommonDetail(models.Model):
    com = models.ForeignKey(Common, models.DO_NOTHING)
    common_detail = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'common_detail'


class TableAuthor(models.Model):
    author = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'table_author'


class TableGenre(models.Model):
    genre = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'table_genre'


class TableKeyword(models.Model):
    content = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'table_keyword'


class User(models.Model):
    password = models.CharField(max_length=255, blank=True, null=True)
    age = models.IntegerField(blank=True, null=True)
    job = models.CharField(max_length=255, blank=True, null=True)
    sex = models.CharField(max_length=45, blank=True, null=True)
    mbti = models.CharField(max_length=45, blank=True, null=True)
    tendency = models.CharField(max_length=255, blank=True, null=True)
    nickname = models.CharField(max_length=255, blank=True, null=True)
    user_id = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user'


class UserTendency(models.Model):
    user_uid = models.ForeignKey(User, models.DO_NOTHING, db_column='user_uid')
    user_genre = models.CharField(max_length=255, blank=True, null=True)
    user_author = models.CharField(max_length=255, blank=True, null=True)
    user_publisher = models.CharField(max_length=255, blank=True, null=True)
    user_keyword = models.CharField(max_length=255, blank=True, null=True)
    user_tendencycol = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'user_tendency'
