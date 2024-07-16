package com.ForoHub.ForoHubChallenge.Base.topico;

import com.ForoHub.ForoHubChallenge.Base.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull @Valid
        Curso curso,
        @NotBlank
        String autor
) {
        public LocalDateTime fechaCreacion() {
                return fechaCreacion();
        }
}
