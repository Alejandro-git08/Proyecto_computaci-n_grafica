// Integrantes: Alejandro Santos, Omar Muñoz

package computacion_grafica;

import algoritmos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class PanelDibujo extends JPanel {


    private DDA dda;
    private Bresenham bresenham;
    private Circunferencia circunferencia;
    private Elipse elipse;


    private String algoritmoActual = "";

    private int escala = 20;
    private int centroX = 600;
    private int centroY = 450;

    private int ultimoMouseX;
    private int ultimoMouseY;

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
    private JButton btnZoomMas;
    private JButton btnZoomMenos;

    public PanelDibujo() {

        setLayout(null);

        setBackground(Color.WHITE);

        crearBotones();
        crearFormulario();
        agregarZoomMouse();
        agregarMovimientoPlano();
    }

    private void crearBotones() {

        btnDDA = new JButton("DDA");
        btnBresenham = new JButton("Bresenham");
        btnCirculo = new JButton("Circunferencia");
        btnElipse = new JButton("Elipse");
        btnPresentacion = new JButton("Presentación");
        btnZoomMas = new JButton("+");
        btnZoomMenos = new JButton("-");

        btnDDA.setBounds(20, 20, 120, 30);
        btnBresenham.setBounds(150, 20, 120, 30);
        btnCirculo.setBounds(280, 20, 150, 30);
        btnElipse.setBounds(440, 20, 120, 30);
        btnPresentacion.setBounds(570, 20, 150, 30);
        btnZoomMas.setBounds(740, 20, 50, 30);
        btnZoomMenos.setBounds(800, 20, 50, 30);


        add(btnDDA);
        add(btnBresenham);
        add(btnCirculo);
        add(btnElipse);
        add(btnPresentacion);
        add(btnZoomMas);
        add(btnZoomMenos);

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

            JOptionPane.showMessageDialog(
                    null, "UNIVERSIDAD TECNOLÓGICA DE PANAMÁ\n"
                    
            				+ "INGENIERÍA DE SOFTWARE\n\n"

                            + "Proyecto #1\n"

                            + "Algoritmos de Discretización\n\n"
                            
                            +"Profesor: \n"
                            
                            +"Mark Tuck\n\n"

                            + "Integrantes:\n"

                            + "Alejandro Santos\n"
                            
            				+ "Omar Muñoz\n\n"
            				
            				+ "Grupo: 1SF142\n\n"
            				
            				+ "2026"
            );
        });

        btnZoomMas.addActionListener(e -> {

            if (escala < 80) {
                escala += 2;
                repaint();
            }
        });


        btnZoomMenos.addActionListener(e -> {

            if (escala > 5) {
                escala -= 2;
                repaint();
            }
        });
    }


    private void crearFormulario() {

        panelFormulario = new JPanel();

        panelFormulario.setLayout(null);

        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos"));

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

                break;

            case "BRESENHAM":

                bresenham = new Bresenham(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3),
                        obtenerEntero(txt4)
                );

                break;

            case "CIRCULO":

                circunferencia = new Circunferencia(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3)
                );

                break;

            case "ELIPSE":

                elipse = new Elipse(

                        obtenerEntero(txt1),
                        obtenerEntero(txt2),
                        obtenerEntero(txt3),
                        obtenerEntero(txt4)
                );

                break;

            default:

                JOptionPane.showMessageDialog(null, "Seleccione un algoritmo");

                return;
        }

        repaint();
    }


    private boolean validarCampos() {

        if (txt1.getText().trim().isEmpty() || txt2.getText().trim().isEmpty() || txt3.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            return false;
        }

        if (txt4.isVisible() && txt4.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            return false;
        }

        if (!esNumero(txt1.getText()) || !esNumero(txt2.getText()) || !esNumero(txt3.getText())) {
            JOptionPane.showMessageDialog(null, "Solo se permiten números enteros");
            return false;
        }

        if (txt4.isVisible() && !esNumero(txt4.getText())) {
            JOptionPane.showMessageDialog(null, "Solo se permiten números enteros");
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


    public void pixel(int x, int y, Graphics g) {

        int px = centroX + (x * escala) - (escala / 2);

        int py = centroY - (y * escala) - (escala / 2);

        g.fillRect(px, py, escala, escala);
    }


    private void agregarZoomMouse() {

        addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                if (e.getWheelRotation() < 0) {

                    if (escala < 80) {

                        escala += 2;
                    }

                } else {

                    if (escala > 5) {

                        escala -= 2;
                    }
                }

                repaint();
            }
        });
    }

    private void agregarMovimientoPlano() {

        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                ultimoMouseX = e.getX();

                ultimoMouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {

                int dx = e.getX() - ultimoMouseX;

                int dy = e.getY() - ultimoMouseY;

                centroX += dx;

                centroY += dy;

                ultimoMouseX = e.getX();

                ultimoMouseY = e.getY();

                repaint();
            }
        };

        addMouseListener(mouseAdapter);

        addMouseMotionListener(mouseAdapter);
    }

    private void dibujarPlano(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(new Color(220, 220, 220));

        for (int x = centroX - (escala / 2);

             x < getWidth();

             x += escala) {

            g2.drawLine(x, 0, x, getHeight());
        }

        for (int x = centroX - (escala / 2);

             x > 0;

             x -= escala) {

            g2.drawLine(x, 0, x, getHeight());
        }

        for (int y = centroY - (escala / 2);

             y < getHeight();

             y += escala) {

            g2.drawLine(0, y, getWidth(), y);
        }

        for (int y = centroY - (escala / 2);

             y > 0;

             y -= escala) {

            g2.drawLine(0, y, getWidth(), y);
        }


        g2.setColor(Color.BLACK);

        g2.setStroke(new BasicStroke(2));

        g2.drawLine(0, centroY, getWidth(), centroY);

        g2.drawLine(centroX, 0, centroX, getHeight());


        if (escala >= 15) {

            g2.setColor(Color.GRAY);

            // X POSITIVOS

            for (int x = centroX + escala;

                 x < getWidth();

                 x += escala) {

                int valorX = (x - centroX) / escala;

                g2.drawString(
                        String.valueOf(valorX),
                        x - 5,
                        centroY + 15
                );
            }

            // X NEGATIVOS

            for (int x = centroX - escala;

                 x > 0;

                 x -= escala) {

                int valorX = (x - centroX) / escala;

                g2.drawString(
                        String.valueOf(valorX),
                        x - 5,
                        centroY + 15
                );
            }

            // Y POSITIVOS

            for (int y = centroY - escala;

                 y > 0;

                 y -= escala) {

                int valorY = -(y - centroY) / escala;

                g2.drawString(
                        String.valueOf(valorY),
                        centroX + 8,
                        y + 5
                );
            }

            // Y NEGATIVOS

            for (int y = centroY + escala;

                 y < getHeight();

                 y += escala) {

                int valorY = -(y - centroY) / escala;

                g2.drawString(
                        String.valueOf(valorY),
                        centroX + 8,
                        y + 5
                );
            }
        }

        g2.setColor(Color.RED);

        g2.fillRect(
                centroX - (escala / 2),
                centroY - (escala / 2),
                escala,
                escala
        );

        g2.setColor(Color.BLACK);

        g2.drawString(
                "(0,0)",
                centroX + 10,
                centroY - 10
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        dibujarPlano(g);
        
        //azul por el contraste, te gusta?
        g.setColor(Color.BLUE);

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