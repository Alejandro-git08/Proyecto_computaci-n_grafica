// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class Circunferencia {

    private int xc;
    private int yc;
    private int radio;
    private int x;
    private int y;
    private int p;

    public Circunferencia(int xc, int yc, int radio) {

        this.xc = xc;

        this.yc = yc;

        this.radio = radio;

        inicializar();
    }

    private void inicializar() {

        x = 0;

        y = radio;

        p = 1 - radio;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        dibujarSimetria(g, panel);

        while (x < y) {

            x++;

            if (p < 0) {

                p = p + 2 * x + 1;

            } else {

                y--;

                p = p + 2 * (x - y) + 1;
            }

            dibujarSimetria(g, panel);
        }
    }

    private void dibujarSimetria(Graphics g, PanelDibujo panel) {

        panel.pixel(xc + x, yc + y, g);

        panel.pixel(xc - x, yc + y, g);

        panel.pixel(xc + x, yc - y, g);

        panel.pixel(xc - x, yc - y, g);

        panel.pixel(xc + y, yc + x, g);

        panel.pixel(xc - y, yc + x, g);

        panel.pixel(xc + y, yc - x, g);

        panel.pixel(xc - y, yc - x, g);
    }

    public int getMaximo() {

        return Math.max(

                Math.abs(xc) + radio,

                Math.abs(yc) + radio
        );
    }
}
