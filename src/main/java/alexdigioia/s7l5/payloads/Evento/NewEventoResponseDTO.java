package alexdigioia.s7l5.payloads.Evento;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewEventoResponseDTO(
        @NotNull(message = "L'id è obbligatorio")
        UUID id
) {

}
