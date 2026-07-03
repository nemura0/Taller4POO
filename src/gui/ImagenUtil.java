package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;

/** utilidad para cargar la imagen de una carta. si no existe genera una por defecto */
public class ImagenUtil {

	// busca la imagen como {nombre}.png/.jpg/.jpeg dentro de la carpeta img
	public static ImageIcon iconoDe(String nombre, int ancho, int alto) {
		String[] extensiones = { ".png", ".jpg", ".jpeg" };
		for (String ext : extensiones) {
			File f = new File("img/" + nombre + ext);
			if (f.exists()) {
				ImageIcon icono = new ImageIcon(f.getPath());
				return escalar(icono, ancho, alto);
			}
		}
		return porDefecto(nombre, ancho, alto);
	}

	private static ImageIcon escalar(ImageIcon icono, int ancho, int alto) {
		return new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
	}

	// imagen dibujada al vuelo cuando la carta no tiene archivo, asi la gui no se rompe
	private static ImageIcon porDefecto(String nombre, int ancho, int alto) {
		BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(220, 220, 220));
		g.fillRect(0, 0, ancho, alto);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(0, 0, ancho - 1, alto - 1);
		g.setFont(new Font("SansSerif", Font.BOLD, 14));
		g.drawString("sin imagen", 10, alto / 2);
		g.drawString(nombre, 10, alto / 2 + 20);
		g.dispose();
		return new ImageIcon(img);
	}

}
