# AI Gym Recommendation System (Microservices Architecture)

This project is a full-stack AI-based gym recommendation system built using
Spring Boot microservices and a modern frontend application.

The system provides personalized workout recommendations based on user inputs
such as fitness goals, activity data, and user profile.

---

## 🧩 Architecture Overview

The application follows a **microservices architecture** and consists of:

- Eureka Server – Service discovery
- API Gateway – Central entry point for all requests
- Config Server – Centralized configuration management
- User Service – Manages user data and authentication
- Activity Service – Handles workout/activity data
- AI Service – Generates workout recommendations using AI logic
- Frontend – User interface for interacting with the system

---

## ⚙️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Cloud (Eureka, Config Server, Gateway)
- REST APIs
- MongoDB
- WebClient
- Microservices Architecture

### Frontend
- React (Vite)
- JavaScript
- HTML / CSS

---

## 🤖 AI Recommendation Logic

The AI service analyzes user activity data and fitness goals to generate
personalized workout recommendations.

The recommendation logic is implemented using rule-based decision logic
and AI-driven service integration.

---

## 🚀 Features

- Microservices-based backend architecture
- Centralized configuration using Config Server
- Service discovery using Eureka
- API Gateway routing
- AI-based workout recommendations
- Secure and scalable backend services
- Modern frontend UI

---

## ▶️ How to Run Locally

### Prerequisites
- Java 17+
- Maven
- Node.js
- MongoDB

### Backend
1. Start Eureka Server
2. Start Config Server
3. Start Gateway
4. Start User, Activity, and AI services

### Frontend
```bash
cd fitness-frontend
npm install
npm run dev

