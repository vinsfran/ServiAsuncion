package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;

/**
 * Created by vinsfran on 18/4/16.
 */
public class Sebtipmov implements Serializable {

    private Integer nroTipmov;
    private String desTipmov;

    public Sebtipmov() {
    }

    public Integer getNroTipmov() {
        return nroTipmov;
    }

    public void setNroTipmov(Integer nroTipmov) {
        this.nroTipmov = nroTipmov;
    }

    public String getDesTipmov() {
        return desTipmov;
    }

    public void setDesTipmov(String desTipmov) {
        this.desTipmov = desTipmov;
    }
}
