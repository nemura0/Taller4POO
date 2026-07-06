package logica;

import dominio.Carta;
import patrones.OrdenamientoStrategy;

import java.io.FileNotFoundException;
import java.util.List;

/** contrato del sistema: cargar, crud, ordenar y guardar la coleccion */
public interface Sistema {

	void loadData(String rutatxt) throws FileNotFoundException;

	List<Carta> getCartas();

	void agregarCarta(Carta c);

	void eliminarCarta(Carta c);

	void setEstrategia(OrdenamientoStrategy estrategia);

	void ordenar();

	void guardar();
	
	void modificarCarta(Carta c);

}
