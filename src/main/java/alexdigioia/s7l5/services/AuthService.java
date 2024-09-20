package alexdigioia.s7l5.services;

import alexdigioia.s7l5.entities.Utente;
import alexdigioia.s7l5.exceptions.UnauthorizedException;
import alexdigioia.s7l5.payloads.Utente.LoginDTO;
import alexdigioia.s7l5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService usersService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Utente found = this.usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return this.jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("credenziali errate");
        }
    }


}
