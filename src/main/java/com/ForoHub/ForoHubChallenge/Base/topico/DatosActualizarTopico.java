package com.ForoHub.ForoHubChallenge.Base.topico;

import com.ForoHub.ForoHubChallenge.Base.Curso;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotNull
        Long id,
        String titulo,
        String mensaje,
        Curso curso,
        String autor
) {
}
