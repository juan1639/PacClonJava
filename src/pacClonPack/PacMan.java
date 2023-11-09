package pacClonPack;

import java.awt.Color;
import java.awt.Graphics;

// ===============================================================
public class PacMan {

	public static final int DIVIDIR_TILE_ENTRE = 10;

	private int x;
	private int y;
	private int tileX;
	private int tileY;
	private int pulsada;
	private int pulsadaConfirmada;

	private int[][] direcciones = {
		{1, 0, 0},
		{-1, 0, 2},
		{0, -1, 4},
		{0, 1, 6},
	};
	private int nextAnima;
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

		this.nextAnima = 0;

		this.velXY[0] = this.direcciones[this.pulsada][0];
		this.velXY[1] = this.direcciones[this.pulsada][1];

		this.vel = (int) (this.tileY / DIVIDIR_TILE_ENTRE);
		this.avanzar = true;
	}

	public void dibuja(Graphics g, int[][] matriz, Settings sett) {

		this.pulsada = actualiza_teclado(sett);
		actualiza(matriz);

		g.setColor(Color.yellow);
		g.fillOval(this.x, this.y, this.tileX, this.tileY);

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
