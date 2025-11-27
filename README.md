# Student-API
Student Management API

# Student Management CRUD API

## How to run

./mvnw spring-boot:run

API base URL: http://localhost:8080/api/students

H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:testdb)

## Endpoints

POST   /api/students → create (201 + Location header)
GET    /api/students/{id}
GET    /api/students ?branch=CSE&yop=2025&page=0&size=20 → with pagination + filters
PUT    /api/students/{id} → full update (requires all fields)
DELETE /api/students/{id} → soft delete

## Assumptions

- Soft delete using active flag + @Where clause
- PUT requires all fields (full replace)
- Email is unique
- H2 in-memory database
- Validation errors return 400 with field→message map
- Duplicate email → 409 Conflict
- Not found → 404
- Pagination sorted by id descending
