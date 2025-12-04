//package istad.co.Homework.security.service;
//
//import auth.res_server.demo.config.CustomUserDetails;
//import auth.res_server.demo.domain.User;
//import auth.res_server.demo.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class CustomAuthProvider implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.debug("Loading user by username: {}", username);
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        // Convert authorities from strings to GrantedAuthority objects
//        Set<GrantedAuthority> authorities = user.getAuthorities().stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toSet());
//
//        // Create CustomUserDetails from User entity
//        return new CustomUserDetails(
//                user.getId(),
//                user.getUuid(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                user.getFamilyName(),
//                user.getGivenName(),
//                user.getPhoneNumber(),
//                user.getGender(),
//                user.getDob(),
//                user.getProfileImage(),
//                user.getCoverImage(),
//                user.isAccountNonExpired(),
//                user.isAccountNonLocked(),
//                user.isCredentialsNonExpired(),
//                user.isEnabled(),
//                user.isEmailVerified(),
//                authorities
//        );
//    }
//
//    @Transactional(readOnly = true)
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        log.debug("Loading user by email: {}", email);
//
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//
//        Set<GrantedAuthority> authorities = user.getAuthorities().stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toSet());
//
//        return new CustomUserDetails(
//                user.getId(),
//                user.getUuid(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                user.getFamilyName(),
//                user.getGivenName(),
//                user.getPhoneNumber(),
//                user.getGender(),
//                user.getDob(),
//                user.getProfileImage(),
//                user.getCoverImage(),
//                user.isAccountNonExpired(),
//                user.isAccountNonLocked(),
//                user.isCredentialsNonExpired(),
//                user.isEnabled(),
//                user.isEmailVerified(),
//                authorities
//        );
//    }
//}