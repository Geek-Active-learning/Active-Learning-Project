//package com.example.demo.config;
//
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//
//public class AuthenticationConverter {
//
//    public static final String AUTHORITIES_CLAIM_NAME = "roles";
//
//    protected JwtAuthenticationConverter authenticationConverter() {
//        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        authoritiesConverter.setAuthorityPrefix("");
//        authoritiesConverter.setAuthoritiesClaimName(AUTHORITIES_CLAIM_NAME);
//
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
//        return converter;
//    }
//}
