package pacClonPack;

import java.awt.Color;
import java.awt.Graphics;

// ===============================================================
public class Puntitos {

	public static final int INI_SIZE_GORDOS = 35;
	public static final int SIZE_NORMALES = 10;

	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private Boolean gordos;
	private int size;

	public Puntitos(int x, int y, int tileX, int tileY, Boolean gordos) {

		this.tileX = tileX;
		this.tileY = tileY;
		this.x = x * this.tileX;
		this.y = y * this.tileY;
		this.gordos = gordos;

		this.size = this.gordos ? INI_SIZE_GORDOS : SIZE_NORMALES; 
	}

	public void dibuja(Graphics g) {

		int[] rgb = {220, 220, 220, 200, 255, 9};

		int sz = (int) (this.size / 2);
		int centroX = (int) (this.tileX / 2);
		int centroY = (int) (this.tileY / 2);

		if (this.gordos) {
			g.setColor(new Color(rgb[3], rgb[4], rgb[5]));
			this.size = cambiar_sizeGordos(this.size);

		} else {
			g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		}

		g.fillOval(this.x + centroX - sz, this.y + centroY - sz, sz * 2, sz * 2);
	}

	private int cambiar_sizeGordos(int size) {

		size --;

		if (size <= 2) {
			size = INI_SIZE_GORDOS;
		}

		return size;
	}
}
