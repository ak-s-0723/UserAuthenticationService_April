package org.example.userauthenticationservice_april.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservice_april.models.Session;
import org.example.userauthenticationservice_april.models.SessionState;
import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.repositories.SessionRepository;
import org.example.userauthenticationservice_april.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secretKey;

    public User signUp(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            return null;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public Pair<User,MultiValueMap<String,String>> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()) {
            return null;
        }

        if(!bCryptPasswordEncoder.matches(password,
                optionalUser.get().getPassword())) {
            return null;
        }

        //TOKEN GENERATION


//        String message = "{\n" +
//                "   \"email\": \"anurag@scaler.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndJuly2024\"\n" +
//                "}";

        Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("email",optionalUser.get().getEmail());
        claims.put("roles",optionalUser.get().getRoleSet());
        long nowInMillis = System.currentTimeMillis();
        claims.put("iat",nowInMillis);
        claims.put("exp",nowInMillis+100000000L);  //10000 sec


//        byte[] content = message.getBytes(StandardCharsets.UTF_8);


        //MacAlgorithm algorithm = Jwts.SIG.HS256;
        //SecretKey secretKey = algorithm.key().build();

        //secretKey.getAlgorithm().toString();

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        //Storing Session for validation purpose
        Session session = new Session();
        session.setUser(optionalUser.get());
        session.setSessionState(SessionState.ACTIVE);
        session.setToken(token);
        sessionRepository.save(session);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        return new Pair<User,MultiValueMap<String,String>>(optionalUser.get(),headers);
    }

    public Boolean validateToken(String token,Long userId) {
        //If you are getting email as input
        //Go to user table and do findByEmail and
        //from User , get Id and use below

        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_Id(token,userId);

        if(optionalSession.isEmpty()) {
            System.out.println("User or Token not found");
            return false;
        }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        long tokenExpiry = (Long)claims.get("exp");
        long nowInMillis = System.currentTimeMillis();

        if(nowInMillis > tokenExpiry) {
            System.out.println(tokenExpiry);
            System.out.println(nowInMillis);
            System.out.println("Token Expired");
            return false;
        }

        Optional<User> optionalUser  = userRepository.findById(userId);
        String userEmail = optionalUser.get().getEmail();

        if(!userEmail.equals(claims.get("email"))) {
            System.out.println(userEmail);
            System.out.println(claims.get("email"));
            System.out.println("Emails didn't match");
            return false;
        }

        return true;
    }
}
