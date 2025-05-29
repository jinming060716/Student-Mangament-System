# Student Management System

This is my first personal project: a Java-based Student Management System running on the command line (CLI).

## Overview

The Student Management System is a simple, CLI-based application that allows users to manage student records efficiently. All data is stored in memory—no text files or databases are used for data persistence—so all data will be lost when the program exits. This project is intended as a learning exercise in Java programming and basic object-oriented design.

## Features

- Add new student records
- View all students
- Search for students by ID or name
- Update student information
- Delete student records
- Simple, text-based menu interface
- **Note:** All data is stored in memory (no file or database storage)

## Technologies Used

- Java (Standard Edition)
- No external libraries required
- Command-Line Interface (CLI)

## Getting Started

### Prerequisites

- Java JDK 8 or higher installed

### How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/jinming060716/Student-Management-System.git
   cd Student-Management-System
   ```

2. Compile the Java files:
   ```bash
   javac *.java
   ```

3. Run the application:
   ```bash
   java Main
   ```
   *(Replace `Main` with your main class name if different)*

## Project Structure

- `Main.java`: Entry point of the application
- `Student.java`: Defines the Student object and its properties
- `StudentManager.java`: Handles core logic for managing students
- Other utility or helper classes as needed

## Notes

- **Data is NOT saved to any file.** All information is lost when the program is closed.
- This project is for learning purposes and demonstrates basic Java programming.

## Contributing

This is a personal learning project. Contributions are welcome for educational purposes!

---

*Created by jinming060716 as a first Java project.*