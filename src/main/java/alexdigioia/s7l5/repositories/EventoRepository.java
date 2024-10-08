package alexdigioia.s7l5.repositories;

import alexdigioia.s7l5.entities.Evento;
import alexdigioia.s7l5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByOrganizzatore(Utente organizzatore);
}
