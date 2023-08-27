# GitHub Repository List App
This is a Spring Boot application which interact with the GitHub API to list all repos of User.

## Getting Started
To run the application, make sure you have Java & Gradle installed.

1. Clone this repository.
2. Open the project in your preferred Java IDE.
3. Build and run the application.

### Usage
Once the application is running you can access the endpoint using browser, bash or Postman.

- Endpoint: "http://localhost:8080/github/repos"
- Query param: "username" - the GitHub username for which you want  to list repos.
  - i.e: curl http://localhost:8080/github/repos?username={GitHubUsername}

### API Responses
- JSON: List of repositories with repository name, owner login, and branch information. 
- Status Codes:
  - 200 OK: Successful response 
  - 404 Not Found: If the user doesn't exist on GitHub. 
  - 406 Not Acceptable: If the "Accept" header is not supported. 
  - 415 Unsupported Media Type: If the content type is not supported.

### NOTES
This application uses Spring Boot and the Spring RestTemplate to communicate with the GitHub API.