// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class Bresenham {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int dx;
    private int dy;
    private int sx;
    private int sy;
    private int err;


    public Bresenham(int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        inicializar();
    }


    private void inicializar() {

        dx = Math.abs(x2 - x1);
        dy = Math.abs(y2 - y1);
        sx = x1 < x2 ? 1 : -1;
        sy = y1 < y2 ? 1 : -1;
        err = dx - dy;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        int x = x1;
        int y = y1;

        while (true) {

            panel.pixel(x, y, g);

            if (x == x2 && y == y2) {

                break;
            }

            int e2 = 2 * err;

            if (e2 > -dy) {

                err -= dy;

                x += sx;
            }

            if (e2 < dx) {

                err += dx;

                y += sy;
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