package br.com.eltoncamargo.apicities.service;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


import java.util.Arrays;
import java.util.List;

import br.com.eltoncamargo.apicities.model.Cidade;
import br.com.eltoncamargo.apicities.repositories.CidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanciaService {

  private final CidadeRepository cidadeRepository;
  Logger log = LoggerFactory.getLogger(DistanciaService.class);

  public DistanciaService(final CidadeRepository cidadeRepository) {
    this.cidadeRepository = cidadeRepository;
  }



  /**
   * 2nd option
   *
   * @param cidade1
   * @param cidade2
   * @return
   */
  public Double distanceByPointsInMiles(final Long cidade1, final Long cidade2) {
    log.info("nativePostgresInMiles({}, {})", cidade1, cidade2);
    return cidadeRepository.distanciaByPoints(cidade1, cidade2);
  }

  /**
   * 3rd option
   *
   * @param cidade1
   * @param cidade2
   * @param unit
   * @return
   */
  public Double distanceUsingPoints(final Long cidade1, final Long cidade2, final EarthRadius unit) {
    log.info("distanceUsingPoints({}, {}, {})", cidade1, cidade2, unit);
    final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

    Point p1 = cities.get(0).getLocation();
    Point p2 = cities.get(1).getLocation();

    return doCalculation(p1.getX(), p1.getY(), p2.getX(), p2.getY(), unit);
  }

  /**
   * 4th option
   *
   * @param cidade1
   * @param cidade2
   * @return
   */
  public Double distanceByCubeInMeters(Long cidade1, Long cidade2) {
    log.info("distanceByCubeInMeters({}, {})", cidade1, cidade2);
    final List<Cidade> cities = cidadeRepository.findAllById((Arrays.asList(cidade1, cidade2)));

    Point p1 = cities.get(0).getLocation();
    Point p2 = cities.get(1).getLocation();

    return cidadeRepository.distanciaByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  private double doCalculation(final double lat1, final double lon1, final double lat2,
                               final double lng2, final EarthRadius earthRadius) {
    double lat = toRadians(lat2 - lat1);
    double lon = toRadians(lng2 - lon1);
    double a = sin(lat / 2) * sin(lat / 2) + cos(toRadians(lat1)) * cos(toRadians(lat2)) * sin(lon / 2) * sin(lon / 2);
    double c = 2 * atan2(sqrt(a), sqrt(1 - a));

    return earthRadius.getValue() * c;
  }
}
