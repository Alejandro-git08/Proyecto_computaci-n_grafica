// Integrantes: Alejandro Santos, Omar Muñoz

package algoritmos;

import computacion_grafica.PanelDibujo;

import java.awt.*;

public class Circunferencia {

    private int xc;
    private int yc;
    private int radio;

    public Circunferencia(int xc, int yc, int radio) {
        this.xc = xc;
        this.yc = yc;
        this.radio = radio;
    }

    public void dibujar(Graphics g, PanelDibujo panel) {

        int x = 0;
        int y = radio;
        int p = 1 - radio;

        dibujarPuntos(g, panel, x, y);

        while (x < y) {

            x++;

            if (p < 0) {
                p = p + 2 * x + 1;
            } else {
                y--;
                p = p + 2 * (x - y) + 1;
            }

            dibujarPuntos(g, panel, x, y);
        }
    }

    private void dibujarPuntos(Graphics g, PanelDibujo panel, int x, int y) {

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
