package patrones;

import dominio.*;

public class CartaFactory {
	
	public static Carta makeCarta(String linea) {
		
		String [] p = linea.split(";");
		
		String name = p[0].trim();
		int rarity = Integer.parseInt(p[1].trim());
		String type = p[2].trim();
		
		switch(type) {
		
		case "Pokemon":
			
			int dmg = Integer.parseInt(p[3].trim());
			int cantEnergy = Integer.parseInt(p[4].trim());
			
			Pokemon nuevopokemon = new Pokemon(name, rarity, type, dmg, cantEnergy);
			return nuevopokemon;
		
		case "Item" :
			
			int bonus = Integer.parseInt(p[3].trim());
			
			Item nuevoitem = new Item(name, rarity, type, bonus);
			return nuevoitem;
			
		case "Supporter" :
			
			int effectsbyturn = Integer.parseInt(p[3].trim());
			
			Supporter nuevosupporter = new Supporter(name, rarity, type, effectsbyturn);
			return nuevosupporter;
			
		case "Energy" :
			
			String element = p[3].trim();
			Energy nuevoenergy = new Energy(name, rarity, type, element);
			return nuevoenergy;
			
			default:
				System.out.println("Error : archivo txt incluye un tipo de carta no contemplado por este sistema");
				return null;
		}
		
		
	}

}
