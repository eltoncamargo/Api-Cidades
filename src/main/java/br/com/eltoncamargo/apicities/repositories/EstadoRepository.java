package br.com.eltoncamargo.apicities.repositories;

import br.com.eltoncamargo.apicities.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
