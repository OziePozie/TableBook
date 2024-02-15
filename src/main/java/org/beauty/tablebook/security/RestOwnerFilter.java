package org.beauty.tablebook.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.beauty.tablebook.controllers.restaurant.ErrorDTO;
import org.beauty.tablebook.controllers.restaurant.ExceptionController;
import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.models.restaurants.Restaurants;
import org.beauty.tablebook.models.users.UserService;
import org.beauty.tablebook.models.users.Users;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import io.jsonwebtoken.*;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class RestOwnerFilter extends OncePerRequestFilter {
    RestaurantService restaurantService;
    UserService userService;
    ExceptionController exceptionController;
    @Override
    public void doFilterInternal(HttpServletRequest req,
                                 HttpServletResponse res,
                                 FilterChain filterChain)throws IOException, ServletException {
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


            String restID = request.getRequestURI().split("/")[5];
            String email = (String) claims.get("sub");
            Restaurants restaurants = restaurantService.getRestaurantByID(Long.valueOf(restID));
            Users user = userService.findByEmail(email);
            try {

                if (Objects.equals(restaurants.getOwner().getUserID(), user.getUserID())){

                    request.setAttribute("claims", claims);

                    req.setAttribute("userId", email);

                    filterChain.doFilter(req, res);

                } else throw new ServletException("Not a owner this Restaurant");
            } catch (ServletException e){
                ErrorDTO errorDTO = exceptionController
                        .handleServletException(request,
                                e);

                res.setStatus(errorDTO.getStatus());
                res.setContentType("application/json");

                ObjectMapper mapper = new ObjectMapper();
                PrintWriter out = res.getWriter();
                out.print(mapper.writeValueAsString(errorDTO ));
                out.flush();
            }



        }
    }


}