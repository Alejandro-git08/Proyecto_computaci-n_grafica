// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class Elipse {

    private int xc;
    private int yc;
    private int rx;
    private int ry;
    private int x;
    private int y;
    private double dx;
    private double dy;
    private double p1;
    private double p2;

    public Elipse(int xc, int yc, int rx, int ry) {

        this.xc = xc;
        this.yc = yc;
        this.rx = rx;
        this.ry = ry;

        inicializar();
    }

    private void inicializar() {

        x = 0;

        y = ry;

        dx = 2 * ry * ry * x;

        dy = 2 * rx * rx * y;

        p1 = (ry * ry)

                - (rx * rx * ry)

                + (0.25 * rx * rx);
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        region1(g, panel);

        region2(g, panel);
    }

    private void region1(Graphics g, PanelDibujo panel) {

        while (dx < dy) {

            dibujarSimetria(g, panel);

            x++;

            dx += 2 * ry * ry;

            if (p1 < 0) {

                p1 += dx + (ry * ry);

            } else {

                y--;

                dy -= 2 * rx * rx;

                p1 += dx - dy + (ry * ry);
            }
        }

        p2 =
                ((ry * ry) * ((x + 0.5) * (x + 0.5)))

                        + ((rx * rx) * ((y - 1) * (y - 1)))

                        - (rx * rx * ry * ry);
    }

    private void region2(Graphics g, PanelDibujo panel) {

        while (y >= 0) {

            dibujarSimetria(g, panel);

            y--;

            dy -= 2 * rx * rx;

            if (p2 > 0) {

                p2 += (rx * rx) - dy;

            } else {

                x++;

                dx += 2 * ry * ry;

                p2 += dx - dy + (rx * rx);
            }
        }
    }

    private void dibujarSimetria(Graphics g, PanelDibujo panel) {

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