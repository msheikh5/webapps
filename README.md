# WebApps Master (Java) — Sockets, Servlets/JSP, and Spring Boot

A collection of **Java web and networking demos** that implement a simple **Student Grading System** using three different approaches:

1. **Java Sockets** (client/server, multi-threaded server)
2. **Java Servlets + JSP** (WAR deployed on Tomcat)
3. **Spring Boot MVC** (Controller + views)

All implementations connect to a **MySQL** database named `school` and support:
- **Login** (student id + password)
- **View grades** per course
- **Course statistics** (count, min, max, avg) for a selected course

> This repository is mainly for learning and practicing backend fundamentals: networking, web apps, JDBC, and basic SQL.

---

## Repository Layout

```
webapps-master/
├── sockets/
│   ├── Server students grading system/   # multi-threaded socket server (port 6666)
│   └── Client system grading/            # socket client
├── servlets/                             # Servlet/JSP webapp (WAR)
└── springboot/                            # Spring Boot MVC app
```

---

## Prerequisites

- **Java 8+**
- **MySQL 8+** (or MySQL in Docker)
- **Maven** (needed for `servlets/` and `springboot/`)
- Optional: **Tomcat 9+** (to run the WAR from `servlets/`)
- Optional: IntelliJ IDEA (easiest for the `sockets/` projects)

Check:
```bash
java -version
mvn -version
mysql --version
```

---

## Database Setup (MySQL)

### Option A — Run MySQL with Docker (recommended)
```bash
docker run --name school-mysql \
  -e MYSQL_ROOT_PASSWORD=YOUR_PASSWORD \
  -e MYSQL_DATABASE=school \
  -p 3306:3306 \
  -d mysql:8
```

Connect:
```bash
docker exec -it school-mysql mysql -uroot -p
```

### Option B — Use local MySQL
Create a database called `school`, then run the SQL below.

---

## Required Schema + Sample Data

Run this in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS school;
USE school;

CREATE TABLE IF NOT EXISTS student (
  id INT PRIMARY KEY,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS course (
  courseId INT PRIMARY KEY,
  courseName VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS marks (
  id INT NOT NULL,
  courseId INT NOT NULL,
  grade INT NOT NULL,
  PRIMARY KEY (id, courseId),
  FOREIGN KEY (id) REFERENCES student(id),
  FOREIGN KEY (courseId) REFERENCES course(courseId)
);

-- Sample data
INSERT INTO student (id, password) VALUES
(1, '1234'),
(2, '1234')
ON DUPLICATE KEY UPDATE password=VALUES(password);

INSERT INTO course (courseId, courseName) VALUES
(10, 'Databases'),
(20, 'Java'),
(30, 'Networks')
ON DUPLICATE KEY UPDATE courseName=VALUES(courseName);

INSERT INTO marks (id, courseId, grade) VALUES
(1, 10, 95),
(1, 20, 88),
(2, 10, 76),
(2, 30, 90)
ON DUPLICATE KEY UPDATE grade=VALUES(grade);
```

---

## IMPORTANT: Configure the DB Connection

The code uses JDBC `DriverManager.getConnection(...)` in `StudentRepository` classes.

In multiple modules you will find something similar to:

```java
DriverManager.getConnection(
  "jdbc:mysql://host.docker.internal:3306/school",
  "root",
  "Mohammad2001"
);
```

### What you should do
- Replace **password** with your own.
- If you run the app on your host machine, change `host.docker.internal` to:
  - `localhost` (common), or
  - `127.0.0.1`

Example:
```java
jdbc:mysql://localhost:3306/school
```

> ⚠️ Security note: credentials are hard-coded because this is a learning project. In production, use environment variables or config files.

---

# 1) Run the Spring Boot App (`springboot/`)

### Build + Run
```bash
cd springboot
mvn clean package
mvn spring-boot:run
```

Then open:
- `http://localhost:8080/` or `http://localhost:8080/login` (depending on routing)

### What it does
- `LoginController` handles `/login`
- `MarksController` displays grades/statistics
- `StudentRepository` does JDBC queries on MySQL

---

# 2) Run the Servlet/JSP WebApp (`servlets/`)

This is a **WAR** project (packaging = `war`) meant to be deployed on **Tomcat**.

### Build the WAR
```bash
cd servlets
mvn clean package
```

You should get a WAR file in:
- `servlets/target/demo3.war` (name may vary)

### Deploy to Tomcat
Copy the WAR into Tomcat’s `webapps/` folder:
```bash
cp target/*.war /path/to/tomcat/webapps/
```

Start Tomcat, then open (example):
- `http://localhost:8080/demo3/login`

> If your context path is different, it will match your WAR name.

---

# 3) Run the Socket Client/Server (`sockets/`)

The sockets implementation is split into two projects:

- **Server students grading system**: opens a server socket on port **6666**
- **Client system grading**: connects to `localhost:6666`

## Start the server
In IntelliJ:
- Open `sockets/Server students grading system`
- Run: `com.company.Server`

Or from terminal (if you configured classpath + MySQL driver):
- Compile and run normally.

## Start the client
In IntelliJ:
- Open `sockets/Client system grading`
- Run: `com.company.Client`

### Expected flow
- Client connects to server
- Login prompt (id + password)
- Menu:
  - show marks
  - course statistics
  - exit

---

## Troubleshooting

### “Communications link failure” / “Connection refused”
- MySQL is not running
- Wrong hostname (use `localhost` if app runs on host)
- Wrong password

### Port 6666 already in use (Sockets)
Change port in:
- `ServerSocket serverSocket = new ServerSocket(6666);`
- client `new Socket("localhost", 6666);`

### WAR doesn’t load / JSP errors
- Make sure you are using a compatible Tomcat (Tomcat 9 is usually safe for `javax.servlet.*`)
- Ensure MySQL driver is included (Maven dependency exists in `pom.xml`)

