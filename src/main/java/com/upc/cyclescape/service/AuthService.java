package com.upc.cyclescape.service;


import com.upc.cyclescape.dto.AuthenticationResponse;
import com.upc.cyclescape.dto.LoginRequest;
import com.upc.cyclescape.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {
    public abstract AuthenticationResponse register(RegisterRequest registerRequest);

    public abstract AuthenticationResponse login(LoginRequest loginRequest);

    public void validateRegisterRequest(RegisterRequest registerRequest);
    public void existsUserByEmail(RegisterRequest registerRequest);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
