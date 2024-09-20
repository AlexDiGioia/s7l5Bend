package alexdigioia.s7l5.services;

import alexdigioia.s7l5.entities.Utente;
import alexdigioia.s7l5.enums.Ruolo;
import alexdigioia.s7l5.exceptions.BadRequestException;
import alexdigioia.s7l5.exceptions.NotFoundException;
import alexdigioia.s7l5.payloads.Utente.NewUtenteDTO;
import alexdigioia.s7l5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    PasswordEncoder bcrypt;


    public Utente save(NewUtenteDTO body) {
        if (this.utenteRepository.existsByEmail(body.email()))
            throw new BadRequestException("Email " + body.email() + " è già stata usata");
        Ruolo newRuolo;
        if (body.ruolo().equalsIgnoreCase("utente")) {
            newRuolo = Ruolo.UTENTE;
        } else if (body.ruolo().equalsIgnoreCase("organizzatore")) {
            newRuolo = Ruolo.ORGANIZZATORE;
        } else {
            throw new BadRequestException("ruolo non valido, inserisci UTENTE o ORGANIZZATORE");
        }
        Utente newUtente = new Utente(body.nome(), body.surname(), body.surname(), bcrypt.encode(body.password()), newRuolo);
        return this.utenteRepository.save(newUtente);
    }

    //public List<Utente> findAll() {
    //    return this.utenteRepository.findAll();
    //}
    public Page<Utente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return utenteRepository.findAll(pageable);
    }


    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteById(UUID id) {
        if (!utenteRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        utenteRepository.deleteById(id);
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Non ci sono risultati associati a questa email: " + email));
    }

    public Utente findByIdAndUpdate(UUID id, Utente body) {
        this.utenteRepository.findByEmail(body.getEmail()).ifPresent(
                user -> {
                    throw new BadRequestException("Email " + body.getEmail() + " già usata");
                }
        );
        Utente found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        return this.utenteRepository.save(found);
    }
}
