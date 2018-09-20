package br.edu.utfpr.pb.controleveiculo;

import br.edu.utfpr.pb.controleveiculo.customScope.ViewScope;
import br.edu.utfpr.pb.controleveiculo.filter.LoginFilter;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Main extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(Main.class);
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean servletRegistrationBean = 
				new ServletRegistrationBean(servlet, "*.jsf");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}
	
	@Bean
	public CustomScopeConfigurer customScopeConfigurer() {
		Map<String, Object> scopes = new HashMap<>();
		CustomScopeConfigurer scopeConfigurer = 
				new CustomScopeConfigurer();
		scopes.put("view", new ViewScope());
		scopeConfigurer.setScopes(scopes);
		return scopeConfigurer;
	}
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(someFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("loggedIn", "false");
		registration.setName("loginFilter");
		registration.setOrder(1);
		return registration;
	}

	public Filter someFilter() {
		return new LoginFilter();
	}
}