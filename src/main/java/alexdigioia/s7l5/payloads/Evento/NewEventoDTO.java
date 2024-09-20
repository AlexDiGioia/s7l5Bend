package alexdigioia.s7l5.payloads.Evento;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewEventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 3, max = 80, message = "Il numero di caratteri del titolo deve essere compreso tra 3 e 100 caratteri")
        String titolo,

        @NotEmpty(message = "La descrizione è obbligatoria")
        @Size(min = 10, max = 500, message = "Il numero di caratteri della descrizione deve essere compreso tra 10 e 500 caratteri")
        String descrizione,

        @NotNull(message = "La data è obbligatoria")
        LocalDate data,

        @NotNull(message = "Il numero di posti disponibili è obbligatorio")
        @Min(value = 10, message = "Devono esserci disponibili almeno 10 posti")
        Integer postiDisponibili,

        @NotEmpty(message = "L'id dell'organizzatore è obbligatorio")
        String organizzatoreId
) {
}