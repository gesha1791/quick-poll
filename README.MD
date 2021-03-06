# <a href="http://git.foxminded.com.ua/gesha17/quick-poll">Quick Poll</a>
#### System for creating polls

The application functionality is based on REST principles and realizes REST API without frontend.
- Users interact with QuickPoll services to create new polls
- Each poll contains a set of options that are provided during poll
  creation
- Options inside a poll can be updated at a later point
- To keep things simple, QuickPoll restricts voting on a single option
- Participants can cast any number of votes
- Results of a poll can be viewed by anyone

### **Main used technologies are:**
  - spring-boot-starter-data-jpa
  - spring-boot-starter-web
  - spring-boot-starter-security
  - spring-security-oauth2
  - spring-boot-starter-test
  - Lombok
  - h2
  - swagger
  - javax.inject

### **Base URI**
  http://localhost:8090

### **Available resources**
|Resource|Description|
|---|---|
|User|Singleton User Resource|
|Users|Collection User Resource|
|Poll|Singleton User Resource|
|Polls|Collection User Resource|
|Vote|Singleton User Resource|
|Votes|Collection User Resource|
|ComputeResult|Count Processing Resource|

### **Allowed operations on a Poll resource**
|HTTP Method|Resource Endpoint|Input|Success Response|Error Response|Description|
|---|---|---|---|---|---|
|GET|/polls|Body:Empty|Status:200 Body:Poll list|Status:500|Retrieves all available polls|
|POST|/polls|Body:New Poll data|Status:201 Body:Newly created poll id|Status:500|Creates a new poll|
|PUT|/polls|N/A|N/A|Status:400|Forbidden Action|
|DELETE|/polls|N/A|N/A|Status:400|Forbidden Action|
|GET|/polls/{pollId}|Body:Empty|Status:200 Body:Poll data|Status:404 or 500|Retrieves an existing poll|
|POST|/polls/{pollId}|N/A|N/A|Status:400|Foridden
|PUT|/polls/{pollId}|Body:Poll data with updates|Status:200 Body:Empty|Status:404 or 500|Updating an existing poll|
|DELETE|/polls/{pollId}|Body:Empty|Status:200|Status:404 or 500|Deletes an existing poll|

### **Allowed operations on a Vote resource**
|HTTP Method|Resource Endpoint|Input|Success Response|Error Response|Description|
|---|---|---|---|---|---|
|GET|/polls/{pollId}/votes|Body:Empty|Status:200 Body:Votes list|Status:500|Retrieves all available votes for a given poll|
|POST|/polls/{pollId}/votes|Body:New Vote|Status:201 Body:Newly created vote id|Status:500|Creates a new vote|
|PUT|/polls/{pollId}/votes|N/A|N/A|Status:400|Forbidden Action|
|DELETE|/polls/{pollId}/votes|N/A|N/A|Status:400|Forbidden Action|
|GET|/polls/{pollId}/votes/{voteId}|Body:Empty|Status:200 Body:Vote data|Status:404 or 500|Retrieves an existing vote|
|POST|/polls/{pollId}/votes/{voteId}|N/A|N/A|Status:400|Forbidden Action|
|PUT|/polls/{pollId}/votes/{voteId}|N/A|N/A|Status:400|Forbidden is a casted vote can′t be updated according to our requirements|
|DELETE|/polls/{pollId}/votes/{voteId}|N/A|N/A|Status:400|Forbidden is a casted vote can′t be updated according to our requirements|



### **Allowed operations on ComputeResult resource**
|HTTP Method|Resource Endpoint|Input|Success Response|Error Response|Description|
|---|---|---|---|---|---|
|GET|/computeresult|Body:Empty Param:pollId|Status:200 Body:Vote count|Status 500|Returns the vote count for the given poll|

### **Request and response data format**
- application/JSON

## **Security**

#### Test user information

|Username|Password|Is Admin|
|---|---|---|
|mickey|cheese|no|
|minnie|red01|no|
|donald|quack|no|
|daisy|quack2|no|
|clarabelle|moo|no|
|admin|admin|yes|

#### CURL commands

curl -u mickey:cheese http://localhost:8090/v3/polls get all polls, base authentification

curl -i -u admin:admin -X DELETE http://localhost:8090/v3/polls/13 delete poll
curl -i -u mickey:cheese -X DELETE http://localhost:8090/v3/polls/13 forbidden 403

OAuth 2

curl -u quickpolliOSClient:top_secret -X POST http://localhost:8090/oauth/token -H “Accept:application/json” -d “username=mickey&password=cheese&grant_type=password”
generate a token for the user mickey

access token:
{"access_token":"77ed953e-b3b6-4ea1-820e-2e9acc702293","token_type":"bearer","expires_in":42874,"scope":"read write"}

curl http://localhost:8090/oauth2/v3/polls -H "Authorization: Bearer 77ed953e-b3b6-4ea1-820e-2e9acc702293" GET all polls OAuth 2

curl -u quickpolliOSClient:top_secret -X POST http://localhost:8090/oauth/token -H "Accept:application/json" -d "username=admin&password=admin&grant_type=password"
generate a token for the user admin

curl -X DELETE http://localhost:8080/oauth2/v3/polls/11 -H "Authorization: Bearer 946e1f71-21c3-4923-bf96-3b0f8cea708a" DELETE poll