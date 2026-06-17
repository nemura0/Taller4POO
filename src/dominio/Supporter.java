package dominio;

import patrones.Visitor;

public class Supporter extends Carta {
	
	private int effectsbyturn; // efectos x turno btw

	public Supporter(String name, int rarity, String type, int effectbyturn) {
		super(name, rarity, type);
		this.effectsbyturn = effectbyturn;
	}

	public int getEffectsbyturn() {
		return effectsbyturn;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	

}
