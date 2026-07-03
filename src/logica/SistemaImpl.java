package logica;

import dominio.*;
import patrones.CartaFactory;
import patrones.OrdenamientoStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** sistema principal (singleton). mantiene la coleccion y ofrece crud, ordenar y persistir */
public class SistemaImpl implements Sistema {

	private static SistemaImpl instancia;
	private ArrayList<Carta> listaCarta;
	private OrdenamientoStrategy estrategia;
	private String ruta; // ruta del txt, se recuerda para poder guardar despues

	private SistemaImpl() {
		this.listaCarta = new ArrayList<>();
	}

	public static SistemaImpl getInstancia() {
		if (instancia == null) {
			instancia = new SistemaImpl();
		}
		return instancia;
	}

	@Override
	public void loadData(String rutatxt) throws FileNotFoundException {
		this.ruta = rutatxt;
		File f = new File(rutatxt);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String linea = sc.nextLine();

			Carta nueva = CartaFactory.makeCarta(linea);

			if (nueva != null) {
				listaCarta.add(nueva);
			}
		}
		sc.close();
	}

	@Override
	public List<Carta> getCartas() {
		return listaCarta;
	}

	@Override
	public void agregarCarta(Carta c) {
		listaCarta.add(c);
		guardar();
	}

	@Override
	public void eliminarCarta(Carta c) {
		listaCarta.remove(c);
		guardar();
	}

	@Override
	public void setEstrategia(OrdenamientoStrategy estrategia) {
		this.estrategia = estrategia;
	}

	@Override
	public void ordenar() {
		// le pasa el trabajo a la estrategia de turno (strategy)
		if (estrategia != null) {
			estrategia.ordenar(listaCarta);
		}
	}

	@Override
	public void guardar() {
		// reescribe el txt con el mismo formato. cada carta sabe armar su propia linea
		try {
			PrintWriter pw = new PrintWriter(new File(ruta));
			for (Carta c : listaCarta) {
				pw.println(c.toLinea());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: no se pudo guardar en " + ruta);
		}
	}

}
