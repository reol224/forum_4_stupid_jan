package com.example.forum_4_stupid.filter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKeys {
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final SecretKey getSigningKey(){
        return key;
    }
}
