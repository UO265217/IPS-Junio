package giis.demo.logistica.view;

import giis.demo.logistica.model.LogisticaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudEnvioView extends JFrame {

    private JTextField txtOrigen;
    private JTextField txtDestino;

    private JButton btnConfirmar;

    private LogisticaModel model;

    public SolicitudEnvioView() {
        this.model = LogisticaModel.getInstance();

        setTitle("Solicitud Envio");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponent();
        addListeners();
    }

    private void initComponent() {
        setLayout(new BorderLayout(10, 10)); // Espaciado general

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Hacer que los JTextField se expandan horizontalmente

        // Fila 1: Origen
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        gbc.anchor = GridBagConstraints.EAST; // Alinear JLabel a la derecha
        formPanel.add(new JLabel("Origen:"), gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 0; // Fila 0
        gbc.weightx = 1.0; // Permitir que el JTextField tome espacio extra horizontal
        gbc.anchor = GridBagConstraints.WEST; // Alinear JTextField a la izquierda
        txtOrigen = new JTextField(20); // Especificar un número de columnas para el tamaño preferido
        formPanel.add(txtOrigen, gbc);

        // Fila 2: Destino
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 1; // Fila 1
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Destino:"), gbc);

        gbc.gridx = 1; // Columna 1
        gbc.gridy = 1; // Fila 1
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        txtDestino = new JTextField(20);
        formPanel.add(txtDestino, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnConfirmar = new JButton("Confirmar Solicitud");
        buttonPanel.add(btnConfirmar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void addListeners() {
        btnConfirmar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registrarSolicitud();
            }
        });
    }

    private void registrarSolicitud() {
        String origen = txtOrigen.getText();
        String destino = txtDestino.getText();
        String estadoInicial = "PENDIENTE";

        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Los campos Origen y Destino son obligatorios.",
                    "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String seguimientoGenerado = model.registrarEnvio(origen, destino, estadoInicial); // Sin pasar numeroSeguimiento

            if (!seguimientoGenerado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Solicitud de envío registrada con éxito.\nNúmero de Seguimiento: " + seguimientoGenerado,
                        "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                txtOrigen.setText("");
                txtDestino.setText("");
                // txtNumeroSeguimiento.setText(""); // Ya no existe
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo registrar la solicitud (el modelo no devolvió un número de seguimiento).",
                        "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar la solicitud: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
