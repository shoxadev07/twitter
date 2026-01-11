package uz.salikhdev.springbootinfinity.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.salikhdev.springbootinfinity.repositroy.UserRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {



        try {

            String bearerHeader = request.getHeader("Authorization");

            if (bearerHeader != null && bearerHeader.startsWith("Bearer ")) {

               try {
                   String token = bearerHeader.substring(7);

                   userRepository.findByToken(token).ifPresent(user -> {
                       CustomUserDetails customUserDetails = new CustomUserDetails(user);
                       SecurityContextHolder.getContext().setAuthentication(
                               new UsernamePasswordAuthenticationToken(
                                       customUserDetails,
                                       null,
                                       customUserDetails.getAuthorities()
                               )
                       );
                   });
               }catch (Exception e){
                   e.printStackTrace();
                   SecurityContextHolder.clearContext();
                   throw new RuntimeException("Token processing error", e);
               }
            }

            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
            throw new RuntimeException("Token processing error", e);
        }
    }
}
