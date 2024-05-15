package com.cntrlflow.server.utility;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    public String generateToken(String username) {
        return Jwts.builder()
                   .setSubject(username)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date((new Date()).getTime() + Constants.JWT_EXPIRY_MS))
                   .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
                   .compact();
    }
    
    public String getUsername(String token) {
    	return extractClaim(token, Claims::getSubject);
    }
    
    public Date getExpiry(String token) {
    	return extractClaim(token, Claims::getExpiration);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    	final Claims claims = extractAllClaims(token);
    	return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
    	return Jwts.parser().setSigningKey(Constants.JWT_SECRET).parseClaimsJws(token).getBody();
    }
    
    private Boolean isTokenExpired(String token) {
    	return getExpiry(token).before(new Date());
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
    	final String username = getUsername(token);
    	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(Constants.JWT_SECRET).parseClaimsJws(token);

            if(!isTokenExpired(token)) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getJwtFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, Constants.JWT_NAME);
        if(cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }
}
