# CoderHack

## Overview
CoderHack is a RESTful API service built using Spring Boot that manages a leaderboard for a coding platform. The application allows for user registration, score updates, and badge assignment based on performance. Users can be created, retrieved, updated, and deleted via a set of API endpoints.

## Features
- **User Registration:** Create new users with an initial score of 0 and no badges.
- **Score Management:** Update user scores with validation for allowed values (0-100).
- **Badge Assignment:** Automatically assign badges based on user scores:
  - `Code Ninja` for scores greater than 0.
  - `Code Champ` for scores greater than or equal to 30.
  - `Code Master` for scores greater than or equal to 60.
- **User Retrieval:** Fetch user details by ID or get a list of all users sorted by score.
- **User Deletion:** Remove users from the leaderboard.

## Technologies Used
- **Java:** Programming language used for building the application.
- **Spring Boot:** Framework for building the RESTful API.
- **MongoDB:** NoSQL database for data persistence.
- **JUnit:** Testing framework for unit tests.
- **Mockito:** Library for mocking objects in tests.

## Getting Started

### Prerequisites
Make sure you have the following installed on your local machine:
- **JDK 21**
- **Gradle** (for dependency management)
- **MongoDB** (for data storage)

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Deepanshukumar2335/CoderHack.git
   cd coderhack

### API ENDPOINTS ###
## User Management ##
**Create User** : POST /users
**Get All Users** :GET /users
**Get User by ID** : GET /users/{userId}
**Update User Score**: PUT /users/{userId}
**Delete User** : DELETE /users/{userId}

## Unit Tests ##
Unit tests are implemented for both the service and controller layers using JUnit and Mockito to ensure functionality and reliability. Tests cover:
**User creation and retrieval**
**Score updates and badge assignment**
**User deletion and error handling**

Author
Deepanshu Kumar
deepanshukumar2335@gmail.com
https://github.com/Deepanshukumar2335/CoderHack

