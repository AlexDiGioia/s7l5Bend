package alexdigioia.s7l5.services;

import alexdigioia.s7l5.entities.Evento;
import alexdigioia.s7l5.entities.Utente;
import alexdigioia.s7l5.exceptions.BadRequestException;
import alexdigioia.s7l5.exceptions.NotFoundException;
import alexdigioia.s7l5.payloads.Evento.NewEventoDTO;
import alexdigioia.s7l5.repositories.EventoRepository;
import alexdigioia.s7l5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    //public List<Evento> findAll() {
    //    return eventoRepository.findAll();
    //}

    public Page<Evento> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventoRepository.findAll(pageable);
    }

    public Evento findById(UUID id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Evento save(NewEventoDTO newEventoDTO) {
        Utente organizzatore = utenteRepository.findById(UUID.fromString(newEventoDTO.organizzatoreId()))
                .orElseThrow(() -> new BadRequestException("Organizzatore non trovato"));

        Evento evento = new Evento(
                newEventoDTO.titolo(),
                newEventoDTO.descrizione(),
                newEventoDTO.data(),
                newEventoDTO.postiDisponibili(),
                organizzatore
        );

        return eventoRepository.save(evento);
    }

    public void deleteById(UUID id) {
        if (!eventoRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        eventoRepository.deleteById(id);
    }
}
