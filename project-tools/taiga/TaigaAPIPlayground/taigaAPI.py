import requests
import datetime
from getpass import getpass

refreshToken = ""
authToken = ""
print("Enter username or email: ")
username = input()
password = getpass()

login = {
    "password": password,
    "type": "normal",
    "username": username
}
loginResponse = requests.post('https://api.taiga.io/api/v1/auth', data=login)
if loginResponse.status_code != 200:
    print('Error code ' + str(loginResponse.status_code) +
          ' - Login failed. Terminating script!')
else:
    auth = loginResponse.json()
    refreshToken = auth['refresh']
    authToken = auth['auth_token']
    headers = {
        "Authorization": "Bearer " + authToken
    }
    print('Login successful!')
    print()
    print("Enter the project slug:")

    slug = input()

    response = requests.get(
        "https://api.taiga.io/api/v1/projects/by_slug?slug=" + slug, headers=headers)

    if response.status_code != 200:
        print('Error code ' + str(response.status_code) +
              ' - Project not found. Terminating script!')
    else:
        project = response.json()
        print()
        print('This project has ' +
              str(len(project["members"])) + ' members. The members are: ')
        print('----------------------------------------------------------')
        members = {}
        for member in project["members"]:
            members[member["full_name_display"]] = 0
            print(member["full_name_display"] + " : " + member["role_name"])

        print()
        sprints = requests.get(
            "https://api.taiga.io/api/v1/milestones?project=" + str(project["id"]), headers=headers).json()
        print("This project has " + str(len(sprints)) + " sprints:")
        print('----------------------------------------------------------')
        i = 0
        for sprint in sprints:
            print(str(i) + " : " + sprint["name"])
            print("Start date: " + sprint["estimated_start"])
            print("End date: " + sprint["estimated_finish"])
            print("Total points: " + str(sprint["total_points"]))
            print("Closed points: " + str(sprint["closed_points"]))
            print()
            i += 1

        print("Choose a sprint (type in the number next to the sprint name): ")
        sprintNum = input()
        print()
        print("You chose sprint " + sprints[int(sprintNum)]["name"])

        milestone = requests.get(
            "https://api.taiga.io/api/v1/milestones/" + str(sprints[int(sprintNum)]["id"]), headers=headers).json()



        print("The user stories are: ")
        print('----------------------------------------------------------')
        for userStory in milestone["user_stories"]:
            print("Name: " + userStory["subject"])
            print("Finished: " + str(userStory["finish_date"] is not None))
            print("Created: " + datetime.datetime.strptime(
                userStory["created_date"], '%Y-%m-%dT%H:%M:%S.%fZ').strftime("%Y-%m-%d"))
            print("Moved to sprint: " + datetime.datetime.strptime(
                userStory["modified_date"], '%Y-%m-%dT%H:%M:%S.%fZ').strftime("%Y-%m-%d"))
            print()

        print("Tasks: ")
        print('----------------------------------------------------------')
        tasks = requests.get('https://api.taiga.io/api/v1/tasks?project=' + str(
            project["id"]) + '&milestone=' + str(sprints[int(sprintNum)]["id"]), headers=headers).json()
        for task in tasks:
            print("Name: " + task["subject"])
            if task["assigned_to_extra_info"] is None:
                print("Assigned to: None")
            else:
                name = task["assigned_to_extra_info"]["full_name_display"]
                print("Assigned to: " + name)
                members[name] += 1
            print()

        print("Total: ")
        print('----------------------------------------------------------')
        for key in members:
            print("Name: " + key)
            print("Assigned tasks: " + str(members[key]))

        print('----------------------------------------------------------')
        userstory = requests.get(
            "https://api.taiga.io/api/v1/userstories/3847664", headers=headers).json()

        print("User Story description - ",userstory["description"])
        print("User Story tags - ",userstory["tags"])

        userstoryhistory = requests.get(
            "https://api.taiga.io/api/v1/history/userstory/3847664", headers=headers).json()

        # print(userstoryhistory)
        all_w = list()
        for item in userstoryhistory:
            if 'comment' in item:
                if item['comment'] != "":
                    all_w.append(item['comment'])
        print("Here are the comments - ", all_w)
