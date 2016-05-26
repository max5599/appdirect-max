# Appdirect-max

## Requirement
Java 8

## To package and run
gradlew build && java -jar build/libs/appdirect-max-1.0.0.jar

## Usage

### Subscription
Subscription use OAuth 1.0 authorization

#### Subscription Create Notification URL
https://appdirect-max.herokuapp.com/subscription/create?url={eventUrl}

#### Subscription Cancel Notification URL
https://appdirect-max.herokuapp.com/subscription/cancel?url={eventUrl}

### List user
https://appdirect-max.herokuapp.com/