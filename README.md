# 📚 Library Management System

![Java](https://img.shields.io/badge/Java-21-blue.svg) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg) ![Maven](https://img.shields.io/badge/Build-Maven-orange.svg)

A full-featured web application for managing a library's books, members, and transactions. Originally a Java console application, this project was refactored into a modern web service using the Spring Boot framework, featuring role-based security, a persistent database, and a dynamic web interface.

---

## ✨ Key Features

* **Role-Based Security:** Differentiated user roles with distinct permissions:
    * **ADMIN:** Full access to all management features, including adding, editing, and deleting books and members.
    * **LIBRARIAN:** Can view data and manage core library operations like borrowing and returning books.
* **Book Management:** Full CRUD (Create, Read, Update, Delete) functionality for the library's book catalog.
* **Member Management:** Full CRUD (Create, Read, Update, Delete) functionality for managing library member accounts.
* **Transaction Management:** Complete lifecycle for library transactions:
    * **Borrowing:** Assign a book to a member, updating book availability.
    * **Returning:** Process returned books and update records.
* **Persistent Database:** Utilizes a file-based H2 database to ensure all data (books, members, transactions) is saved permanently across application restarts.
* **Web-Based Database Console:** Includes an embedded H2 console for direct database viewing and management during development.
* **Dynamic Frontend:** A user-friendly web interface built with Thymeleaf for server-side rendering of all views.

---

## 🛠️ Tech Stack

* **Backend:** Java 21, Spring Boot
* **Security:** Spring Security
* **Data Persistence:** Spring Data JPA, Hibernate
* **Database:** H2 Database Engine
* **Frontend:** Thymeleaf, HTML
* **Build & Dependency Management:** Apache Maven

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java Development Kit (JDK) 21 or newer installed.
* Apache Maven installed and configured.
* Git installed on your machine.

### Installation & Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Prashanth291/LibraryManagementSystem-Java](https://github.com/Prashanth291/LibraryManagementSystem-Java)
    ```

2.  **Navigate to the project directory:**
    ```bash
    cd LibraryManagementSystem-Java
    ```

3.  **Build the project using the Maven wrapper:**
    * On Windows:
        ```bash
        .\mvnw.cmd clean install
        ```
    * On macOS/Linux:
        ```bash
        ./mvnw clean install
        ```

4.  **Run the application:**
    ```bash
    java -jar target/LibrarySystem-0.0.1-SNAPSHOT.jar
    ```
    Alternatively, you can import the project into your favorite IDE (like NetBeans, IntelliJ, or Eclipse) and run the `main` method in the `LibrarySystemApplication.java` file.

---

## 💻 Usage

Once the application is running, you can access it through your web browser.

### Application URLs
* **Login Page:** `http://localhost:8083/login`
* **Home Dashboard (after login):** `http://localhost:8083/`

*(Note: The port may be different if you have changed it in `application.properties`)*

### Default Credentials
The application is pre-configured with two user roles for demonstration:

* **Administrator:**
    * **Username:** `admin`
    * **Password:** `admin123`
* **Librarian:**
    * **Username:** `librarian`
    * **Password:** `lib123`

### H2 Database Console
You can directly view and query the live database through the built-in H2 web console.

* **URL:** `http://localhost:8083/h2-console`
* **JDBC URL:** `jdbc:h2:file:./librarydb` (You must enter this value)
* **Username:** `sa`
* **Password:** (leave blank)

---

## 📁 Project Structure

The project follows a standard Spring Boot package structure to ensure a clean separation of concerns:
* `config`: Contains security and other application-level configurations.
* `controllers`: Handles incoming web requests and maps them to the appropriate views.
* `models`: Defines the core data structures (JPA Entities) like `Book`, `Member`, etc.
* `repositories`: Spring Data JPA interfaces for database operations.
* `services`: Contains the business logic of the application.
* `resources/templates`: Contains all the Thymeleaf HTML files for the frontend.
* `resources/static`: For static assets like CSS and JavaScript files.

---

## 📄 License

This project is licensed under the MIT License.
