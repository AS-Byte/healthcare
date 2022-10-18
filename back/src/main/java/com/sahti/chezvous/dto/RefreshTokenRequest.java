package com.sahti.chezvous.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    private String refreshToken;
    private String username;
}
