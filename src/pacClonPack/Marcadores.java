package pacClonPack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

// ===============================================================
public class Marcadores {

	public int size;
	public int x;
	public int y;
	public String txt;

	public Marcadores(int size, int x, int y, String txt) {

		this.size = size;
		this.x = x;
		this.y = y;
		this.txt = txt;
	}

	public void dibuja(Graphics g, int valor) {

		int[] rgb = {225, 225, 9};

		Font fuente = new Font("Helvetica", Font.BOLD, this.size);

		String textoScore = this.txt + valor;

		g.setFont(fuente);
		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.drawString(textoScore, this.x, this.y);
	}

	public static int check_nuevoRecord(int marcador, int hi) {

		if (marcador > hi) {
			return marcador;
		}

		return hi;
	}
}
