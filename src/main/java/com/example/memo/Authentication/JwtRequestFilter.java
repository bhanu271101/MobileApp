package com.example.memo.Authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{


    @Autowired
    private JWTToken jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


                String authHeader=request.getHeader("Authorization");

                if(authHeader!=null && authHeader.startsWith("Bearer "))
                {
                    String token=authHeader.substring(7);
                    try{
                        if(jwtToken.validateToken(token))
                        {
                            String userId=jwtToken.extractUserId(token);
                            request.setAttribute("userId", userId);
                        }
                    }
                    catch(Exception e)
                    {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write("Invalid token");
                        return;
                    }
                }
                filterChain.doFilter(request, response);
    }

    
}
