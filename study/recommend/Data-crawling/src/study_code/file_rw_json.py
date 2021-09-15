import json

file_path = "./sample_test.json"

data = {}
data['posts'] = []
data['posts'].append({
    "title" : "i'm title",
    "overview" : "just do it!"
})

data['posts'].append({
    "title" : "i'm second title",
    "overview" : "just do it! try again!"
})

print(data)

with open(file_path, 'w') as outfile:
    json.dump(data, outfile, indent = 4)


print("------- save json -------")

with open(file_path, "r") as json_file:
    json_data = json.load(json_file)

json_data['posts'].append({
    "title" : "my third title",
    "overview" : "just do it! we can",
    "add" : "실화가"
})

with open(file_path, 'w') as outfile:
    json.dump(json_data, outfile, indent=4)


# print("------ append mode ------")

# with open(file_path, "a") as append_file:
#     for i in range(10):
#         test = {
#             "title" : f'my {i}th title',
#             "overview" : f'just do it! try : {i}'
#         }

#         append_file.write(test)

print(" ------ test ------")

with open('./test1.json', 'w', encoding="UTF-8") as test_1th:
    data_1th = []

for i in range(3):
    data_1th.append({
        'title' : f'{i}번 제목',
        'story' : f'{i}번 내용'
    })

with open('./test1.json', 'w', encoding="UTF-8") as test_1th:
    json.dump(data_1th, test_1th, indent=4 ,ensure_ascii=False)
