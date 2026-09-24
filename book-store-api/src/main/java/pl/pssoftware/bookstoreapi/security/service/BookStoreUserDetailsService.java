package pl.pssoftware.bookstoreapi.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.pssoftware.bookstoreapi.security.config.BookStoreUserDetails;
import pl.pssoftware.bookstoreapi.security.entity.User;
import pl.pssoftware.bookstoreapi.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BookStoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + email
                        )
                );

        return new BookStoreUserDetails(user);
    }
}
