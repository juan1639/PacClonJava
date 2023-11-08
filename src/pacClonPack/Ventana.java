package pacClonPack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// ===============================================================
public class Ventana extends JPanel implements ActionListener {

    private Settings settings;

    private int newGame;
    private int game_over_pane;

    private ArrayList<Pared> pared = new ArrayList<>();
    private ArrayList<Puntitos> puntitos = new ArrayList<>();

    //private Pared[][] pared = new Pared[Settings.FILAS][Settings.COLUMNAS];
    //private Puntitos[][] puntitos = new Puntitos[Settings.FILAS][Settings.COLUMNAS];

    private Marcadores marcador;
    private Marcadores nivel;
    private Marcadores hi;

    private Timer timer;

    // --------------------------------------------------
    public Ventana() {
        
        inicializa();
    }
    
    private void inicializa() {

        load_settings();

        addKeyListener(new TAdapter());

        int[] rgb = settings.getColorFondos();
        setBackground(new Color(rgb[3], rgb[4], rgb[5]));
        setFocusable(true);
        setPreferredSize(new Dimension(settings.resX, settings.resY));

        comenzar();
    }

    private void load_settings() {

        settings = new Settings();
    }

    private void comenzar() {

        instanciar_elementos_laberinto();

        marcador = new Marcadores(settings.getTileY(), (int) (settings.resX / 20), (int) (settings.getTileY() / 1.1), "Score: ");
        hi = new Marcadores(settings.getTileY(), (int) (settings.resX / 1.6), (int) (settings.getTileY() / 1.1), "Record: ");

        timer = new Timer((int) (1000 / settings.FPS), this);
        timer.start();
    }

    private void instanciar_elementos_laberinto() {

        int filas = settings.getFilas();
        int columnas = settings.getColumnas();

        int ancho = settings.getTileX();
        int alto = settings.getTileY();

        for (int i = 0; i < filas; i ++) {
            for (int ii = 0; ii < columnas; ii ++) {

                int tile = settings.laberinto.matriz[i][ii];
                //System.out.println("..." + settings.laberinto.matriz[i][ii]);

                if (tile == 9) {
                    pared.add(new Pared(ii, i, ancho, alto));

                } else if (tile == 1) {
                    puntitos.add(new Puntitos(ii, i, ancho, alto));

                } else if (tile == 5) {
                    pared.add(new Pared(ii, i, 5, 5));
                    
                } else {
                    pared.add(new Pared(ii, i, 2, 2));
                }

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        renderiza(g);
    }
    
    private void renderiza(Graphics g) {

        for (Pared tile: pared) {
            tile.dibuja(g);
        }

        for (Puntitos tile: puntitos) {
            tile.dibuja(g);
        }

        marcador.dibuja(g, settings.getPuntos());
        hi.dibuja(g, settings.getHiScore());

        Toolkit.getDefaultToolkit().sync();      
    }

    private void pre_juegoDialog() {

        if (settings.getContador_optionPane() <= 0) {
            return;
        }

        int contador = settings.getContador_optionPane();
        contador --;
        settings.setContador_optionPane(contador);

        if (settings.estado.preJuego) {

            if (settings.getContador_optionPane() == 0) {

                newGame = JOptionPane.showConfirmDialog(this, "Comenzar nueva partida...", "COMENZAR", JOptionPane.CLOSED_OPTION);

                if (newGame == JOptionPane.OK_OPTION) {

                    settings.estado.enJuego = true;
                    settings.estado.preJuego = false;
                    Toolkit.getDefaultToolkit().beep();
                }
            }

        } else if (settings.estado.gameOver) {

            if (settings.getContador_optionPane() == 0) {

                game_over_pane = JOptionPane.showConfirmDialog(this, "Volver a jugar?");

                if (game_over_pane == JOptionPane.NO_OPTION || game_over_pane == JOptionPane.CANCEL_OPTION) {

                    Toolkit.getDefaultToolkit().beep();
                    System.exit(0);

                } else if (game_over_pane == JOptionPane.YES_OPTION) {

                    //reset_rejugar();
                }
            }
        }
    }

    private void reset_falseControles() {
        settings.controles.izquierda = false;
        settings.controles.derecha = false;
        settings.controles.arriba = false;
        settings.controles.abajo = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //pre_juegoDialog();

        if (settings.estado.enJuego) {
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (settings.estado.enJuego) {

                if (key == KeyEvent.VK_LEFT) {
                    reset_falseControles();
                    settings.controles.izquierda = true;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    reset_falseControles();
                    settings.controles.derecha = true;
                }

                if (key == KeyEvent.VK_UP) {
                    reset_falseControles();
                    settings.controles.arriba = true;
                }

                if (key == KeyEvent.VK_DOWN) {
                    reset_falseControles();
                    settings.controles.abajo = true;
                    System.out.println("Abajo");
                }
            }

            if (key == KeyEvent.VK_ESCAPE) {
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
            }
        }
    }
}
