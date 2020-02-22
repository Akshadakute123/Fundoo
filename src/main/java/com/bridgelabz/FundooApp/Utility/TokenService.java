package com.bridgelabz.FundooApp.Utility;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class TokenService
{

	public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";

    public String createToken(String email) {
        
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("email",email).sign(algorithm);
            return token;
        
        
       
            
    }
    
    
    

    public String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("email").asString();
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }
//    public String decodeToken(String token) {
//        
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//            return jwt.getClaim("email").asString();
//        
//          
//        }
//    
//
//    public boolean isTokenValid(String token) {
//        String email = this.decodeToken(token);
//        return email != null;
//    }
//    
//    public String getUserToken(String token) {
//		Claim claim = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaim("email");
//
//		System.out.println("claim" + claim);
//		return claim.asString();
//
//	}
	
	
}
