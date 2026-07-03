package patrones;

import dominio.Carta;
import java.util.List;

/** patron strategy: cada forma de ordenar la coleccion es una clase aparte */
public interface OrdenamientoStrategy {

	void ordenar(List<Carta> cartas);

}
