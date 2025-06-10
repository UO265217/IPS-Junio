package giis.demo.logistica.model;

import giis.demo.logistica.model.dto.EnvioDTO;
import giis.demo.logistica.model.dto.HistorialEnviosDTO;
import giis.demo.util.Database;

import java.util.List;
import java.util.Map;

public class LogisticaModel {

    private Database db = new Database();

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

}
