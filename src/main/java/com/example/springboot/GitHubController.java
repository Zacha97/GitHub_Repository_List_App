package com.example.springboot;

import com.example.api.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github")
public class GitHubController {
    private final GitHubApiClient gitHubApiClient;

    public GitHubController(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    @GetMapping("/repos")
    public ResponseEntity<String> listUserRepositories(@RequestParam String username,
                                                       @RequestHeader("Accept") String acceptHeader) {
        try {
            ResponseEntity<String> response = gitHubApiClient.getUserRepositories(username);
            // response process
            return response;
        } catch (RuntimeException e) {
            //exceptions handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @Service
    public static class GitHubApiClient {
        private final String GITHUB_API_BASE_URL = "https://api.github.com";

        private final RestTemplate restTemplate;

        public GitHubApiClient() {
            this.restTemplate = new RestTemplate();
        }

        public ResponseEntity<String> getUserRepositories(String username) {
            String url = GITHUB_API_BASE_URL + "/users/" + username + "/repos";

            try {
                return restTemplate.getForEntity(url, String.class);
            } catch (org.springframework.web.client.HttpClientErrorException.NotFound ex) {
                throw new UserNotFoundException("User not found on GitHub");
            } catch (Exception e) {
                throw new RuntimeException("GitHub API request failed: " + e.getMessage());
            }
        }
    }
}