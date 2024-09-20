package alexdigioia.s7l5.payloads.Utente;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewUtenteResponseDTO(
        @NotNull(message = "L'id è obbligatorio")
        UUID id
) {
}
