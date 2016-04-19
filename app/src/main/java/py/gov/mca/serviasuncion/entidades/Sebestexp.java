package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;

/**
 * Created by vinsfran on 18/4/16.
 */
public class Sebestexp implements Serializable {

    private Integer nroEstexp;
    private String desEstexp;

    public Sebestexp() {
    }

    public Integer getNroEstexp() {
        return nroEstexp;
    }

    public void setNroEstexp(Integer nroEstexp) {
        this.nroEstexp = nroEstexp;
    }

    public String getDesEstexp() {
        return desEstexp;
    }

    public void setDesEstexp(String desEstexp) {
        this.desEstexp = desEstexp;
    }
}
