# achievement-activity

## About

Just a test project in which I tried different technologies and approaches.

- **[1]** Send action: **[POST]** `localhost:8080/activity/save` -> **[BODY]** `{ "userId": 1, "action": "test" }`
- **[2]** Get achievements: **[GET]** `localhost:8081/achievements/get` -> **[RESPONSE]** `["achievement1", "achievement2"]`

### Technologies

* Language: **Java, SQL**
* Technologies: **Javalin, Kafka, Liquibase**
* Database: **PostgreSQL**
* Deploy: **Docker**

## Installing

### Clone the project

```shell
git clone https://github.com/l1ve4code/achievement-activity.git
```

### Project startup _(using Docker)_

Just run this command and project will be built and launched automatically

```shell
docker compose up -d
```

Good luck ✨

## Author

* Telegram: **[@live4code](https://t.me/live4code)**
* Email: **steven.marelly@gmail.com**