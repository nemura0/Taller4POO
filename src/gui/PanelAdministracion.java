package gui;

import dominio.Carta;
import logica.Sistema;
import patrones.CartaFactory;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** pestaña de administracion: agregar, eliminar y modificar cartas */
public class PanelAdministracion extends JPanel implements ActionListener {

	private Sistema sistema;
	private PanelColeccion panelColeccion; // para refrescar la otra pestaña cuando cambia algo

	private JTextField campoNombre;
	private JTextField campoRareza;
	private JComboBox<String> comboTipo;
	private JTextField campoDano;
	private JTextField campoEnergias;
	private JTextField campoBonificacion;
	private JTextField campoEfectos;
	private JTextField campoElemento;

	private JButton botonAgregar;
	private JButton botonModificar;

	private JComboBox<Carta> comboEliminar;
	private JButton botonEliminar;

	public PanelAdministracion(Sistema sistema, PanelColeccion panelColeccion) {
		this.sistema = sistema;
		this.panelColeccion = panelColeccion;

		setLayout(new GridLayout(0, 2, 5, 5));

		campoNombre = new JTextField();
		campoRareza = new JTextField();
		comboTipo = new JComboBox<>(new String[] { "Pokemon", "Item", "Supporter", "Energy" });
		campoDano = new JTextField();
		campoEnergias = new JTextField();
		campoBonificacion = new JTextField();
		campoEfectos = new JTextField();
		campoElemento = new JTextField();

		add(new JLabel("Nombre:")); add(campoNombre);
		add(new JLabel("Rareza:")); add(campoRareza);
		add(new JLabel("Tipo:")); add(comboTipo);
		add(new JLabel("Dano (Pokemon):")); add(campoDano);
		add(new JLabel("Energias (Pokemon):")); add(campoEnergias);
		add(new JLabel("Bonificacion (Item):")); add(campoBonificacion);
		add(new JLabel("Efectos x turno (Supporter):")); add(campoEfectos);
		add(new JLabel("Elemento (Energy):")); add(campoElemento);

		botonAgregar = new JButton("Agregar");
		botonAgregar.addActionListener(this);
		botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(this);
		add(botonAgregar); add(botonModificar);

		comboEliminar = new JComboBox<>();
		botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(this);
		add(comboEliminar); add(botonEliminar);

		refrescarComboEliminar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonAgregar) {
			agregar();
		} else if (e.getSource() == botonEliminar) {
			eliminar();
		} else if (e.getSource() == botonModificar) {
			modificar();
		}
	}

	// arma la linea con el formato del txt y deja que la factory cree la carta
	private void agregar() {
		try {
			String nombre = campoNombre.getText().trim();
			if (nombre.isEmpty()) {
				throw new IllegalArgumentException("el nombre no puede estar vacio");
			}
			String rareza = campoRareza.getText().trim();
			String tipo = (String) comboTipo.getSelectedItem();

			String linea = nombre + ";" + rareza + ";" + tipo + ";" + extras(tipo);

			Carta c = CartaFactory.makeCarta(linea);
			if (c == null) {
				throw new IllegalArgumentException("tipo de carta no valido");
			}

			sistema.agregarCarta(c);
			refrescarComboEliminar();
			panelColeccion.refrescar();
			limpiar();
			JOptionPane.showMessageDialog(this, "carta agregada");
		} catch (NumberFormatException ex) {
			// pasa si la rareza o algun campo numerico viene vacio o con letras
			JOptionPane.showMessageDialog(this, "hay campos numericos con valores invalidos");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	// devuelve los atributos propios del tipo, ya con el ; que corresponde
	private String extras(String tipo) {
		if (tipo.equals("Pokemon")) {
			return campoDano.getText().trim() + ";" + campoEnergias.getText().trim();
		} else if (tipo.equals("Item")) {
			return campoBonificacion.getText().trim();
		} else if (tipo.equals("Supporter")) {
			return campoEfectos.getText().trim();
		} else {
			return campoElemento.getText().trim();
		}
	}

	private void eliminar() {
		Carta c = (Carta) comboEliminar.getSelectedItem();
		if (c == null) {
			JOptionPane.showMessageDialog(this, "no hay carta para eliminar");
			return;
		}
		sistema.eliminarCarta(c);
		refrescarComboEliminar();
		panelColeccion.refrescar();
		JOptionPane.showMessageDialog(this, "carta eliminada");
	}

	private void modificar() {
		// TODO (Luis): completar la modificacion de una carta.
		// idea: tomar la carta elegida en comboEliminar, mirar su tipo con getType()
		// y segun el tipo pedir el nuevo valor y usar el setter que corresponde:
		//   Pokemon   -> setDmg / setCantEnergy
		//   Item      -> setBonus
		//   Supporter -> setEffectsbyturn
		//   Energy    -> setElement
		// al final llamar a sistema.guardar() y panelColeccion.refrescar().
		// el metodo agregar() de mas arriba sirve de ejemplo para leer campos y validar.
		JOptionPane.showMessageDialog(this, "modificar: pendiente por completar");
	}

	// recarga el combo de eliminar con las cartas actuales
	private void refrescarComboEliminar() {
		comboEliminar.removeAllItems();
		for (Carta c : sistema.getCartas()) {
			comboEliminar.addItem(c);
		}
	}

	private void limpiar() {
		campoNombre.setText("");
		campoRareza.setText("");
		campoDano.setText("");
		campoEnergias.setText("");
		campoBonificacion.setText("");
		campoEfectos.setText("");
		campoElemento.setText("");
	}

}
