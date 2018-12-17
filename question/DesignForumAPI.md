# Design Forum API in https://www.apicur.io/.

#### 1) Идентифицировать ресурсы, минимум 5:
      topics, posts, votes, comments, users


#### 2) Примеры репрезентаций (с учетом выбранного формата репрезентаций json, здесь нужно для каждого ресурса привести конкретный пример сереализованного json объекта с заполненными значениями полей)Ж
    topic
        {
            "id": 1,
            "themes": "What is your favorite film?"
            "posts":
            [
                {"id": 3, "postValue": "Titanik"},
                {"id": 4, "postValue": "Titanik"},
                {"id": 5, "postValue": "Titanik"}
            ]
        }

    post
                 {
                     "id": 7,
                     "postValue": "Titanik",
                     "comments":
                     [
                          {"id": 6, "commentValue": "Bad"},
                          {"id": 7, "commentValue": "Good"},
                          {"id": 8, "comentValue": "Nice"}
                     ]
                     "countVotes" : 23
                 }

    comment
        {
            {"id": 12, "commentValue": "Bad"},
        }

    vote
        {
            "id": 23,
            "post": {"id": 7, "postValue": "Titanik"}
        }

    user
    {
        “id” :  54,
        “name”: “Evgen”,
        		"posts" :
       			[
       			    {“id” : 2133,  “postValue” : “sdfsdf” },
       			    {“id” : 123,  “postValue” : “vcb” },
       			    {“id” :  213, “postValue” : “cbv” }
      			]

                "comments":
                [
        			{“id” : 4342,  “postValue” : “sdfsdf” },
        			{“id” : 234,  “postValue” : “vcb” },
        			{“id” :  1234, “postValue” : “cbv” }
        		]
        }

    }

#### Несколько эндпоинтов

    https://api.forum.com/users/
    https://api.forum.com/users/{userId}
    https://api.forum.com/users?userId=23 // request parametr
    https://api.forum.com/topics/
    https://api.forum.com/topics/{topicId}
    https://api.forum.com/topics/{topicId}/posts
    https://api.forum.com/topics/{topicId}/posts/{postId}
    https://api.forum.com/topics/{topicId}/posts?postId=1 // request parametr


#### Пример экшинов (операций/методов/http verbs), с примерами ответов, http codes ("happy path", errors - в каких случаях какая ошибка), какие операции на какие эндпоинты запрещены (5-15 при наличии в дизайне)?

### **Allowed operations on a Topics resource**
|HTTP Method|Resource Endpoint|Input|Success Response|Error Response|Description|
|---|---|---|---|---|---|
|GET|/topics|Body:Empty|Status:200 Body:Topics list|Status:500|Retrieves all available topics|
|POST|/topics|Body:New Topics data|Status:201 Body:Newly created topic id|Status:500|Creates a new topic|
|PUT|/topics|N/A|N/A|Status:400|Forbidden Action|
|DELETE|/topics|N/A|N/A|Status:400|Forbidden Action|
|GET|/topics/{topicId}|Body:Empty|Status:200 Body:Topic data|Status:404 or 500|Retrieves an existing topic|
|POST|/topics/{topicId}|N/A|N/A|Status:400|Foridden
|PUT|/topics/{topicId}|Body:Topic data with updates|Status:200 Body:Empty|Status:404 or 500|Updating an existing topic|
|DELETE|/topicss/{topicId}|Body:Empty|Status:200|Status:404 or 500|Deletes an existing topic|

### **Allowed operations on a Posts resource**
|HTTP Method|Resource Endpoint|Input|Success Response|Error Response|Description|
|---|---|---|---|---|---|
|GET|/topics/{topicId}/posts|Body:Empty|Status:200 Body:Posts list|Status:500|Retrieves all available posts for a given topic|
|POST|/topics/{topicId}/posts|Body:New post|Status:201 Body:Newly created post id|Status:500|Creates a new post|
|PUT|/topics/{topicId}/posts|N/A|N/A|Status:400|Forbidden Action|
|DELETE|/topics/{topicId}/posts|N/A|N/A|Status:400|Forbidden Action|
|GET|/topics/{topicId}/posts/{postId}|Body:Empty|Status:200 Body:post data|Status:404 or 500|Retrieves an existing post|
|POST|/topics/{topicId}/posts/{postId}|N/A|N/A|Status:400|Forbidden Action|
|PUT|/topics/{topicId}/posts/{postId}|Body:Post data with updates|Status:200 Body:Empty|Status:404 or 500|Updating an existing post|
|DELETE|/topics/{topicId}/posts/{postId}|Body:Empty|Status:200|Status:404 or 500|Deletes an existing post|


#### Примеры интеракций запрос-ответ (задействовать вещи из предыдущих пунктов)

##### Запрос
GET https://api.forum.com/posts/
##### Заголовк
N/A
##### Тело
N/A
##### Ответ
200 OK

##### Заголовок
Content-Type →application/json;charset=UTF-8
Transfer-Encoding →chunked
Date →Mon, 17 Dec 2018 08:17:33 GMT
##### Тело


    [

        {
            "id": 7,
            "postValue": "Titanik",
            "comments":
            [
                 {"id": 6, "commentValue": "Bad"},
                 {"id": 7, "commentValue": "Good"},
                 {"id": 8, "comentValue": "Nice"}
            ]
            "countVotes" : 23
        },

        post
                {
                    "id": 8,
                    "postValue": "Titanik",
                    "comments":
                    [
                         {"id": 12, "commentValue": "Bad"},
                         {"id": 23, "commentValue": "Good"},
                         {"id": 43, "comentValue": "Nice"}
                    ]
                    "countVotes" : 25
                }
      ]

##### Запрос
PUT https://api.forum.com/posts/{postId}
##### Заголовк
N/A
##### Тело
        {
            "id": 7,
            "postValue": "Titanik good or bad film",
            "comments":
            [
                 {"id": 6, "commentValue": "Bad"},
                 {"id": 7, "commentValue": "Good"},
                 {"id": 8, "comentValue": "Nice"}
            ]
            "countVotes" : 23
        }
##### Ответ
200 OK

##### Заголовок
Content-Type →application/json;charset=UTF-8
Transfer-Encoding →chunked
Date →Mon, 17 Dec 2018 08:17:33 GMT
##### Тело

    {
            "id": 7,
            "postValue": "Titanik good or bad film",
            "comments":
            [
                 {"id": 6, "commentValue": "Bad"},
                 {"id": 7, "commentValue": "Good"},
                 {"id": 8, "comentValue": "Nice"}
            ]
            "countVotes" : 23
        }