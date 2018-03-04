# Rent Your Dreams - car rental application

Responsive web app for supercar rental.

### Technologies:

Spring Boot / Sring Security / Hibernate / REST API / PostgreSQL / Bootstrap

Build tool: Maven

### Application is up and running in cloud:

http://rentyourdreams.herokuapp.com/

(App is sometimes sleepy, so give it 30 or 40 secs for wake up :)


## Features:

User is able to:

* Register new account
* Login/logout
* See his profile (if he has rented car - it is displayed there)
* Browse available cars (by type or by popularity)
* Send request for a rental

Admin is able to:

* Add new car
* Update already existing car
* Delete car
* See all cars with details
* Rent a car to user which requested for a rent(*)
* Return a car after and make it available again
* Search for a car
* See all user accounts

(*) If there is request for a rent - admin will see notification with number of waiting requests. Also in list of all cars in admin panel, cars waiting for rent are highlighted.



## How to run it locally:

You will need Maven installed locally and PostgreSQL database.

1. Download server side and client side of application

Server:
```
https://github.com/rafalciesla/rent-your-dreams-car-rental-server/archive/master.zip
```

Client:
```
https://github.com/rafalciesla/rent-your-dreams-car-rental-client/archive/master.zip
```

2. Extract project folders

3. Enter the server side app folder and go to "src/main/resources" and edit application.properties file.

4. Set datasource url, username and password for your specific database (*).

5. Repeat step 3 and 4 for client side app.

6. Open command line console, enter to server side app folder and enter line:

```
mvnw clean install
```

7. Repeat step 6 for client side app.

8. Enter your SQL console and run following queries:

```
CREATE TABLE users
(
    email text NOT NULL,
    password text NOT NULL,
    enabled boolean NOT NULL,
    PRIMARY KEY (email)
)
WITH (
    OIDS = FALSE
);
```

and

```
CREATE TABLE user_roles
(
    id serial NOT NULL,
    email text NOT NULL,
    role text NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);
```

9. Now go back to your system command line console, go to server side application folder and run it entering:

```
java -jar target/rent-your-dreams-car-rental-1.0-RELEASE.jar
```

10. And similarly for client side app:

```
java -jar target/rent-your-dreams-car-rental-client-1.0-RELEASE.jar
```

11. Now just open your browser and go to:

```
localhost:8383
```

12. After you register new account you can grant yourself admin privileges by SQL query below:

```
UPDATE user_roles SET role='ROLE_ADMIN' WHERE email='your@email.com'
```

This is it, enjoy! :)


(*) IMPORTANT!!! If you want to run this application again and not lose your data from database, after first start you have to edit application.properties file in server side of application and change this line:

```
spring.jpa.hibernate.ddl-auto=create-drop
```

to:

```
spring.jpa.hibernate.ddl-auto=validate
```
