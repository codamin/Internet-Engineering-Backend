package Loghme.controllers;
import Loghme.controllers.DTOs.Token;
import Loghme.controllers.requests.Login;
import Loghme.controllers.requests.TokenIdLogin;
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
        return new Token(AuthService.authTokenID(tokenIdLogin));
    }
}