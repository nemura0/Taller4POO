package dominio;

import patrones.*;

public class Pokemon extends Carta {

	private int dmg;
	private int cantEnergy;

	public Pokemon(String name, int rarity, String type, int dmg, int cantEnergy) {
		super(name, rarity, type);
		this.dmg = dmg;
		this.cantEnergy = cantEnergy;
	}

	public int getDmg() {
		return dmg;
	}

	public int getCantEnergy() {
		return cantEnergy;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public void setCantEnergy(int cantEnergy) {
		this.cantEnergy = cantEnergy;
	}

	@Override
	public void accept(Visitor v) { // solo le abre la puerta al visitor
		v.visit(this);
	}

	@Override
	public String toLinea() {
		return getName() + ";" + getRarity() + ";" + getType() + ";" + dmg + ";" + cantEnergy;
	}

}