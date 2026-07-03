/*
Vicente Guerra / 21.855.415-6 / nemura0
Luis Molina / 21.564.225-9 / mixolydiann
*/
package logica;

import gui.VentanaPrincipal;

import java.io.FileNotFoundException;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		SistemaImpl sys = SistemaImpl.getInstancia();

		sys.loadData("Sobres.txt");

		// se abre la ventana con la coleccion ya cargada
		VentanaPrincipal ventana = new VentanaPrincipal(sys);
		ventana.setVisible(true);

	}

}
