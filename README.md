# Easy URL Shortener

## Project Overview
This project is a full-stack URL Shortener application similar to Bitly. Users can shorten long URLs, view recent URLs, track click counts, and view analytics.

## Tech Stack

Frontend:
React
TypeScript
Vite
Axios
Recharts

Backend:
Java
Spring Boot
Spring Data JPA
H2 Database

## Features

Create short URL
View recent URLs
Redirect short URL
Click tracking
URL analytics
Statistics charts
Responsive UI

## Prerequisites

Node.js installed
Java 17+
Maven installed
Git installed

## Backend Setup

Navigate to backend:

cd backend

Run:

mvn spring-boot:run

Backend runs on:

http://localhost:8080

## Frontend Setup

Navigate to frontend:

cd frontend

Install dependencies:

npm install

Run project:

npm run dev

Frontend runs on:

http://localhost:5173

## Seed Data

Test URLs can be created using the UI input field.

## Testing

Backend tested using:
Postman

Frontend tested using:
Browser testing

## Screenshots

### Main Page
![Main](screenshots/main.png)

### URL Creation
![Create](screenshots/create.png)

### Analytics
![Analytics](screenshots/analytics.png)

### Statistics
![Statistics](screenshots/statistics.png)

## Assumptions

Simple click analytics used.
Click history optional.
In-memory database used.


