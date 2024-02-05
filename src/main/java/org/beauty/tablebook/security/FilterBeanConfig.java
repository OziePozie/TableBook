package org.beauty.tablebook.security;

import org.beauty.tablebook.controllers.restaurant.ExceptionController;
import org.beauty.tablebook.models.restaurants.RestaurantService;
import org.beauty.tablebook.models.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FilterBeanConfig {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UserService userService;
    @Autowired
    ExceptionController exceptionController;

    @Bean
    public FilterRegistrationBean requestRestOwnerFilter() {
        RestOwnerFilter restOwnerFilter =new RestOwnerFilter(restaurantService,
                userService,
                exceptionController);
        final FilterRegistrationBean reg = new FilterRegistrationBean(restOwnerFilter);
        reg.addUrlPatterns("/api/v1/owner/restaurants/*");
        return reg;
    }
    @Bean
    public FilterRegistrationBean requestAdminFilter() {
        AdminFilter adminFilter = new AdminFilter(userService,
                exceptionController);
        final FilterRegistrationBean reg = new FilterRegistrationBean(adminFilter);
        reg.addUrlPatterns("/api/v1/admin/*");
        return reg;
    }

}