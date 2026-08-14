# **GenAI Chat Assistant**

A simple **Spring Boot** application that uses the **Google Gemini API** to answer questions related to **Java and backend development**.

## **Features**

- Ask questions through a **REST API**
- Get AI-generated answers using **Gemini**
- Responses include:
  - **Title**
  - **Key points**
  - **Code examples**
- **Swagger API documentation**
- API key managed using **environment variables**

## **Tech Stack**

- **Java**
- **Spring Boot**
- **Spring Web**
- **Google Gemini API**
- **RestClient**
- **Jackson**
- **Lombok**
- **Swagger / OpenAPI**
- **Gradle**

## **API**

### **POST `/api/v1/chat`**

**Request:**

```json
{
  "question": "What is N+1 problem?"
}
```

**Response:**

```json
{
  "title": "N+1 Problem",
  "points": [
    "N+1 happens when one query is used to fetch the main records.",
    "Additional queries are then executed for each related record.",
    "This can affect application performance."
  ],
  "code": "..."
}
```

## **Configuration**

The **Gemini API key is not stored in the repository**.

In `application.properties`:

```properties
gemini.api.url=${GEMINI_API_URL}
gemini.api.key=${GEMINI_API_KEY}
```

Set the values as **environment variables** before running the application.

## **Run the Application**

```bash
./gradlew bootRun
```

Application:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```
