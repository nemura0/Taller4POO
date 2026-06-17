package dominio;
import patrones.Visitor;

public interface Visitable { // podriamos poner el accept en carta nomas pero hay puntos x diseño so ultimately we shall ball :basketball:

	void accept(Visitor v);
	
}
