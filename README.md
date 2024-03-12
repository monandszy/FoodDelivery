# FoodDelivery

A simple practice project

#### How to use:
- Application starts on port localhost:8087/code
- To get a role and use features, register a new account, login as admin, assign a role

### Features as Admin:

- CRUD Accounts - assign roles
- default admin account: Username: "admin" Password: "admin"

### Features as Seller:

- CRUD Restaurants - Address is assigned based on your ip
- CRUD Menus
- CRUD MenuPositions - Add a picture to each one
- See Complete and Incomplete Orders - Complete Orders

### Features as Client:

- Find restaurants nearest to you based on ip and restaurant's available delivery range
- Read Menus
- Read MenuPositions - Create Order
- Read Your Orders and Their Status - Cancel Order within 20 minutes

### Rest API Features:

- Create a full restaurant in one command
- CRUD Orders (as a Client)

#### Component Diagram

<img src="/diagrams/component.svg" alt="image" height="500">

#### ERD Diagram

<img src="/diagrams/erd.png" alt="image" height="500">

#### Jacoco Test Coverage

<img src="/diagrams/jacoco.png" alt="image">