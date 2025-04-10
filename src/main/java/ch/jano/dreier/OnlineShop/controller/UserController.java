package ch.jano.dreier.OnlineShop.controller;
import ch.jano.dreier.OnlineShop.security.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    @RolesAllowed(Roles.USER)
    public String getUserProfile(Principal principal) {
        return "Eingeloggt als: " + principal.getName();
    }
}
