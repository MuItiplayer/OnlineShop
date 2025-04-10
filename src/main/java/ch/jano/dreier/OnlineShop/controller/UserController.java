package ch.jano.dreier.OnlineShop.controller;

import ch.jano.dreier.OnlineShop.entity.UserEntity;
import ch.jano.dreier.OnlineShop.security.Roles;
import ch.jano.dreier.OnlineShop.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    @RolesAllowed(Roles.USER)
    public String getUserProfile(Principal principal) {
        return "Eingeloggt als: " + principal.getName();
    }

    @GetMapping
    @RolesAllowed(Roles.USER)
    public List<UserEntity> getAllUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public UserEntity getUserById(@PathVariable Long id) {
        return service.findById(id).orElseThrow();
    }

    @PostMapping
    @RolesAllowed(Roles.ADMIN)
    public UserEntity createUser(@RequestBody UserEntity user) {
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.USER)
    public void deleteUser(@PathVariable Long id) {
        service.deleteById(id);
    }
}
