package com.hayden.airit.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hayden.airit.entity.*;

@Service("TokenService")
public class TokenService {
    public String getAdminToken(String email,String password){
        String token="";
        token= JWT.create().withAudience(email)// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(password));// 以 password 作为 token 的密钥
        return token;
    }
    public String getToken(Admin user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
    public String getToken(Manager manager) {
        String token="";
        token= JWT.create().withAudience(manager.getUsername())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(manager.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
