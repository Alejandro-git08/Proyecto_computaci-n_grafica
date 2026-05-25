// Integrantes: Alejandro Santos, Omar Muñoz

package computacion_grafica;

import algoritmos.*;

import javax.swing.*;
import java.awt.*;

public class PanelDibujo extends JPanel {

    private DDA dda;
    private Bresenham bresenham;
    private Circunferencia circunferencia;
    private Elipse elipse;

    private String algoritmoActual = "";
    private int escala = 10;
    private int centroX;
    private int centroY;

    private JPanel panelFormulario;

    private JLabel lbl1;
    private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;

    private JTextField txt1;
    private JTextField txt2;
    private JTextField txt3;
    private JTextField txt4;

    private JButton btnDibujar;

    private JButton btnDDA;
    private JButton btnBresenham;
    private JButton btnCirculo;
    private JButton btnElipse;
    private JButton btnPresentacion;

    public PanelDibujo() {

        setLayout(null);

        crearBotones();

        crearFormulario();
    }

    private void crearBotones() {

        btnDDA = new JButton("DDA");
        btnBresenham = new JButton("Bresenham");
        btnCirculo = new JButton("Circunferencia");
        btnElipse = new JButton("Elipse");
        btnPresentacion = new JButton("Presentación");
        btnDDA.setBounds(20, 20, 120, 30);
        btnBresenham.setBounds(150, 20, 120, 30);
        btnCirculo.setBounds(280, 20, 150, 30);
        btnElipse.setBounds(440, 20, 120, 30);
        btnPresentacion.setBounds(570, 20, 150, 30);

        add(btnDDA);
        add(btnBresenham);
        add(btnCirculo);
        add(btnElipse);
        add(btnPresentacion);

        btnDDA.addActionListener(e -> {

            algoritmoActual = "DDA";

            configurarFormularioLinea();
        });

        btnBresenham.addActionListener(e -> {

            algoritmoActual = "BRESENHAM";

            configurarFormularioLinea();
        });

        btnCirculo.addActionListener(e -> {

            algoritmoActual = "CIRCULO";

            configurarFormularioCirculo();
        });

        btnElipse.addActionListener(e -> {

            algoritmoActual = "ELIPSE";

            configurarFormularioElipse();
        });

        btnPresentacion.addActionListener(e -> {

            JOptionPane.showMessageDialog(null,

                    "UNIVERSIDAD TECNOLÓGICA DE PANAMÁ\n"
            		
            				+ "INGENIERÍA DE SOFTWARE\n\n"

                            + "Proyecto #1\n"

                            + "Algoritmos de Discretización\n\n"
                            
                            +"Profesor: \n"
                            
                            +"Mark Tuck\n\n"

                            + "Integrantes:\n"

                            + "Alejandro Santos\n"
                            
            				+"Omar Muñoz\n\n"
            				
            				+"Grupo: 1SF142\n\n"
            				
            				+"2026");
        });
    }


    private void crearFormulario() {

        panelFormulario = new JPanel();

        panelFormulario.setLayout(null);

        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos")
        );

        panelFormulario.setBounds(20, 70, 250, 250);

        lbl1 = new JLabel();
        lbl2 = new JLabel();
        lbl3 = new JLabel();
        lbl4 = new JLabel();
        txt1 = new JTextField();
        txt2 = new JTextField();
        txt3 = new JTextField();
        txt4 = new JTextField();

        btnDibujar = new JButton("Dibujar");

        lbl1.setBounds(20, 30, 80, 25);
        txt1.setBounds(100, 30, 100, 25);
        lbl2.setBounds(20, 70, 80, 25);
        txt2.setBounds(100, 70, 100, 25);
        lbl3.setBounds(20, 110, 80, 25);
        txt3.setBounds(100, 110, 100, 25);
        lbl4.setBounds(20, 150, 80, 25);
        txt4.setBounds(100, 150, 100, 25);

        btnDibujar.setBounds(60, 190, 120, 30);

        panelFormulario.add(lbl1);
        panelFormulario.add(txt1);
        panelFormulario.add(lbl2);
        panelFormulario.add(txt2);
        panelFormulario.add(lbl3);
        panelFormulario.add(txt3);
        panelFormulario.add(lbl4);
        panelFormulario.add(txt4);

        panelFormulario.add(btnDibujar);

        add(panelFormulario);

