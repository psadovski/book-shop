package pl.pssoftware.bookstoreapi.security.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import pl.pssoftware.bookstoreapi.security.dto.LoginRequest;
import pl.pssoftware.bookstoreapi.security.dto.LoginResponse;
import pl.pssoftware.bookstoreapi.security.dto.RegisterRequest;
import pl.pssoftware.bookstoreapi.security.dto.RegisterResponse;
import pl.pssoftware.bookstoreapi.security.entity.Role;
import pl.pssoftware.bookstoreapi.security.entity.User;
import pl.pssoftware.bookstoreapi.security.entity.UserStatus;
import pl.pssoftware.bookstoreapi.security.exception.UserAlreadyExistsException;
import pl.pssoftware.bookstoreapi.security.repository.RoleRepository;
import pl.pssoftware.bookstoreapi.security.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authenticated =
                authenticationManager.authenticate(authentication);

        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = getJwtClaimsSet(now, authenticated);

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(jwtClaimsSet))
                .getTokenValue();

        return new LoginResponse(token);
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("ROLE_USER not found"));
        User savedUser = userRepository.save(getUser(request, userRole));

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getEmail()
        );
    }

    private @NonNull User getUser(RegisterRequest request, Role userRole) {
        User user = new User();
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setPhoneNumber(request.phoneNumber());
        user.setStatus(UserStatus.ACTIVE);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Set.of(userRole));
        return user;
    }

    private static @NonNull JwtClaimsSet getJwtClaimsSet(Instant now, Authentication authentication) {
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return JwtClaimsSet.builder()
                .issuer("book-store-api")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(authentication.getName())
                .claim("roles", roles)
                .build();
    }
}
