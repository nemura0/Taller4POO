package gui;

import dominio.Carta;
import logica.Sistema;
import patrones.OrdenPorNombre;
import patrones.OrdenPorPoder;
import patrones.OrdenPorRareza;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** pestaña que muestra la coleccion, con el combo de ordenamiento y el clic para ver en grande */
public class PanelColeccion extends JPanel implements ActionListener, MouseListener {

	private Sistema sistema;
	private DefaultListModel<Carta> modelo;
	private JList<Carta> lista;
	private JComboBox<String> combo;

	public PanelColeccion(Sistema sistema) {
		this.sistema = sistema;
		setLayout(new BorderLayout());

		combo = new JComboBox<>(new String[] { "Rareza", "Nombre", "Poder" });
		combo.addActionListener(this);
		JPanel arriba = new JPanel();
		arriba.add(new JLabel("Ordenar por:"));
		arriba.add(combo);
		add(arriba, BorderLayout.NORTH);

		modelo = new DefaultListModel<>();
		lista = new JList<>(modelo);
		lista.addMouseListener(this);
		add(new JScrollPane(lista), BorderLayout.CENTER);

		// arranca ordenado por rareza para que se vea prolijo
		sistema.setEstrategia(new OrdenPorRareza());
		refrescar();
	}

	/** vuelve a llenar la lista con lo que hay en el sistema, aplicando el orden actual */
	public void refrescar() {
		sistema.ordenar();
		modelo.clear();
		for (Carta c : sistema.getCartas()) {
			modelo.addElement(c);
		}
	}

	// al cambiar el combo se elige la estrategia y se reordena (patron strategy)
	@Override
	public void actionPerformed(ActionEvent e) {
		String opcion = (String) combo.getSelectedItem();
		if (opcion.equals("Rareza")) {
			sistema.setEstrategia(new OrdenPorRareza());
		} else if (opcion.equals("Nombre")) {
			sistema.setEstrategia(new OrdenPorNombre());
		} else {
			sistema.setEstrategia(new OrdenPorPoder());
		}
		refrescar();
	}

	// al hacer clic en una carta se abre la vista ampliada
	@Override
	public void mouseClicked(MouseEvent e) {
		Carta c = lista.getSelectedValue();
		if (c != null) {
			VistaAmpliada vista = new VistaAmpliada(c);
			vista.setVisible(true);
		}
	}

	// swing obliga a tener estos metodos aunque no se usen (interfaz MouseListener)
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}
