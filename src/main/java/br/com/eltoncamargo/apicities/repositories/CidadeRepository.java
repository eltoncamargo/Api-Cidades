package br.com.eltoncamargo.apicities.repositories;

import br.com.eltoncamargo.apicities.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(value = "SELECT ((SELECT lat_lon FROM cidade WHERE id=?1) <@> (SELECT lat_lon FROM cidade WHERE id=?2)) as distance", nativeQuery = true)
    Double distanciaByPoints(Long cidade1, Long cidade2);

    @Query(value = "SELECT earth_distance(ll_to_earth(?1,?2), ll_to_earth(?3,?4)) as distance", nativeQuery = true)
    Double distanciaByCube(double x, double y, double x1, double y1);

    @Query(value = "SELECT * FROM cidade WHERE uf=?1", nativeQuery = true)
    List<Cidade> findByIdEstado(Long idEstado);
}
