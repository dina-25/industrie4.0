# Interaction-API

The REST-API is based on the [Spark Framework] (http://sparkjava.com/). For an introduction on how to use this framework visit the [Getting-Started Page] (http://sparkjava.com/documentation#getting-started).
For persisting the data we use the [H2 Database Engine] (http://www.h2database.com) as a lightweight database with good support for Java.

#### Starting Development

To start developing with this project clone the repository and import it as a maven project. Next resolve the dependencies and run the Main-Class of the Java-Project. The current REST-Endpoints will be available under:

Users (Listing all or just one User and creating an User):
* GET localhost/users
* GET localhost/users/:id
* POST localhost/users