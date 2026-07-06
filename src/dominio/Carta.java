package dominio;

/** clase base de las cartas. guarda lo comun: nombre, rareza y tipo */
public abstract class Carta implements Visitable {

	private String name;
	private int rarity;
	private String type;

	public Carta(String name, int rarity, String type) {
		this.name = name;
		this.rarity = rarity;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public int getRarity() {
		return rarity;
	}

	public String getType() {
		return type;
	}

	/** devuelve la carta como linea del txt: Nombre;Rareza;Tipo;... cada tipo agrega lo suyo */
	public abstract String toLinea();

	// asi las listas y combos de la gui muestran algo legible en vez del hash del objeto
	@Override
	public String toString() {
		return name + " (" + type + ")";
	}

}
