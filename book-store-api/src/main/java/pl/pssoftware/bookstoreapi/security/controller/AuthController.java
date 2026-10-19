package pl.pssoftware.bookstoreapi.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pssoftware.bookstoreapi.security.dto.LoginRequest;
import pl.pssoftware.bookstoreapi.security.dto.LoginResponse;
import pl.pssoftware.bookstoreapi.security.dto.RegisterRequest;
import pl.pssoftware.bookstoreapi.security.dto.RegisterResponse;
import pl.pssoftware.bookstoreapi.security.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
