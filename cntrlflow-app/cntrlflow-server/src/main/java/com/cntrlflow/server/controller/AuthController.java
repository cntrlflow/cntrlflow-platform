package com.cntrlflow.server.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cntrlflow.server.model.LoginRequest ;
import com.cntrlflow.server.service.AuthService;
import com.cntrlflow.server.utility.Constants;
import com.cntrlflow.server.utility.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {

            boolean result = authService.authenticateUser(loginRequest);
            
            if(result) {
                String jwt = jwtUtil.generateToken(loginRequest.getUsername());

                //Create HttpOnly cookie
                Cookie jwtCookie = new Cookie(Constants.JWT_NAME, jwt);
                jwtCookie.setHttpOnly(true);
                jwtCookie.setSecure(true);
                jwtCookie.setPath(Constants.COOKIE_PATH);
                jwtCookie.setMaxAge(3600);
                response.addCookie(jwtCookie);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", "success");
                jsonObject.put("message", "Login successful");

                return ResponseEntity.ok().body(jsonObject.toString());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie jwtCookie = new Cookie(Constants.JWT_NAME, null);
        jwtCookie.setPath(Constants.COOKIE_PATH);
        jwtCookie.setMaxAge(0);
        jwtCookie.setValue(null);
        response.addCookie(jwtCookie);

        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok().body("Logout sucessful");
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(HttpServletRequest request, HttpServletResponse response) {
        String jwtToken = jwtUtil.getJwtFromCookie(request);
        boolean validateStatus = jwtUtil.validateJwtToken(jwtToken);

        if(validateStatus) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "success");
            jsonObject.put("message", "token validated");

            return ResponseEntity.ok().body(jsonObject.toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
        }
    }

}