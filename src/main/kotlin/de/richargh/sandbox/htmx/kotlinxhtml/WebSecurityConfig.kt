package de.richargh.sandbox.htmx.kotlinxhtml

import de.richargh.sandbox.htmx.kotlinxhtml.login.web.LoginFormData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
//                .csrf { it.ignoringRequestMatchers("/*") }
                .authorizeHttpRequests { requests ->
                    requests
                            .requestMatchers("/", "/greeting", "/public/**").permitAll()
                            .anyRequest().authenticated()
                }
                .formLogin { form ->
                    form
                            .loginPage("/login")
                            .usernameParameter(LoginFormData::user.name)
                            .passwordParameter(LoginFormData::pass.name)
                            .successHandler { _, response, authentication ->
                                println("Success: $authentication")
                                response.sendRedirect("/")
                            }
                            .permitAll()
                }
                .logout { form ->
                    form
                            .logoutSuccessUrl("/login?logout=true")
                            .deleteCookies("JSESSIONID")
                            .permitAll()
                }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val alex: UserDetails = User
                .builder()
                .username("alex")
                .password(passwordEncoder().encode("alex"))
                .roles("USER")
                .build()
        val taylor: UserDetails = User
                .builder()
                .username("taylor")
                .password(passwordEncoder().encode("taylor"))
                .roles("USER")
                .build()
        return InMemoryUserDetailsManager(alex, taylor)
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}