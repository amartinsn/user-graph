## Registering User Events

```
POST /users/1/events
{ "eventName" : "read", "object": { "type":"Article", "subject": "Politics", "url":"http://www.globo.com/politics/2322345" } }
{ "eventName" : "watched", "object": { "type":"Video", "subject": "Sports", "url":"http://www.globo.com/sports/video/123234" } }
{ "eventName" : "commented", "object": { "type":"Article", "subject": "Entertainment", "url":"http://www.globo.com/foo/2333" } }
{ "eventName" : "rated", "object": { "type":"CookingRecipe", "url":"http://www.globo.com/foo/2333" } }
```


## Registering User Preferences

```
POST /users/1/preferences
{ "supports": { "type":"SoccerTeam", "name":"Flamengo" } }
```