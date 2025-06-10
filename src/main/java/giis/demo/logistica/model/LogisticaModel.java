package giis.demo.logistica.model;

import giis.demo.logistica.model.dto.EnvioDTO;
import giis.demo.logistica.model.dto.HistorialEnviosDTO;
import giis.demo.util.ApplicationException;
import giis.demo.util.Database;

import java.util.List;
import java.util.Map;
import java.util.Random;

public final class LogisticaModel {

    Random random = new Random();

    private static LogisticaModel instance = null;
    private Database db = new Database();

    public static LogisticaModel getInstance() {
        if (instance == null) {
            instance = new LogisticaModel();
        }

        return instance;
    }

    public EnvioDTO getEnvioPorNumeroSeguimiento(int numeroSeguimiento) {
        String sql = "select " +
                "id, numero_seguimiento, origen, destino, estado_actual " +
                "from Envios " +
                "where numero_seguimiento = ?";

        List<EnvioDTO> envios = db.executeQueryPojo(EnvioDTO.class, sql, numeroSeguimiento);

        if (envios.isEmpty()) {
            return null;
        } else {
            return envios.get(0);
        }
    }

    public List<HistorialEnviosDTO> getHistorialEnvio(int idEnvio) {

        String sql = "select " +
                "fecha_hora, descripcion_evento " +
                "from HistorialEnvios " +
                "where id_envio = ? " +
                "order by fecha_hora asc";

        return db.executeQueryPojo(HistorialEnviosDTO.class, sql, idEnvio);
    }

    public String registrarEnvio(String origen, String destino, String estado) {

        int numeroSeguimiento;
        EnvioDTO envioExistente;

        int intentosMaximos = 10;
        int intentos = 0;

        do {
            numeroSeguimiento = 10000 + random.nextInt(90000);
            envioExistente = getEnvioPorNumeroSeguimiento(numeroSeguimiento);

            intentos++;

            if (intentos > intentosMaximos && envioExistente != null) {
                throw new ApplicationException("No se pudo generar un número de seguimiento");
            }
        } while (envioExistente != null);

        System.out.println("Número de seguimiento generado: " + numeroSeguimiento);

        String sqlEnvio = "insert into Envios (numero_seguimiento, origen, destino, estado_actual) " +
                "values " +
                "(?, ?, ?, ?)";

        db.executeUpdate(sqlEnvio, numeroSeguimiento, origen, destino, estado);

        EnvioDTO envioGenerado = getEnvioPorNumeroSeguimiento(numeroSeguimiento);

        if (envioGenerado != null) {
            String sqlHistorial = "insert into HistorialEnvios (id_envio, descripcion_evento, fecha_hora) " +
            "values " +
            "(?, ?, datetime('now', 'localtime'))";

            db.executeUpdate(sqlHistorial, envioGenerado.getId(), "Solicitud de envío creada");
        } else {
            System.err.println("Error crítico: No se pudo recuperar el envío ID con seguimiento " + numeroSeguimiento + " después de la inserción para registrar el historial.");
            throw new ApplicationException("Error al registrar el historial inicial para el envío " + numeroSeguimiento);
        }

        return String.valueOf(envioGenerado.getNumero_seguimiento());
    }

}
