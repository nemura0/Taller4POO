package patrones;

import dominio.*;

public interface Visitor {

	void visit(Pokemon pokemon);
	void visit(Item item);
	void visit(Supporter support);
	void visit(Energy energy);
	
}
