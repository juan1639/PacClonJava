package pacClonPack;

import java.awt.Color;
import java.awt.Graphics;

// ===============================================================
public class Puntitos {

	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private int size;

	public Puntitos(int x, int y, int tileX, int tileY) {

		this.tileX = tileX;
		this.tileY = tileY;
		this.x = x * this.tileX;
		this.y = y * this.tileY;
		this.size = 10;
	}

	public void dibuja(Graphics g) {

		int[] rgb = {230, 230, 230};

		int sz = (int) (this.size / 2);
		int centroX = (int) (this.tileX / 2);
		int centroY = (int) (this.tileY / 2);

		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.fillOval(this.x + centroX - sz, this.y + centroY - sz, sz * 2, sz * 2);
	}
}
