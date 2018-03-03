# rent-your-dreams-car-rental-client

Web application for supercar rental.

### Technologies:

Spring Boot / Sring Security / Hibernate / REST API / PostgreSQL / Bootstrap

Build tool: Maven

App is responsive

### Application is up and running in cloud:

http://rentyourdreams.herokuapp.com/

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
