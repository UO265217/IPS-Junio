package giis.demo.logistica.view;

import giis.demo.logistica.model.LogisticaModel;
import giis.demo.logistica.model.dto.EnvioDTO;
import giis.demo.logistica.model.dto.HistorialEnviosDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SeguimientoView extends JFrame {

    private JTextField txtNumeroSeguimiento;
    private JButton btnRastrear;
    private JTextArea textAreaResultados;
    private JScrollPane scrollPaneResultados;

    private LogisticaModel model;

    public SeguimientoView() {
        this.model = LogisticaModel.getInstance();
        setTitle("Consultar Seguimiento de Envío");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        addListeners();
    }

    private void initComponents() {

        JPanel panelEntrada = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEntrada.add(new JLabel("Número de Seguimiento:"));
        txtNumeroSeguimiento = new JTextField(20);
        panelEntrada.add(txtNumeroSeguimiento);
        btnRastrear = new JButton("Rastrear");
        panelEntrada.add(btnRastrear);
        textAreaResultados = new JTextArea();
        textAreaResultados.setEditable(false);
        textAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPaneResultados = new JScrollPane(textAreaResultados);
        scrollPaneResultados.setBorder(BorderFactory.createTitledBorder("Resultados del Seguimiento"));
        setLayout(new BorderLayout(5, 5));
        add(panelEntrada, BorderLayout.NORTH);
        add(scrollPaneResultados, BorderLayout.CENTER);
    }

    private void addListeners() {
        btnRastrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });
    }

    private void realizarBusqueda() {
        String numSeguimientoStr = txtNumeroSeguimiento.getText().trim();
        textAreaResultados.setText("");

        if (numSeguimientoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número de seguimiento.", "Entrada Requerida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int numeroSeguimiento = Integer.parseInt(numSeguimientoStr);
            EnvioDTO envio = model.getEnvioPorNumeroSeguimiento(numeroSeguimiento); // Ahora devuelve EnvioDTO

            if (envio == null) {
                textAreaResultados.setText("No se encontró ningún envío con el número: " + numeroSeguimiento);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("--- Información del Envío ---\n");

                sb.append(String.format("Nº Seguimiento: %s\n", envio.getNumero_seguimiento()));
                sb.append(String.format("Origen:         %s\n", envio.getOrigen()));
                sb.append(String.format("Destino:        %s\n", envio.getDestino()));
                sb.append(String.format("Estado Actual:  %s\n", envio.getEstado_actual()));

                sb.append("\n--- Historial de Eventos ---\n");

                List<HistorialEnviosDTO> historial = model.getHistorialEnvio(envio.getId()); // Ahora devuelve List<HistorialEnviosDTO>

                if (historial.isEmpty()) {
                    sb.append("No hay eventos de historial registrados para este envío.\n");
                } else {
                    for (HistorialEnviosDTO evento : historial) {
                        sb.append(String.format("%s - %s\n",
                                evento.getFecha_hora(),
                                evento.getDescripcion_evento()));
                    }
                }
                textAreaResultados.setText(sb.toString());
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El número de seguimiento debe ser un valor numérico.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la solicitud: " + ex.getMessage(), "Error Inesperado", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