        btnDibujar.addActionListener(e -> dibujarFigura());
    }

    private void configurarFormularioLinea() {

        lbl1.setText("x1:");
        lbl2.setText("y1:");
        lbl3.setText("x2:");
        lbl4.setText("y2:");
        lbl4.setVisible(true);
        txt4.setVisible(true);

        limpiarCampos();
    }

    private void configurarFormularioCirculo() {

        lbl1.setText("xc:");
        lbl2.setText("yc:");
        lbl3.setText("Radio:");
        lbl4.setVisible(false);
        txt4.setVisible(false);

        limpiarCampos();
    }

    private void configurarFormularioElipse() {

        lbl1.setText("xc:");
        lbl2.setText("yc:");
        lbl3.setText("rx:");
        lbl4.setText("ry:");
        lbl4.setVisible(true);
        txt4.setVisible(true);

        limpiarCampos();
    }

    private void dibujarFigura() {

        if (!validarCampos()) {

            return;
        }

        switch (algoritmoActual) {

            case "DDA":

                dda = new DDA(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3),
                        obtenerEntero(txt4)
                );

                ajustarEscala(dda.getMaximo());

                break;

            case "BRESENHAM":

                bresenham = new Bresenham(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3),
                        obtenerEntero(txt4)
                );

                ajustarEscala(bresenham.getMaximo());

                break;

            case "CIRCULO":

                circunferencia = new Circunferencia(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3)
                );

                ajustarEscala(circunferencia.getMaximo());

                break;

            case "ELIPSE":

                elipse = new Elipse(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3),
                        obtenerEntero(txt4)
                );

                ajustarEscala(elipse.getMaximo());

                break;

            default:

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un algoritmo"
                );

                return;
        }

        repaint();
    }


    private boolean validarCampos() {

        if (txt1.getText().trim().isEmpty()
                || txt2.getText().trim().isEmpty()
                || txt3.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios"
            );

            return false;
        }

        if (txt4.isVisible()
                && txt4.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Todos los campos son obligatorios"
            );

            return false;
        }

        if (!esNumero(txt1.getText())
                || !esNumero(txt2.getText())
                || !esNumero(txt3.getText())) {

            JOptionPane.showMessageDialog(
                    null,
                    "Solo se permiten números enteros"
            );

            return false;
        }

        if (txt4.isVisible()
                && !esNumero(txt4.getText())) {

            JOptionPane.showMessageDialog(
                    null,
                    "Solo se permiten números enteros"
            );

            return false;
        }

        return true;
    }

    private boolean esNumero(String texto) {

        try {

            Integer.parseInt(texto);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }

    private int obtenerEntero(JTextField campo) {

        return Integer.parseInt(campo.getText());
    }

    private void limpiarCampos() {

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
    }


    private void ajustarEscala(int maximo) {

        centroX = 700;
        centroY = 450;

        int margen = 100;
        int anchoDisponible = 450;
        int altoDisponible = 700;
        int escalaX = anchoDisponible / (maximo * 2 + 1);
        int escalaY = altoDisponible / (maximo * 2 + 1);

        escala = Math.min(escalaX, escalaY);

        if (escala < 2) {

            escala = 2;
        }
    }

    public void pixel(int x, int y, Graphics g) {

        int px = centroX + (x * escala);
        int py = centroY - (y * escala);

        g.fillRect(px, py, escala, escala);
    }

    private void dibujarPlano(Graphics g) {

        g.setColor(Color.LIGHT_GRAY);

        for (int x = centroX; x < getWidth(); x += escala) {

            g.drawLine(x, 0, x, getHeight());
        }

        for (int x = centroX; x > 300; x -= escala) {

            g.drawLine(x, 0, x, getHeight());
        }

        for (int y = centroY; y < getHeight(); y += escala) {

            g.drawLine(300, y, getWidth(), y);
        }

        for (int y = centroY; y > 0; y -= escala) {

            g.drawLine(300, y, getWidth(), y);
        }

        g.setColor(Color.BLACK);

        g.drawLine(300, centroY, getWidth(), centroY);

        g.drawLine(centroX, 0, centroX, getHeight());
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        dibujarPlano(g);

        switch (algoritmoActual) {

            case "DDA":

                if (dda != null) {

                    dda.dibujar(g, this);
                }

                break;

            case "BRESENHAM":

                if (bresenham != null) {

                    bresenham.dibujar(g, this);
                }

                break;

            case "CIRCULO":

                if (circunferencia != null) {

                    circunferencia.dibujar(g, this);
                }

                break;

            case "ELIPSE":

                if (elipse != null) {

                    elipse.dibujar(g, this);
                }

                break;
        }
    }
}
