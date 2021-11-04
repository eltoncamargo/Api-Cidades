package br.com.eltoncamargo.apicities.repositories;

import br.com.eltoncamargo.apicities.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, Long> {
}
