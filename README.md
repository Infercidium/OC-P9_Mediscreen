# Mediscreen
Description

## Composing

### APPLICATION
This application uses java 8 with spring boot and maven, as well as MySQL and MongoDB for the databases, 
finally Docker through Dockerfiles and Docker-compose allows the proper deployment of the application.

IMAGE COMPOSITION

A Réadapté =>
### INTERACTION
Access to the user interface is through `localhost:8083`.

1. `Home`
  * Available with a simple `/`.
  * Gives access to Login and user management.
  * 
2. `Login`
  * Contains classic login and login by GitHub.
  * The classic connection requires the username and the user password, this one must have an upper case, a lower case, a number, a special character and be at least 8 characters long.
  
3. `bidList` & `curvePoint` & `rating` & `ruleName` & `trade` & `user`

  Appel d'url depuis un terminal:
  
## Launch
Application uses docker-compose, use the following command to start it:
`docker-compose start` and stop with `docker-compose stop`

### First Launch
During the first launch, it is important to check if the `ports` used by TourGuide are not already occupied on your machine, 
this information is available in `docker-compose`: `tourguide_net`, the `ipv4_adress` of each container and the `networks`.

Setup for docker-compose:

Entry into the Main module: 
`cd TourGuide`

BootJar construction:
`./gradlew bootjar`

Back to the whole project:
`cd..`

Entry into the GpsUtil module:
`cd TourGuide_GpsUtil`

BootJar construction:
`./gradlew bootjar`

Back to the whole project:
`cd..`

Entry into the RewardCentral module:
`cd TourGuide_RewardCentral`

BootJar construction:
`./gradlew bootjar`

Back to the whole project:
`cd..`

Entering the TripPricer module:
`cd TourGuide_TripPricer`

BootJar construction:
`./gradlew bootjar`

Back to the whole project:
`cd..`

And finally `docker-compose up`, for stop use `docker-compose stop`.

## Testing
This app has Unit test written. Once the test has been generated, it can be accessed by following this path: 

`JavaPathENProject8\TourGuide\build\jacocoHtml\index.html` for the Main module, 

`JavaPathENProject8\TourGuide_GpsUtil\build\jacocoHtml\index.html` for the GpsUtil module, 

`JavaPathENProject8\TourGuide_RewardCentral\build\jacocoHtml\index.html` for the RewardCentral module 

and `JavaPathENProject8\TourGuide_TripPricer\build\jacocoHtml\index.html` for the TripPricer module.

### Test Report

To generate the 4 reports:

Entry into the Main module: 
`cd TourGuide`

Test generation:
`./gradlew test`

Back to the whole project:
`cd..`

Entry into the GpsUtil module:
`cd TourGuide_GpsUtil`

Test generation:
`./gradlew test`

Back to the whole project:
`cd..`

Entry into the RewardCentral module:
`cd TourGuide_RewardCentral`

Test generation:
`./gradlew test`

Back to the whole project:
`cd..`

Entering the TripPricer module:
`cd TourGuide_TripPricer`

Test generation:
`./gradlew test`

Back to the whole project:
`cd..`
