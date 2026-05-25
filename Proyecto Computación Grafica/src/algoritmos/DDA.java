// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class DDA {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int dx;
    private int dy;
    private int pasos;
    private float incrementoX;
    private float incrementoY;

    public DDA(int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        calcularValores();
    }

    private void calcularValores() {

        dx = x2 - x1;

        dy = y2 - y1;

        pasos = Math.max(Math.abs(dx), Math.abs(dy));

        incrementoX = (float) dx / pasos;

        incrementoY = (float) dy / pasos;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        float x = x1;

        float y = y1;

        for (int i = 0; i <= pasos; i++) {

            panel.pixel(Math.round(x), Math.round(y), g);

            x += incrementoX;

            y += incrementoY;
        }
    }

    public int getMaximo() {

        return Math.max(

                Math.max(Math.abs(x1), Math.abs(x2)),

                Math.max(Math.abs(y1), Math.abs(y2))
        );
    }
}
