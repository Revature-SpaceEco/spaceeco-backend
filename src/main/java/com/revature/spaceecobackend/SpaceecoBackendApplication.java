package com.revature.spaceecobackend;

import com.revature.spaceecobackend.filter.JwtRequestFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpaceecoBackendApplication {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(SpaceecoBackendApplication.class, args);
  }

  @Bean
  public FilterRegistrationBean<JwtRequestFilter> initFilter() {
    FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new JwtRequestFilter());
    registrationBean.addUrlPatterns("*");
    return registrationBean;

  }

}
