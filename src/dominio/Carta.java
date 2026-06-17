package dominio;

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
	
	

	
	
	
}
