// Integrantes: Alejandro Santos, Omar Muñoz
 
package computacion_grafica;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Ventana();
        });

    }
}