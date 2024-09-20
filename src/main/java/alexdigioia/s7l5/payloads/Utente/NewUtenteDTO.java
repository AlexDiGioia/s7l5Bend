package alexdigioia.s7l5.payloads.Utente;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
        @NotEmpty(message = "Il nome proprio è obbligatorio")
        @Size(min = 3, max = 40, message = "Il nome proprio deve essere compreso tra 3 e 40 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 40, message = "Il cognome deve essere compreso tra 3 e 40 caratteri")
        String surname,
        @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
        //  @Pattern() Permette di inserire una Regular Expression per validare praticamente qualsiasi cosa (es. PW fatte in una certa maniera)
        String password) {
}
