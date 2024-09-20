package alexdigioia.s7l5.payloads.Utente;

import jakarta.validation.constraints.NotEmpty;

public record LoginDTO(
        @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        String password
) {

}
