package com.example.test.security.dto.response;

import org.springframework.security.core.GrantedAuthority;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private String token;
    private String firstname;
    private String lastname;
    private Collection<? extends GrantedAuthority> authorities;
}
