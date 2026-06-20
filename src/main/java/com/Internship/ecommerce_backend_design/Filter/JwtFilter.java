package com.Internship.ecommerce_backend_design.Filter;

import com.Internship.ecommerce_backend_design.Service.UserDetailService;
import com.Internship.ecommerce_backend_design.Service.UserService;
import com.Internship.ecommerce_backend_design.Utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserDetailService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizeHeaders=request.getHeader("Authorization");
        String userName=null;
        String jwtToken=null;
        if(authorizeHeaders!=null && authorizeHeaders.startsWith("Bearer ")) {
            jwtToken = authorizeHeaders.substring(7);
            userName = jwtUtils.extractUserName(jwtToken);

            if (userName != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                if (jwtUtils.validateToken(jwtToken)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken
                                    (userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
            response.addHeader("admin","Taha");
            filterChain.doFilter(request, response);
        }
    }

