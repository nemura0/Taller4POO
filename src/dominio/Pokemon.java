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

	@Override
	public void accept(Visitor v) { // solo le abrimos la pueeeeeeerta al visitor
		v.visit(this);
	}
	
	

}
