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
    "overview" : "just do it! we can"
})

with open(file_path, 'w') as outfile:
    json.dump(json_data, outfile, indent=4)