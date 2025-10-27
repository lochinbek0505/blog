package uz.falconmobile.blog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.falconmobile.blog.services.AuthanticationService;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthentiocationFilter extends OncePerRequestFilter {

    private final AuthanticationService authanticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String token = extractToken(request);
            if (token != null) {
                UserDetails userDetails = authanticationService.validateToken(token);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                if(userDetails instanceof BlogUserDetails) {
                    request.setAttribute("userId",((BlogUserDetails) userDetails).getId());
                }
            }

        }catch (Exception e){
            log.warn("RECIVER INVALID AUTHENTICATION TOKEN");
        }

        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }
        return null;
    }

}
