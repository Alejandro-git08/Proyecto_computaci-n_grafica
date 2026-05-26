// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class Elipse {

    private int xc;
    private int yc;
    private int rx;
    private int ry;

    public Elipse(int xc, int yc, int rx, int ry) {
        this.xc = xc;
        this.yc = yc;
        this.rx = rx;
        this.ry = ry;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        int x = 0;
        int y = ry;

        double rx2 = rx * rx;
        double ry2 = ry * ry;

        double dx = 2 * ry2 * x;
        double dy = 2 * rx2 * y;

        double p1 = ry2 - (rx2 * ry) + (0.25 * rx2);

        // REGION 1
        while (dx < dy) {

            dibujarSimetria(g, panel, x, y);

            x++;
            dx += 2 * ry2;

            if (p1 < 0) {
                p1 += dx + ry2;
            } else {
                y--;
                dy -= 2 * rx2;
                p1 += dx - dy + ry2;
            }
        }

        // REGION 2 
        double p2 =
                (ry2 * (x + 0.5) * (x + 0.5))
                        + (rx2 * (y - 1) * (y - 1))
                        - (rx2 * ry2);

        while (y >= 0) {

            dibujarSimetria(g, panel, x, y);

            y--;
            dy -= 2 * rx2;

            if (p2 > 0) {
                p2 += rx2 - dy;
            } else {
                x++;
                dx += 2 * ry2;
                p2 += dx - dy + rx2;
            }
        }
    }

    private void dibujarSimetria(Graphics g, PanelDibujo panel, int x, int y) {
        panel.pixel(xc + x, yc + y, g);
        panel.pixel(xc - x, yc + y, g);
        panel.pixel(xc + x, yc - y, g);
        panel.pixel(xc - x, yc - y, g);
    }

    public int getMaximo() {
        return Math.max(
                Math.abs(xc) + rx,
                Math.abs(yc) + ry
        );
    }
}