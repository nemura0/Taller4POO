package dominio;

import patrones.Visitor;

public class Energy extends Carta {
	
	private String element;

	public Energy(String name, int rarity, String type, String element) {
		super(name, rarity, type);
		this.element = element;
	}

	public String getElement() {
		return element;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
		
	}
	
	

}
