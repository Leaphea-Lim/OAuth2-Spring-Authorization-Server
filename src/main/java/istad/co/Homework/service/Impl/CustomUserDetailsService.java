package istad.co.Homework.service.Impl;

import istad.co.Homework.config.CustomUserDetails;
import istad.co.Homework.domain.User;
import istad.co.Homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found")
                );

        return new CustomUserDetails(user);
    }
//@Override
//public UserDetails loadUserByUsername(String username) {
//    User user = userRepository.findByUsername(username)
//            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//    // If password is plain text, encode it for comparison
//    String password = user.getPassword();
//    if (!password.startsWith("$2a$")) { // Not BCrypt
//        password = passwordEncoder.encode(password);
//    }
//
//    return org.springframework.security.core.userdetails.User
//            .withUsername(user.getUsername())
//            .password(password)
//            .roles("USER") // Add proper roles
//            .build();
//}
}
