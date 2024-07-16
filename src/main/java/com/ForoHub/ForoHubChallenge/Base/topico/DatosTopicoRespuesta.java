package com.ForoHub.ForoHubChallenge.Base.topico;

import com.ForoHub.ForoHubChallenge.Base.Curso;

public record DatosTopicoRespuesta(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        Curso curso
) {
}
