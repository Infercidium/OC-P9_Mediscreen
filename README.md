# Mediscreen
The Mediscreen application allows the physician to make a preventive screening for type 2 diabetes based on patient information and associated medical notes.

## Composing

### APPLICATION
This application uses java 8 with spring boot and maven, as well as MySQL and MongoDB for the databases, 
finally Docker through Dockerfiles and Docker-compose allows the proper deployment of the application.

![schemas P9](https://user-images.githubusercontent.com/82523651/152108360-ce8b3903-a1d2-4b93-95f3-ac321914d85a.png)

### INTERACTION
Access to the user interface is through `localhost:8083`.

`Home`
  * Available with a simple `/`.
  * Gives access to Research for found patient.

  Call url from terminal:
  
  `curl -d "family={family}&given={given}&dob={dob}&sex={sex}&address={address}" -X POST http://localhost:8081/patient/add`
  * Allows you to add a patient in the MySQL database by going directly through the Info-module.

  `curl -d "patId={patId}&=Patient: {Patient.family} Practitioner's notes/recommendations: {notes/recommendations}" -X POST http://localhost:8082/patHistory/add`
  * Allows you to add a note in the MongoDB database by going directly through the Note-module.

  `curl -d "patId={patId}" -X POST http://localhost:8080/assess/id`
  * Allows you to obtain the result of the diabetes risk report by going directly through the Assess module. 
  * For the patient with the same id.

  `curl -d "familyName={familyName}" -X POST http://localhost:8080/assess/familyName`
  * Allows you to obtain the result of the diabetes risk report by going directly through the Assess module. 
  * For the patient with the same last name, the first.
  
## Launch
Application uses docker-compose, use the following command to start it:
`docker-compose start` and stop with `docker-compose stop`

### First Launch
During the first launch, it is important to check if the `ports` used by Mediscreen are not already occupied on your machine, 
this information is available in `docker-compose`: `mediscreen_net`, the `ipv4_adress` of each container and the `networks`.

Setup for docker-compose:

Entry into the UI module: 
`cd mediscreenUI`

Building the .jar file:
`mvn clean install`

Back to the whole project:
`cd..`

Entry into the Info module:
`cd mediscreenInfo`

Building the .jar file:
`mvn clean install`

Back to the whole project:
`cd..`

Entry into the Note module:
`cd mediscreenNote`

Building the .jar file:
`mvn clean install`

Back to the whole project:
`cd..`

Entering the Assess module:
`cd mediscreenCalcul`

Building the .jar file:
`mvn clean install`

Back to the whole project:
`cd..`

And finally `docker-compose up`, for stop use `docker-compose stop`.

## Testing
This app has Unit test written. Once the test has been generated, it can be accessed by following this path: 

`P9-mediscreen\mediscreenUI\target\site\project-reports.html` for the UI module, 

`P9-mediscreen\mediscreenInfo\target\site\project-reports.html` for the Info module, 

`P9-mediscreen\mediscreenNote\target\site\project-reports.html` for the Note module 

and `P9-mediscreen\mediscreenCalcul\target\site\project-reports.html` for the Assess module.

### Test Report

To generate the 4 reports:

Entry into the UI module: 
`cd mediscreenUI`

Test generation:
`mvn clean verify site`

Back to the whole project:
`cd..`

Entry into the Info module:
`cd mediscreenInfo`

Test generation:
`mvn clean verify site`

Back to the whole project:
`cd..`

Entry into the Note module:
`cd mediscreenNote`

Test generation:
`mvn clean verify site`

Back to the whole project:
`cd..`

Entering the Assess module:
`cd mediscreenCalcul`

Test generation:
`mvn clean verify site`

Back to the whole project:
`cd..`
