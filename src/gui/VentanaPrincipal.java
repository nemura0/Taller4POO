package gui;

import logica.Sistema;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/** ventana principal con las dos pestañas: administracion y ver coleccion */
public class VentanaPrincipal extends JFrame {

	public VentanaPrincipal(Sistema sistema) {
		setTitle("Coleccion Pokemon TCG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);

		// se crea primero la coleccion para poder avisarle a la de administracion cuando cambie algo
		PanelColeccion panelColeccion = new PanelColeccion(sistema);
		PanelAdministracion panelAdmin = new PanelAdministracion(sistema, panelColeccion);

		JTabbedPane pestanas = new JTabbedPane();
		pestanas.add("Administracion", panelAdmin);
		pestanas.add("Ver Coleccion", panelColeccion);

		add(pestanas);
	}

}
