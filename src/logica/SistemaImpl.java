package logica;

import dominio.*;
import patrones.CartaFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaImpl implements Sistema{

	private static SistemaImpl instancia;
	private ArrayList<Carta> listaCarta;
	
	//patrones.CartaVisitor cardvisitor = new patrones.CartaVisitor(); // 4 testing purposes
	
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
		File f = new File(rutatxt);
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			String linea = sc.nextLine();
			
			Carta nuevacartalol = CartaFactory.makeCarta(linea);
			
			if (nuevacartalol != null) {
				listaCarta.add(nuevacartalol);
			}
			/*nuevacartalol.accept(cardvisitor); // testing
			System.out.println("saved : " + nuevacartalol.getName()
			+ " | power : " + cardvisitor.getCalculatedPower());
		*/}
		sc.close();
	}
	
	
}
