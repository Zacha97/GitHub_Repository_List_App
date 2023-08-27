package com.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubApiClient {
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
