package com.ForoHub.ForoHubChallenge.Controller;

import com.ForoHub.ForoHubChallenge.Base.Topico;
import com.ForoHub.ForoHubChallenge.Base.topico.*;
import com.ForoHub.ForoHubChallenge.Base.topico.DatosTopicoRespuesta;
import com.ForoHub.ForoHubChallenge.Base.topico.ITopicoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")

public class TopicoController {
    @Autowired
    private ITopicoRepository topicoRepository;


    @PostMapping
    public ResponseEntity<DatosTopicoRespuesta> registrarTopico (@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                 UriComponentsBuilder uriComponentsBuilder
    ){
    Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
    DatosTopicoRespuesta datosRespuestaTopico = new DatosTopicoRespuesta(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso());

    URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
}

@GetMapping
public ResponseEntity<Page<DatosTopicoListar>> listadoTopico(@PageableDefault(size = 10) Pageable paginacion) {
    Pageable sortedByFechaCreacionAsc = PageRequest.of(paginacion.getPageNumber(), paginacion.getPageSize(), Sort.by("fechaCreacion").ascending());
    return ResponseEntity.ok(topicoRepository.findBySinRespuestaTrue(sortedByFechaCreacionAsc)
            .map(DatosTopicoListar :: new));
}

@PutMapping("/{id}")
@Transactional
@Operation(summary = "Actualizar un tópico", description = "Actualizar los detalles de un tópico existente mediante su ID.")
public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
    if (!id.equals((datosActualizarTopico.id()))) {
        return ResponseEntity.badRequest().build();
    }
    Optional<Topico> verificarTopico = topicoRepository.findById(id);
    if (verificarTopico.isEmpty()) {
        throw new EntityNotFoundException("El ID " + id + " no existe.");
    }
    Topico topico = verificarTopico.get();
    topico.actualizarInfo((datosActualizarTopico));
    return ResponseEntity.ok(new DatosTopicoRespuesta(topico.getId(), topico.getTitulo(), topico.getMensaje(),
            topico.getAutor(), topico.getCurso()));
}

@DeleteMapping("/{id}")
@Transactional
@Operation(summary = "Eliminar un tópico", description = "Eliminar un tópico existente mediante su ID.")
public ResponseEntity eliminarTopico(@PathVariable Long id) {
    Optional<Topico> verificarTopico = topicoRepository.findById(id);
    if (verificarTopico.isPresent()) {
        topicoRepository.delete(verificarTopico.get());
        return ResponseEntity.ok("Su topico fue eliminado");
    } else {
        return ResponseEntity.status(404).body("El ID " + id + " no existe");
    }
}

@GetMapping("/{id}")
@Operation(summary = "Obtener un tópico por ID", description = "Obtener los detalles de un tópico específico mediante su ID.")
public ResponseEntity<DatosTopicoListar> obtenerTopicoPorId(@PathVariable Long id) {
    Optional<Topico> verificarTopico = topicoRepository.findById(id);
    if (verificarTopico.isPresent()) {
        Topico topico = verificarTopico.get();
        DatosTopicoListar datosTopicoListar = new DatosTopicoListar(topico);
        return ResponseEntity.ok(datosTopicoListar);
    } else {
        throw new EntityNotFoundException("El ID " + id + " no existe.");
    }
}
}
