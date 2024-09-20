package alexdigioia.s7l5.controllers;

import alexdigioia.s7l5.entities.Utente;
import alexdigioia.s7l5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    UtenteService utenteService;

    // GET http://localhost:3001/utenti
    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.utenteService.findAll(page, size, sortBy);
    }

    // GET http://localhost:3001/utenti/{id}
    @GetMapping("/{id}") //
    public Utente findById(@PathVariable UUID userId) {
        return this.utenteService.findById(userId);
    }


    // GET http://localhost:3001/utenti/me
    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente) {
        return currentAuthenticatedUtente;
    }

    // PUT http://localhost:3001/utenti/me  +BODY
    @PutMapping("/me")
    public Utente updateProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente, @RequestBody Utente body) {
        return this.utenteService.findByIdAndUpdate(currentAuthenticatedUtente.getId(), body);
    }


}
