# Student and Course Management System

A Student course management system built by using Spring Boot JPA, featuring CRUD Operations and REST APIs.

## Features

- Add,Fetch, Update or Delete student.
- Add, Fetch, Update and Delete course.
- Enroll Student in a course.
- Mapping of DTO and entity for clean data transfer.
- Rest APIs with proper HTTP status codes.
- Integration Testing done by using MockMvc.
- Uses postgreSQL database to store information.
- For testing, in-memory H2 database is used.
- Exception handling
- Uses Docker-Desktop to run a postgreSQL database.
- Uses postman to test and verify API endpoints.

## How to Run 🚀

- Clone the repository.
- Install **docker-desktop** and **postman**, if not installed (preferred).
- Open the docker-desktop app.
- Open the repository and run the **docker-compose.yml** file using **docker-compose up** command in the terminal.
- After the docker-desktop app is set up, run the main application and verify that it is connected to the database.
- After a successfull connection, open **postman** to test APIs endpoints.
- Try the integration Testing as well to ensure all endpoints are working properly. 
- To check the database, use **DBeaver**. Connect to postgreSQL database.
- For username and password, check **application properties** file.

## Technologies 
- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- H2/ postgreSQL database
- Docker-Desktop
- Postman
- MockMVC
- Maven for Dependency management
- Spring Web
- Lombok
- ModelMapper for DTO/entity Mapping

## Notes

- This project is for **learning purposes**. You can add features such as Spring boot security, add different roles and permissions and front-end integration.

## Developed by: Sahil Bdr. Devkota
## Github: (https://github.com/SahilDevkota)
