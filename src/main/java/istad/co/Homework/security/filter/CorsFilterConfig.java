//package istad.co.Homework.security.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class CorsFilterConfig extends OncePerRequestFilter {
//
//    private final JwtDecoder jwtDecoder;
//    private static final String BEARER_PREFIX = "Bearer ";
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String jwt = resolveToken(request);
//
//        if (StringUtils.hasText(jwt)) {
//            try {
//                Jwt decodedJwt = jwtDecoder.decode(jwt);
//                JwtAuthenticationToken authentication = new JwtAuthenticationToken(
//                        decodedJwt,
//                        null,
//                        decodedJwt.getSubject()
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.debug("Set JWT authentication for user: {}", decodedJwt.getSubject());
//
//            } catch (JwtException e) {
//                log.error("JWT validation failed: {}", e.getMessage());
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid JWT token");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(BEARER_PREFIX.length());
//        }
//        return null;
//    }
//}