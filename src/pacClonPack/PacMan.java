package pacClonPack;

import java.awt.Color;
import java.awt.Graphics;

// ===============================================================
public class PacMan {

	public static final int DIVIDIR_TILE_ENTRE = 10;
	public static final int NRO_ANIMAS_BOCA = 10;

	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private int pulsada;
	private int pulsadaConfirmada;

	private int[][] direcciones = {
		{1, 0, 80, 190, 45, 270},
		{-1, 0, 260, 190, 225, 270},
		{0, -1, 170, 190, 135, 270},
		{0, 1, 350, 190, 315, 270},
	};
	private int contador_anima;
	private int step;
	private int iniRad;

	private int[] velXY = {0, 0};
	private int vel;
	private Boolean avanzar;

	public PacMan(int x, int y, int tileX, int tileY, int dirPorDefecto) {

		this.tileX = tileX;
		this.tileY = tileY;
		this.x = x * this.tileX;
		this.y = y * this.tileY;
		this.pulsada = dirPorDefecto; 

		this.pulsadaConfirmada = this.pulsada;
		this.direcciones = direcciones;

		this.contador_anima = NRO_ANIMAS_BOCA;
		this.step = (int) ((85 + 5) / NRO_ANIMAS_BOCA);
		this.iniRad = iniRad;

		this.velXY[0] = this.direcciones[this.pulsada][0];
		this.velXY[1] = this.direcciones[this.pulsada][1];

		this.vel = (int) (this.tileY / DIVIDIR_TILE_ENTRE);
		this.avanzar = true;
	}

	public void dibuja(Graphics g, int[][] matriz, Settings sett) {

		int[] rgb = {218, 225, 5};

		this.pulsada = actualiza_teclado(sett);
		actualiza(matriz);

		this.contador_anima --;

		if (this.contador_anima <= 1) {
			this.contador_anima = NRO_ANIMAS_BOCA;
		}

		int iniR = this.iniRad - this.contador_anima * this.step;
		int finR = this.direcciones[0][3] + (this.contador_anima * this.step) * 2;

		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.fillArc(this.x, this.y, this.tileX, this.tileY, iniR, finR);

	}

	private void actualiza(int[][] matriz) {

		if (this.x % this.tileX == 0 && this.y % this.tileY == 0) {

			int x = (int) (this.x / this.tileX);
			int y = (int) (this.y / this.tileY);

			Boolean colisionPulsada = check_colisionLaberintoPulsada(x, y, matriz);
			Boolean colisionVelXY = check_colisionLaberintoVelXY(x, y, matriz);

			if (!colisionPulsada) {

				this.avanzar = true;
				this.pulsadaConfirmada = this.pulsada;
				this.velXY[0] = this.direcciones[this.pulsada][0];
				this.velXY[1] = this.direcciones[this.pulsada][1];
				this.iniRad = this.direcciones[this.pulsada][2];

			} else if (!colisionVelXY) {

				this.avanzar = true;

			} else {

				this.avanzar = false;
			}
		}

		if (this.avanzar) {
			this.x += this.velXY[0] * this.vel;
			this.y += this.velXY[1] * this.vel;
		}
	}

	private Boolean check_colisionLaberintoPulsada(int x, int y, int[][] matriz) {

		int velX = this.direcciones[this.pulsada][0];
		int velY = this.direcciones[this.pulsada][1];

		if (matriz[y + velY][x + velX] == 9) {
			return true;
		}

		return false;
	}

	private Boolean check_colisionLaberintoVelXY(int x, int y, int[][] matriz) {

		if (matriz[y + this.velXY[1]][x + this.velXY[0]] == 9) {
			return true;
		}

		return false;
	}

	private int actualiza_teclado(Settings sett) {

		if (sett.controles.izquierda) {
			return 1;

		} else if (sett.controles.derecha) {
			return 0;

		} else if (sett.controles.arriba) {
			return 2;

		} else if (sett.controles.abajo) {
			return 3;
		}

		return 0;
	}
}
