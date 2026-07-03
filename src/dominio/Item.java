package dominio;

import patrones.Visitor;

public class Item extends Carta {

	private int bonus;

	public Item(String name, int rarity, String type, int bonus) {
		super(name, rarity, type);
		this.bonus = bonus;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

	@Override
	public String toLinea() {
		return getName() + ";" + getRarity() + ";" + getType() + ";" + bonus;
	}

}