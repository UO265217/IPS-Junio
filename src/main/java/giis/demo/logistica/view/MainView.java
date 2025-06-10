package giis.demo.logistica.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JButton btnAbrirSeguimiento;
    private JButton btnNuevaSolicitud;

    public MainView() {

        setTitle("Logistica");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAbrirSeguimiento = new JButton("Consultar Seguimiento de Envío");
        btnAbrirSeguimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SeguimientoView seguimientoView = new SeguimientoView();
                seguimientoView.setVisible(true);

            }
        });
        add(btnAbrirSeguimiento);

        btnNuevaSolicitud = new JButton("Nueva Solicitud");
        btnNuevaSolicitud.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SolicitudEnvioView solicitudEnvioView = new SolicitudEnvioView();
                solicitudEnvioView.setVisible(true);
            }
        });
        add(btnNuevaSolicitud);
    }

}
