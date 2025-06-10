package giis.demo.logistica.model.dto;

import lombok.Data;

@Data
public class EnvioDTO {

    private int id;
    private int numero_seguimiento;
    private String origen;
    private String destino;
    private String estado_actual;

}
