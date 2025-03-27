# Digital Library Book Management System

## 📌 Overview
This is a **Spring Boot-based Library Management System** that allows librarians to efficiently add, update, search, and remove books while maintaining their availability status. The application uses an **in-memory collection** instead of a database.

## 🚀 Features
- **Add a Book** (Book ID, Title, Author, Genre, Availability Status)
- **View All Books**
- **Search Books** by ID or Title
- **Update Book Details** (Title, Author, Availability Status)
- **Delete a Book**
- **Exit System** via API

## 🛠️ Tech Stack
- **Java 22**
- **Spring Boot 3.4.4**
- **Spring MVC**
- **Validation API** (for input validation)

## 🔧 Setup & Installation

### **1️⃣ Prerequisites**
Ensure you have installed:
- **JDK 22** ([Download](https://jdk.java.net/))
- **Maven** ([Download](https://maven.apache.org/download.cgi))
- **Postman** (for API testing) *(Optional)*

### **2️⃣ Clone the Repository**
```sh
git clone https://github.com/amit130391/LibraryManagementSystem.git
cd library-management
```

### **3️⃣ Build the Project**
```sh
mvn clean install
```

### **4️⃣ Run the Application**
```sh
mvn spring-boot:run
```
The server starts at **http://localhost:8080**

## 📌 API Endpoints

### **1️⃣ Add a Book** 📚
**POST** `/add`
#### Request Body (JSON)
```json
{
  "bookId": "101",
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "genre": "Technology",
  "availabilityStatus": "Available"
}
```

### **2️⃣ View All Books**
**GET** `/books`

### **3️⃣ Search Book by ID or Title** 🔍
**GET** `/search?query=101`

### **4️⃣ Update a Book** ✏️
**PUT** `/update/{bookId}`
#### Request Body (JSON)
```json
{
  "title": "Spring Boot Advanced",
  "author": "Craig Walls",
  "genre": "Technology",
  "availabilityStatus": "Checked Out"
}
```

### **5️⃣ Delete a Book** ❌
**DELETE** `/delete/{bookId}`

### **6️⃣ Exit System** 🛑
**GET** `/exit`
(This will shut down the application.)

## ⚠️ Constraints
- **Book ID must be unique.**
- **Title & Author must not be empty.**
- **Availability Status must be either `Available` or `Checked Out`.**

