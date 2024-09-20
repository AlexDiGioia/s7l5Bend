package alexdigioia.s7l5.controllers;

import alexdigioia.s7l5.exceptions.BadRequestException;
import alexdigioia.s7l5.payloads.Utente.LoginDTO;
import alexdigioia.s7l5.payloads.Utente.LoginResponseDTO;
import alexdigioia.s7l5.payloads.Utente.NewUtenteDTO;
import alexdigioia.s7l5.payloads.Utente.NewUtenteResponseDTO;
import alexdigioia.s7l5.services.AuthService;
import alexdigioia.s7l5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    // POST http://localhost:3001/auth/login
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO payload) {
        return new LoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }

    // POST http://localhost:3001/auth/register  +BODY
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDTO save(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new NewUtenteResponseDTO(this.utenteService.save(body).getId());
        }
    }
}
