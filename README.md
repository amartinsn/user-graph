# Feeding The Graph

## Creating a user node

```
HTTP POST /users
{
    "id": "4", // cadun id
    "name": "Chuck Norris",
    "username": "chuck.norris",
    "email": "chuck@gmail.com"
}
```

## Creating a friendship

```
HTTP POST /friendships/:userId-:otherUserId
```

## Deleting a friendship

```
HTTP DELETE /friendships/:userId-:otherUserId
```

## Registering user preferences
(WIP)

```
HTTP POST /users/:id/preferences
{
    "follows": {
        "section": {
            "name": "Politica",
            "url": "http://semantica.globo.com/G1/Politica"
        }
    }
}
```

## Registering user events
(TODO)

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

# Querying The Graph

## List of users with a given preference
(WIP)

```
HTTP GET /users/supports/SoccerTeam?name=flamengo
```


## List of friends who follows a given Section
(WIP)

```
HTTP GET /users/:id/friends/follows/section?name=Politica
```