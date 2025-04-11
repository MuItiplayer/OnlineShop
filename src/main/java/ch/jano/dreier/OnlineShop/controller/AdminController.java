package ch.jano.dreier.OnlineShop.controller;

import ch.jano.dreier.OnlineShop.entity.AdminEntity;
import ch.jano.dreier.OnlineShop.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ch.jano.dreier.OnlineShop.security.Roles;


import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    @RolesAllowed(Roles.ADMIN)
    public List<AdminEntity> getAllAdmins() {
        return service.findAll();
    }

    @PostMapping
    @RolesAllowed(Roles.ADMIN)
    public AdminEntity createAdmin(@RequestBody AdminEntity admin) {
        return service.save(admin);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(Roles.ADMIN)
    public void deleteAdmin(@PathVariable Long id) {
        service.deleteById(id);
    }
}
