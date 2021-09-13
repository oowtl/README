from .models import Store
from rest_framework import serializers



# 삭제
# class StoreSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = Store
#         fields = [
#             "id",
#             "store_name",
#             "branch",
#             "area",
#             "tel",
#             "address",
#             "latitude",
#             "longitude",
#             "category_list",
#         ]
