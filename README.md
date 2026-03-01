# 📚 Library Management System – Data Structures Project

This project is a console-based Library Management System developed as part of a Data Structures course.

The system simulates the management of a university library and demonstrates the practical use of custom-built data structures implemented from scratch.

---

## 🚀 Project Overview

The system allows registered users (Students, Employees, and Librarians) to log in and interact with the library system according to their roles.

### 👤 User Roles
- Student
- Employee
- Librarian

Each role has different permissions and responsibilities within the system.

---

## 🔑 Main Features

- Secure login system (ID & Password)
- Book search by name or ISBN
- Borrowing system with approval logic
- Borrow request queue for students with grades below 85%
- Borrowing history tracking for both users and books
- Role-based menu navigation
- Book availability management

---

## 🧠 Data Structures Used

All data structures were implemented manually without using Java built-in collections.

### 1️⃣ MyStack (LIFO)
Used to store:
- Book borrowing history (who borrowed the book)
- User borrowing history (which books the user borrowed)

### 2️⃣ MyQueue (FIFO)
Used to manage:
- Borrow requests that require librarian approval

### 3️⃣ MyArrayBag
Used to store:
- Registered users

### 4️⃣ MyLinkedBag
Used to store:
- Books in the library

---

## 🏗 System Architecture

The project follows Object-Oriented Programming principles:

- Encapsulation for data protection
- Clear separation between:
  - Data Structures
  - Models (Book, User, BorrowRequest)
  - System Logic (LibrarySystem, Menu)

The LibrarySystem class acts as a central static data store for books, users, and borrowing requests.

## 📌 Borrowing Logic

- Employees can borrow books directly.
- Students with grades below 85% must submit a borrow request.
- Librarians can approve or reject pending requests.
- When approved:
  - The book status is updated.
  - The borrowing history is recorded for both the book and the user.

## 🎯 Educational Purpose

This project demonstrates:

- Implementation of Stack, Queue, and Bag data structures
- Proper selection of data structures based on problem requirements
- Real-world simulation of data structure usage
- Understanding of algorithmic complexity and data flow

---

## 👩‍💻 Developed By
Data Structures Course Project  
Faculty of IT – Computer Science Department
