package com.ForoHub.ForoHubChallenge.Base.topico;

import com.ForoHub.ForoHubChallenge.Base.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findBySinRespuestaTrue(Pageable pageable);
}
