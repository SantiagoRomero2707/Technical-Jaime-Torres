package com.example.test.security.dto.response;

import org.springframework.security.core.GrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String firstname;
    private String lastname;
    private Collection<? extends GrantedAuthority> authorities;
}
