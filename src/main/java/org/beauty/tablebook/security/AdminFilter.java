package org.beauty.tablebook.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.restaurant.ErrorDTO;
import org.beauty.tablebook.controllers.restaurant.ExceptionController;
import org.beauty.tablebook.models.users.UserService;
import org.beauty.tablebook.models.users.Users;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@AllArgsConstructor
public class AdminFilter extends OncePerRequestFilter {
    UserService userService;


    private ExceptionController exceptionController;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("Authorization");

        System.out.println(request.getRequestURI());

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else {
            if (authHeader == null || authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token = authHeader;
            final Claims claims = Jwts.parser()
                    .setSigningKey("9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            String email = (String) claims.get("sub");

            Users user = userService.findByEmail(email);
            try {
                if (user.getIsAdmin()) {
                    request.setAttribute("claims", claims);
                    filterChain.doFilter(req, res);

                } else {
                    System.out.println("Not a admin");

                    throw new ServletException("Not a admin role");
                }
            } catch (ServletException e){
                ErrorDTO errorDTO = exceptionController
                        .handleServletException(request,
                                e);
                res.setStatus(errorDTO .getStatus());
                res.setContentType("application/json");

                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = res.getWriter();
                out.print(mapper.writeValueAsString(errorDTO ));
                out.flush();

            }

        }
    }
}