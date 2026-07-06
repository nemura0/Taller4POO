package patrones;

import dominio.Carta;
import java.util.List;

/** ordena alfabeticamente por nombre */
public class OrdenPorNombre implements OrdenamientoStrategy {

	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort((a, b) -> a.getName().compareTo(b.getName()));
	}

}
