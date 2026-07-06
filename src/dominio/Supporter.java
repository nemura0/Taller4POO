package dominio;

import patrones.Visitor;

public class Supporter extends Carta {

	private int effectsbyturn; // efectos por turno

	public Supporter(String name, int rarity, String type, int effectbyturn) {
		super(name, rarity, type);
		this.effectsbyturn = effectbyturn;
	}

	public int getEffectsbyturn() {
		return effectsbyturn;
	}

	public void setEffectsbyturn(int effectsbyturn) {
		this.effectsbyturn = effectsbyturn;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public String toLinea() {
		return getName() + ";" + getRarity() + ";" + getType() + ";" + effectsbyturn;
	}

}