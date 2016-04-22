package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;

/**
 * Created by vinsfran on 18/4/16.
 */
public class Sebtipdocide implements Serializable {

    private String indTipdocide;
    private String desTipdocide;

    public Sebtipdocide() {
    }

    public String getIndTipdocide() {
        return indTipdocide;
    }

    public void setIndTipdocide(String indTipdocide) {
        this.indTipdocide = indTipdocide;
    }

    public String getDesTipdocide() {
        return desTipdocide;
    }

    public void setDesTipdocide(String desTipdocide) {
        this.desTipdocide = desTipdocide;
    }

    public String toString(){
        return getDesTipdocide();
    }
}
