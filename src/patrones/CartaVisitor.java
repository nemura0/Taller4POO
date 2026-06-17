package patrones;

import dominio.Energy;
import dominio.Item;
import dominio.Pokemon;
import dominio.Supporter;

public class CartaVisitor implements Visitor {
	
	private double calculatedPower;
	private double calculatedBonus;
	private int calculatedSupport;
	private int calculatedVenergy;

	@Override
	public void visit(Pokemon pokemon) {
		int power = (pokemon.getDmg()/pokemon.getCantEnergy()) * 100;
		calculatedPower = power;
	}

	@Override
	public void visit(Item item) {
		int bonus = item.getBonus() * 20;
		calculatedBonus = bonus;
	}

	@Override
	public void visit(Supporter support) {
		int Vsupport = support.getEffectsbyturn() * 50;
		calculatedSupport = Vsupport;
	}

	@Override
	public void visit(Energy energy) {
		int Venergy = 1;
		calculatedVenergy = Venergy; // lol
	}

	public double getCalculatedPower() {
		return calculatedPower;
	}

	public double getCalculatedBonus() {
		return calculatedBonus;
	}

	public int getCalculatedSupport() {
		return calculatedSupport;
	}

	public int getCalculatedVenergy() {
		return calculatedVenergy;
	}

	
	
}
