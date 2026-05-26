// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class DDA {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public DDA(int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        float m;

        if (dx != 0) {
            m = (float) dy / dx;
        } else {
            m = 0;
        }

        if (Math.abs(m) <= 1) {

            float y = y1;

            int pasoX;

            if (x1 < x2) {
                pasoX = 1;
            } else {
                pasoX = -1;
            }

            for (int x = x1; x != x2 + pasoX; x += pasoX) {
                panel.pixel(x, Math.round(y), g);
                y += m;
            }

        }

        else {

            float x = x1;
            float incrementoX = 1 / m;
            int pasoY;

            if (y1 < y2) {
                pasoY = 1;
            } else {
                pasoY = -1;
            }

            for (int y = y1; y != y2 + pasoY; y += pasoY) {
                panel.pixel(Math.round(x), y, g);
                x += incrementoX;
            }
        }
    }

    public int getMaximo() {

        return Math.max(
                Math.max(Math.abs(x1), Math.abs(x2)),
                Math.max(Math.abs(y1), Math.abs(y2))
        );
    }
}
