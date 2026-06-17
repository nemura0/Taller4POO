/*
Vicente Guerra / 21.855.415-6 / nemura0
Luis Molina / 21.564.225-9 / mixolydiann
*/
package logica;

import java.io.FileNotFoundException;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		SistemaImpl sys = SistemaImpl.getInstancia();
		
		sys.loadData("Sobres.txt");
		
		
		
	}

}
