## Creating A New User Node

```
HTTP POST /users
{
    "id": "4", // cadun id
    "name": "Chuck Norris",
    "username": "chuck.norris",
    "email": "chuck@gmail.com"
}
```


## Creating A Friendship Between Two Users

```
HTTP POST /friendships/:userId-:otherUserId
```


## Deleting A Friendship Between Two Users

```
HTTP DELETE /friendships/:userId-:otherUserId
```


## Registering User Events

```
HTTP POST /users/:id/events
[
    {
        "eventName": "read",
        "object": {
            "type": "Article",
            "subject": "Politics",
            "url": "http://www.globo.com/politics/2322345"
        }
    },
    {
        "eventName": "watched",
        "object": {
            "type": "Video",
            "subject": "Sports",
            "url": "http://www.globo.com/sports/video/123234"
        }
    },
    {
        "eventName": "commented",
        "object": {
            "type": "Article",
            "subject": "Entertainment",
            "url": "http://www.globo.com/foo/2333"
        }
    },
    {
        "eventName": "rated",
        "object": {
            "type": "CookingRecipe",
            "url": "http://www.globo.com/foo/2333"
        }
    }
]
```


## Registering User Preferences

```
HTTP POST /users/:id/preferences
{
    "supports": {
        "type": "SoccerTeam",
        "name": "Flamengo"
    }
}
```