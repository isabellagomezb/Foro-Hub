package com.ForoHub.ForoHubChallenge.Base.topico;

import com.ForoHub.ForoHubChallenge.Base.Curso;
import com.ForoHub.ForoHubChallenge.Base.Topico;


import java.time.LocalDateTime;


public record DatosTopicoListar(
        Long id,
        String titulo,
        String mensaje,
        Curso curso,
        String autor,
        LocalDateTime fechaCreacion
) {
    public DatosTopicoListar(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getAutor(),
                topico.getFechaCreacion());
    }
}

