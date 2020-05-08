package Loghme.controllers;
import Loghme.DTOs.Token;
import Loghme.requests.Login;
import Loghme.requests.TokenIdLogin;
import Loghme.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Token login(@RequestBody Login login) {
        return new Token(AuthService.authUser(login));
    }

    @PostMapping("/tokenIDLogin")
    public Token tokenLogin(@RequestBody TokenIdLogin tokenIdLogin) throws GeneralSecurityException, IOException, SQLException {
        System.out.println("fuck1");
        System.out.println(tokenIdLogin.getTokenId());
        return new Token(AuthService.authTokenID(tokenIdLogin));
    }
}