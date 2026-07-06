package patrones;

import dominio.Carta;
import java.util.List;

/** ordena de mayor a menor rareza (mientras mayor la rareza, mejor) */
public class OrdenPorRareza implements OrdenamientoStrategy {

	@Override
	public void ordenar(List<Carta> cartas) {
		// b - a para dejar la rareza mas alta primero
		cartas.sort((a, b) -> b.getRarity() - a.getRarity());
	}

}
