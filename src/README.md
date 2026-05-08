# TrainX - Railway Reservation System

TrainX is a comprehensive console-based Railway Reservation System built with Java. It provides a seamless experience for users to search for trains, book tickets, and manage their bookings, while allowing administrators to manage train details, schedules, and user records.

## 🚀 Features

### For Users
- **Search Trains:** Find trains by specifying source and destination.
- **View All Trains:** Browse through the list of available trains and their details.
- **Book Tickets:** Reserve seats on specific train schedules.
- **My Bookings:** View booking history and ticket details.
- **Cancel Booking:** Effortlessly cancel existing reservations.
- **User Authentication:** Secure sign-up and sign-in functionality.

### For Administrators
- **Train Management:** Add, update, view, and delete train information.
- **Schedule Management:** Add and view train schedules.
- **User Management:** Monitor all registered users in the system.
- **Admin Dashboard:** A centralized interface for managing system-wide data.

## 🛠️ Tech Stack
- **Language:** Java 17+
- **Framework:** Spring Boot 3.2.5 (Configured for JPA & H2)
- **Build Tool:** Maven
- **Database:** H2 (In-memory database support configured)
- **Architecture:** Model-View-Controller (MVC) for Console

## 📁 Project Structure

```text
src/main/java/com/zsgs/trainx/
├── controller/     # Logic for handling user requests
├── data/           # Data Transfer Objects (DTOs) and Repositories
├── features/       # Modules for specific features (Admin, Booking, etc.)
│   ├── admin/      # Admin specific Model and View
│   ├── booking/    # Ticket booking logic
│   ├── cancel/     # Cancellation logic
│   ├── home/       # Main user dashboard
│   ├── payment/    # Payment processing (simulation)
│   ├── signin/     # User login logic
│   └── signup/     # User registration logic
├── service/        # Business logic services
├── util/           # Helper classes and data initializers
└── ConsoleApplication.java # Entry point of the application
```

## ⚙️ Prerequisites
- [Java JDK 17](https://www.oracle.com/java/technologies/downloads/) or higher
- [Maven](https://maven.apache.org/download.cgi)

## 🏃 How to Run

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd trainx
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn compile exec:java -Dexec.mainClass="com.zsgs.trainx.ConsoleApplication"
   ```

## 🔑 Default Credentials

- **Admin:**
  - Email: `admin@trainx.com`
  - Password: `admin123`
- **User:**
  - Email: `alice@example.com`
  - Password: `alice123`

---
Built with ❤️ for TrainX enthusiasts.
