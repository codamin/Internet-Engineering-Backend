package Loghme.services;

import Loghme.Utilities.JwtUtils;
import Loghme.database.dataMappers.user.UserMapper;
import Loghme.entities.User;
import Loghme.exceptions.ForbiddenException;
import Loghme.exceptions.NotFoundException;
import Loghme.requests.Login;
import Loghme.requests.TokenIdLogin;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

public class AuthService {
    public static String authUser(Login login) {
        if(login.getEmail() == null || login.getPassword() == null) {
            throw new ForbiddenException("both fields should not be empty to authenticate");
        }
        String email = login.getEmail();
        User foundUser = null;
        try {
            foundUser = UserMapper.getInstance().find(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(foundUser == null) {
            throw new NotFoundException("user not found");
        }
        if(!foundUser.getPassword().equals(DigestUtils.sha256Hex(login.getPassword().getBytes())))
            throw new ForbiddenException("wrong password!!!");

        return JwtUtils.createJWT(email);
    }

    public static String authTokenID(TokenIdLogin tokenIDLogin) throws GeneralSecurityException, IOException, SQLException {
        String verifiedEmail = JwtUtils.verifyGoogleTokenId(tokenIDLogin.getTokenId());
        if(verifiedEmail != null)
            return JwtUtils.createJWT(verifiedEmail);
        else
            throw new NotFoundException("user not found");
    }
}
