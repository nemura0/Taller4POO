package patrones;

import dominio.Carta;
import java.util.List;

/** ordena de mayor a menor poder. se apoya en el visitor para sacar el poder de cada carta */
public class OrdenPorPoder implements OrdenamientoStrategy {

	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort((a, b) -> poder(b) - poder(a));
	}

	// calcula el poder de una carta pasandole el visitor
	private int poder(Carta c) {
		CartaVisitor v = new CartaVisitor();
		c.accept(v);
		return v.getPoder();
	}

}
