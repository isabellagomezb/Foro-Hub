package com.ForoHub.ForoHubChallenge.Base;

import com.ForoHub.ForoHubChallenge.Base.topico.DatosActualizarTopico;
import com.ForoHub.ForoHubChallenge.Base.topico.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity(name = "com.ForoHub.ForoHubChallenge.Base.Topico.")
@Table(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "sin_respuesta")
    private boolean sinRespuesta;


    public  Topico(DatosRegistroTopico datosRegistroTopico) {
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.curso();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
    }

    public void actualizarInfo(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }

        if (datosActualizarTopico.autor() != null) {
            this.autor = datosActualizarTopico.autor();
        }


    }
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}


