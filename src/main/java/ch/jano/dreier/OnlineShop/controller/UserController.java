package ch.jano.dreier.OnlineShop.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getUserProfile(Principal principal) {
        return "Eingeloggt als: " + principal.getName();
    }
}
