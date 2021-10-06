from django.conf.urls import url
from recAPI import views

from django.conf.urls.static import static
from django.conf import settings

urlpatterns=[
    url(r'^book$', views.BookApi),
    url(r'^book/([0-9]+)$', views.BookApi),

    url(r'^user$', views.UserApi),
    url(r'^user/([0-9]+)$', views.UserApi),

    url(r'^user/saveFile', views.SaveFile)
]+static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)