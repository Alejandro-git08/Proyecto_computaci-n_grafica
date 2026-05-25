// Integrantes: Alejandro Santos, Omar Muñoz

package computacion_grafica;

import javax.swing.*;

public class Ventana extends JFrame {

    public Ventana() {

        setTitle("Proyecto Algoritmos de Discretización");

        setSize(1200, 900);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        add(new PanelDibujo());

        setVisible(true);
    }
}
