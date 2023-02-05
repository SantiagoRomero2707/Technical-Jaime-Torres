# TECNICAL INTERVIEW Jaime Torres

This is a RESTful service built using Java with Spring Boot framework and connected with MySQL WorkBench. It provides a simple API for response an artist and museums collections with querys.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- [.NET Core SDK](https://dotnet.microsoft.com/download)
- A text editor or IDE of your choice (e.g. Visual Studio, Visual Studio Code)
- Install SQL Server 2019 and SQL Server Management Studio for use a GUI.
- Install of extension of Swagger for .Net Core

### Installing

1. Clone or download the repository to your local machine.
   git clone https://github.com/SantiagoRomero2707/Tecnical-MediSmart.git
2. Open the project in visual studio and execute the project.
5. The service should now be running at `http://localhost:7190/swagger/index.html` and you can use of documentation for testing of service. 

### Endpoints

- `GET /api/User`: Retrieves a list of all users.
- `GET /api/User/{id}`: Retrieves the details of a specific user by ID.
- `POST /api/User`: Creates a new user.
- `PUT /api/User/{id}`: Updates an existing user.
- `DELETE /api/User/{id}`: Deletes a user.

### Example usage

You can test the endpoints using a tool such as [Postman](https://www.postman.com/) or if you want with of client of swagger.

- Retrieve a list of resources: `GET http://localhost:7190/api/User`
- Retrieve a specific resource: `GET http://localhost:7190/api/User/{id}`
- Create a new resource: `POST http://localhost:7190/api/User` (user` with a JSON payload in the request body)
- Update an existing resource: `PUT http://localhost:7190/api/User/{id}` with a JSON payload in the request body
- Delete a resource: `DELETE http://localhost:7190/api/User/{id}`

## Built With

- [ASP.NET Core](https://docs.microsoft.com/en-us/aspnet/core/)
- [Entity Framework Core](https://docs.microsoft.com/en-us/ef/core/)
- [SQL Server 2019](https://learn.microsoft.com/en-us/sql/sql-server/?view=sql-server-ver16)
- [Client of Swagger](https://swagger.io/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Additional Resources

- [RESTful API Design](https://restfulapi.net/)
- [Best Practices for Designing a Pragmatic RESTful API](https://www.vinaysahni.com/best-practices-for-a-pragmatic-restful-api)
- [ASP.NET Core Web API](https://docs.microsoft.com/en-us/aspnet/core/web-api/)
