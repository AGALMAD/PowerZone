package com.PowerZone.PowerZone.Config

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
        private val authenticationProvider: AuthenticationProvider
) {
    @Bean
    fun securityFilterChain(
            http: HttpSecurity,
            jwtAuthenticationFilter: JwtAuthenticationFilter
    ): DefaultSecurityFilterChain {
        http
                .csrf { it.disable() }
                .authorizeHttpRequests {
                    it
                            //A todos los usuarios se les permite autenticarse y ver mensajes de error
                            .requestMatchers("/api/auth", "/api/auth/refresh", "/error")
                            .permitAll()

                            //Solo los administradores van a poder ver los datos de todos los ususarios y eliminarlos
                            .requestMatchers("/api/user/**" )
                            .hasRole("ADMIN")

                            //Todos los usuarios pueden crear una cuenta
                            .requestMatchers(HttpMethod.POST, "/api/user", "/api/participations" )
                            .permitAll()

                            //Solo solo administradores pueden crear y eliminar actividades
                            .requestMatchers(HttpMethod.POST, "/api/activities", )
                            .hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/activities/**", )
                            .hasRole("ADMIN")

                            //Todas las peticiones de obtencion de datos de usuarios, participaciones, actividades ... es necesario estar autenticados
                            .anyRequest()
                            .fullyAuthenticated()
                }
                .sessionManagement {
                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}