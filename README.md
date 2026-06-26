# EasyShop E-Commerce Application

## Overview

EasyShop is a full-stack e-commerce web application developed using Spring Boot, Java, MySQL, HTML, CSS, and JavaScript. The application allows users to browse products, filter items, manage a shopping cart, update their profile, and securely place orders using JWT authentication.

This project was completed as a capstone project to demonstrate full-stack software development skills, including REST API development, database integration, authentication, and frontend-backend communication.

## Features

- User authentication using JWT
- Secure login and logout functionality
- Browse all available products
- Filter products by category, color, and price
- View detailed product information
- Add products to a shopping cart
- View and clear the shopping cart
- Checkout and place customer orders
- View and update user profile information
- RESTful API built with Spring Boot
- MySQL database integration using Spring Data JPA
- Responsive frontend built with HTML, CSS, Bootstrap, and JavaScript
- Frontend communication with the backend using Axios

  ## Technologies Used

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JSON Web Tokens)
- Maven
- MySQL

### Frontend
- HTML5
- CSS3
- Bootstrap
- JavaScript (ES6)
- Axios
- Mustache.js

### Development Tools
- IntelliJ IDEA
- Git
- GitHub
- MySQL Workbench
- Insomnia
- Firefox Developer Tools

  ## Project Structure

### Backend

The backend follows a layered architecture using Spring Boot:

```
src
├── controllers      # REST API endpoints
├── models           # Entity classes
├── repositories     # Database access using Spring Data JPA
├── services         # Business logic
├── security         # JWT authentication and Spring Security configuration
├── dto              # Data Transfer Objects
└── ECommerceApplication.java
```

### Frontend

The frontend is built using HTML, CSS, JavaScript, and Bootstrap.

```
EasyShopFrontend
├── css
├── images
├── js
│   ├── services
│   ├── lib
│   ├── application.js
│   ├── config.js
│   └── template-builder.js
├── templates
└── index.html
```

## Database

EasyShop uses a MySQL relational database to store application data. Spring Data JPA and Hibernate are used to map Java entities to database tables and perform CRUD operations.

### Main Database Tables

| Table | Description |
|-------|-------------|
| `users` | Stores user account information and login credentials |
| `profiles` | Stores customer profile information such as name, address, phone number, and email |
| `categories` | Stores product categories |
| `products` | Stores product information including name, description, price, color, stock, and image |
| `shopping_cart` | Stores products currently added to a user's shopping cart |
| `orders` | Stores customer orders after checkout |
| `order_line_items` | Stores each product included in an order |


## REST API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/login` | Authenticate a user and return a JWT token |

---

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | Retrieve all products |
| GET | `/products/{id}` | Retrieve a product by ID |
| POST | `/products` | Create a new product *(Admin)* |
| PUT | `/products/{id}` | Update a product *(Admin)* |
| DELETE | `/products/{id}` | Delete a product *(Admin)* |

---

### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/categories` | Retrieve all product categories |

---

### Shopping Cart

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/cart` | View the current user's shopping cart |
| POST | `/cart/products/{id}` | Add a product to the shopping cart |
| DELETE | `/cart` | Clear the shopping cart |

---

### User Profile

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/profile` | Retrieve the logged-in user's profile |
| PUT | `/profile` | Update the logged-in user's profile |

---

### Orders

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/orders` | Checkout and create a new order |


## Installation & Running the Application

### Prerequisites

Before running the application, make sure you have the following installed:

- Java 17 or later
- Maven
- MySQL Server
- IntelliJ IDEA (recommended)
- Git

---

### Clone the Repositories

#### Backend

```bash
<img width="1580" height="137" alt="image" src="https://github.com/user-attachments/assets/3194c006-a917-4b7c-a480-c01f5bc21d36" />

```

#### Frontend

```bash
<img width="1147" height="135" alt="image" src="https://github.com/user-attachments/assets/9f90af61-9b48-4b08-9039-cbc4abf3c14a" />

```

---

### Configure the Database

1. Create a MySQL database named **easyshop**.
2. Import the provided SQL script.
3. Update the database connection in:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/easyshop
spring.datasource.username=
spring.datasource.password=
```

---

### Run the Backend

Open the backend project in IntelliJ and run:

```
ECommerceApplication.java
<img width="1791" height="962" alt="image" src="https://github.com/user-attachments/assets/89bb7701-738a-4d05-baed-19d1c7b5d5a6" />

```

The backend will start on:

```
http://localhost:8080
```

---

### Run the Frontend

Open the frontend project and launch:

```
index.html
<img width="1918" height="153" alt="image" src="https://github.com/user-attachments/assets/276b7faf-bb50-4c00-a3c4-5069def0bce6" />

```

or use Live Server.

The frontend communicates with the backend through:

```
http://localhost:8080
```

## Screenshots

### Home Page

Displays all available products with category, color, and price filters.

<img width="1919" height="861" alt="image" src="https://github.com/user-attachments/assets/bc1915fe-7c90-435a-bece-aab047daa147" />


---

### Login

Secure user authentication using JWT.

<img width="1918" height="885" alt="image" src="https://github.com/user-attachments/assets/2514ae8a-34fc-41e8-a2f4-704fcb3b5d8c" />


---

### Product Details

Displays detailed information about products.

<img width="1919" height="838" alt="image" src="https://github.com/user-attachments/assets/1f667d95-ab3a-44ae-90de-2304721175eb" />


---

### Shopping Cart

Users can add products, view their shopping cart, and clear items.
<img width="1919" height="824" alt="image" src="https://github.com/user-attachments/assets/4decaeaf-d7fb-4436-8241-96dfb4a0a07f" />

---

### User Profile

Users can view and update their profile information.

<img width="1919" height="913" alt="image" src="https://github.com/user-attachments/assets/e7d060af-4446-4da2-9fa1-931f81059106" />


---

### Checkout

Customers can place an order directly from the shopping cart.

<img width="1918" height="916" alt="image" src="https://github.com/user-attachments/assets/54d2195c-05bc-4ad0-a918-a19f169a945d" />

## Conclusion

The EasyShop E-Commerce Application demonstrates the development of a complete full-stack web application using modern software engineering principles and technologies. Throughout this project, RESTful APIs were built with Spring Boot, secure authentication was implemented using JWT, data was managed with MySQL and Spring Data JPA, and a responsive frontend was developed using HTML, CSS, Bootstrap, and JavaScript.

This project provided hands-on experience with backend development, frontend integration, database management, API design, authentication, and full-stack application architecture. It reflects the ability to design, develop, test, and deploy a real-world e-commerce application while following industry best practices.

The EasyShop project represents an important milestone in my journey as a software engineer and serves as a strong demonstration of my Java, Spring Boot, SQL, and full-stack web development skills.

## Author

**Moubarak Congacou**

- GitHub: https://github.com/yu26s9-mouba1
- LinkedIn:https://www.linkedin.com/in/moubarak-congacou-24a467314/
