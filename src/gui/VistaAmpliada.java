package gui;

import dominio.Carta;
import dominio.Energy;
import dominio.Item;
import dominio.Pokemon;
import dominio.Supporter;
import patrones.CartaVisitor;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

/** ventana que muestra una carta en grande con su imagen, atributos y poder calculado */
public class VistaAmpliada extends JDialog {

	public VistaAmpliada(Carta carta) {
		setTitle(carta.getName());
		setModal(true);
		setSize(320, 440);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// imagen de la carta (o una por defecto si el archivo no existe)
		JLabel imagen = new JLabel(ImagenUtil.iconoDe(carta.getName(), 220, 280));
		imagen.setHorizontalAlignment(JLabel.CENTER);
		add(imagen, BorderLayout.CENTER);

		// el poder se calcula con el visitor
		CartaVisitor v = new CartaVisitor();
		carta.accept(v);

		String texto = "<html>"
				+ "Nombre: " + carta.getName() + "<br>"
				+ "Rareza: " + carta.getRarity() + "<br>"
				+ "Tipo: " + carta.getType() + "<br>"
				+ extras(carta) + "<br>"
				+ "Poder: " + v.getPoder()
				+ "</html>";
		add(new JLabel(texto), BorderLayout.SOUTH);
	}

	// muestra los atributos propios segun el tipo (casting como en el libro)
	private String extras(Carta carta) {
		if (carta instanceof Pokemon) {
			Pokemon p = (Pokemon) carta;
			return "Dano: " + p.getDmg() + " - Energias: " + p.getCantEnergy();
		} else if (carta instanceof Item) {
			Item i = (Item) carta;
			return "Bonificacion: " + i.getBonus();
		} else if (carta instanceof Supporter) {
			Supporter s = (Supporter) carta;
			return "Efectos por turno: " + s.getEffectsbyturn();
		} else {
			Energy en = (Energy) carta;
			return "Elemento: " + en.getElement();
		}
	}

}
