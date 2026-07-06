package patrones;

import dominio.Energy;
import dominio.Item;
import dominio.Pokemon;
import dominio.Supporter;

/** visitor que calcula el poder de una carta segun su tipo */
public class CartaVisitor implements Visitor {

	private int poder;

	@Override
	public void visit(Pokemon pokemon) {
		// division entera como pide el readme. guarda por si vienen 0 energias asi no revienta
		if (pokemon.getCantEnergy() == 0) {
			poder = 0;
		} else {
			poder = (pokemon.getDmg() / pokemon.getCantEnergy()) * 100;
		}
	}

	@Override
	public void visit(Item item) {
		poder = item.getBonus() * 20;
	}

	@Override
	public void visit(Supporter support) {
		poder = support.getEffectsbyturn() * 50;
	}

	@Override
	public void visit(Energy energy) {
		poder = 1; // energy vale 1 por defecto
	}

	public int getPoder() {
		return poder;
	}

}
