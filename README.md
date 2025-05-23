# Todo App (Spring Boot + Gradle)

## Requirements
- Java 17 or higher
- Gradle (or use the Gradle Wrapper)

## How to Build and Run

1. Clone or download this repository.
2. Open a terminal in the project root directory.
3. Build the project:
   ```
   gradle build
   ```
4. Run the application:
   ```
   gradle bootRun
   ```

The API will be available at `http://localhost:8080/api/todos`.

## API Endpoints
- `GET /api/todos` - List all todos
- `GET /api/todos/{id}` - Get a todo by ID
- `POST /api/todos` - Create a new todo
- `PUT /api/todos/{id}` - Update a todo
- `DELETE /api/todos/{id}` - Delete a todo

## H2 Database Console
Access the H2 console at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:todoappdb`.