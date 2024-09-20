package alexdigioia.s7l5.controllers;

import alexdigioia.s7l5.entities.Evento;
import alexdigioia.s7l5.payloads.Evento.NewEventoDTO;
import alexdigioia.s7l5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    //@GetMapping
    //public List<Evento> getAllEventi() {
    //    return eventoService.getAllEventi();
    //}

    // GET http://localhost:3001/eventi
    @GetMapping
    public Page<Evento> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return eventoService.findAll(page, size, sortBy);
    }

    // GET http://localhost:3001/eventi/{id}
    @GetMapping("/{id}")
    public Evento findById(@PathVariable UUID id) {
        return eventoService.findById(id);
    }

    //POST http://localhost:3001/eventi/{id}  +BODY
    @PostMapping
    public Evento save(@RequestBody NewEventoDTO newEventoDTO) {
        return eventoService.save(newEventoDTO);
    }

    //DELETE http://localhost:3001/eventi/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteEvento(@PathVariable UUID id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}