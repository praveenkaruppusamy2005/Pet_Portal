package com.pet_portal.application.controller;

import com.pet_portal.application.entity.User;
import com.pet_portal.application.repository.UserRepository;
import com.pet_portal.application.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    // Get all users with pagination and sorting
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<User> users = userService.getUsersWithPaginationAndSorting(page, size, sortBy, direction);
        return ResponseEntity.ok(users.getContent());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> updatedUser = userService.updateUser(id, userDetails);
        return updatedUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Get users by role
    @GetMapping("/role")
    public ResponseEntity<List<User>> getUsersByRole(@RequestParam String role) {
        List<User> users = userRepository.findUsersByRole(role);
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    // Search users by username
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsersByUsername(@RequestParam String keyword) {
        return ResponseEntity.ok(userRepository.searchUsersByUsername(keyword));
    }

    // Find users by adoption application
    @GetMapping("/adoptions")
    public ResponseEntity<List<User>> findUsersByAdoption(@RequestParam String application) {
        return ResponseEntity.ok(userRepository.findUsersByAdoptionApplication(application));
    }

    // Find users by review keyword
    @GetMapping("/reviews")
    public ResponseEntity<List<User>> findUsersByReview(@RequestParam String word) {
        return ResponseEntity.ok(userRepository.findUsersByReviewKeyword(word));
    }

    // Count users by role
    @GetMapping("/count")
    public ResponseEntity<Long> countUsersByRole(@RequestParam String role) {
        return ResponseEntity.ok(userRepository.countUsersByRole(role));
    }

    // Find users without reviews
    @GetMapping("/no-reviews")
    public ResponseEntity<List<User>> findUsersWithoutReviews() {
        return ResponseEntity.ok(userRepository.findUsersWithoutReviews());
    }

    // // Find users by email domain
    // @GetMapping("/email")
    // public ResponseEntity<List<User>> findUsersByEmailDomain(@RequestParam String
    // domain) {
    // return ResponseEntity.ok(userRepository.findUsersByEmailDomain(domain));
}
