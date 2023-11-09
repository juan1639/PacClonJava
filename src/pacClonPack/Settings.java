package pacClonPack;

// ===============================================================
public class Settings {

    public static final int COLUMNAS = 19;
    public static final int FILAS = 15;

	private int columnas = 19;
    private int filas = 15;
    private int tileX = 50;
    private int tileY = 50;
    public int resX;
    public int resY;

    public int FPS = 60;

    // iniSprites {x, y, id, direccion (0 dcha, 1 izda, 2 up 3 do)}
    private int[][] iniSprites = {
        {9, 4, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 2, 1},
        {0, 0, 3, 1}
    };

    private Boolean reinstanciar_pacmanFantasmas = true;

    private int[] colorFondos = {143, 127, 9, 70, 62, 4};

    private int puntos = 0;
    private int nivel = 1;
    private int hiScore = 20000;

    private int contador_optionPane = 9;

    // ------------------------------------------------
    // Funcion Constructora
    // ------------------------------------------------
    public Settings() {
        this.columnas = columnas;
        this.filas = filas;
        this.tileX = tileX;
        this.tileY = tileY;

        this.FPS = FPS;

        this.iniSprites = iniSprites;

        this.colorFondos = colorFondos;

        this.puntos = puntos;
        this.nivel = nivel;
        this.hiScore = hiScore;

        this.contador_optionPane = contador_optionPane;

        this.resX = (int) (this.columnas * this.tileX);
        this.resY = (int) (this.filas * this.tileY);
    }

    // ------------------------------------------------
    // Instancias Controles, Estado, Laberinto
    // ------------------------------------------------
    public Controles controles = new Controles();
    public Estado estado = new Estado();
    public Laberinto laberinto = new Laberinto();

    // ================================================
    public class Controles {

    	public Boolean izquierda = false;
	    public Boolean derecha = false;
	    public Boolean arriba = false;
	    public Boolean abajo = false;

        // ---------------------------------
        public Controles() {
            this.izquierda = izquierda;
            this.derecha = derecha;
            this.arriba = arriba;
            this.abajo = abajo;
        }
    }

    // ================================================
    public class Estado {

    	public Boolean preJuego = false;
        public Boolean preparado = false;
    	public Boolean enJuego = true;
        public Boolean nivelSuperado = false;
    	public Boolean gameOver = false;

        // ---------------------------------
        public Estado() {
            this.preJuego = preJuego;
            this.preparado = preparado;
            this.enJuego = enJuego;
            this.nivelSuperado = nivelSuperado;
            this.gameOver = gameOver;
        }
    }

    // ================================================
    public class Laberinto {

        // columnas 19 x 15 filas, 19 x 13 (12 visibles)

        public int[][] matriz = {
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
            {9,5,1,1,1,1,1,1,1,9,1,1,1,1,1,1,1,5,9},
            {9,1,9,9,1,9,9,9,1,9,1,9,9,9,1,9,9,1,9},

            {9,1,9,9,1,9,9,9,1,9,1,9,9,9,1,9,9,1,9},
            {9,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,9},
            {9,1,9,9,1,9,1,9,9,9,9,9,1,9,1,9,9,1,9},

            {9,1,1,1,1,9,1,1,1,9,1,1,1,1,1,1,1,1,9},
            {9,9,9,9,1,9,9,9,1,9,1,9,9,9,1,9,9,9,9},
            {9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,9},

            {9,5,9,9,1,9,1,9,9,9,9,9,1,9,1,9,9,5,9},
            {9,1,9,9,1,9,1,9,9,9,9,9,1,9,1,9,9,1,9},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},

            {9,1,9,9,1,9,9,9,9,1,9,9,9,9,1,9,9,1,9},
            {9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,9},
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
        };

        // -----------------------------------------
        public Laberinto() {
            this.matriz = matriz;
        }
    }

    // Getters & Setters --------------------------------
    public int getColumnas() {
        return this.columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilas() {
        return this.filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getTileX() {
        return this.tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return this.tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public int[][] getIniSprites() {
        return this.iniSprites;
    }

    public void setIniSprites(int[][] iniSprites) {
        this.iniSprites = iniSprites;
    }

    public Boolean getReinstanciar_pacmanFantasmas() {
        return this.reinstanciar_pacmanFantasmas;
    }

    public void setReinstanciar_pacmanFantasmas(Boolean reinstanciar_pacmanFantasmas) {
        this.reinstanciar_pacmanFantasmas = reinstanciar_pacmanFantasmas;
    }

    public int[] getColorFondos() {
        return this.colorFondos;
    }

    public void setColorFondos(int[] colorFondos) {
        this.colorFondos = colorFondos;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getHiScore() {
        return this.hiScore;
    }

    public void setHiScore(int hiScore) {
        this.hiScore = hiScore;
    }

    public int getContador_optionPane() {
        return this.contador_optionPane;
    }

    public void setContador_optionPane(int contador_optionPane) {
        this.contador_optionPane = contador_optionPane;
    }
}
