package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vinsfran on 18/4/16.
 */
public class Semdepen implements Serializable {

    private String codDepen;
    private String desDepen;
    private int nroMesent;
    private String usuAlta;
    private Date fecAlta;
    private String usuUltmod;
    private Date fecUltmod;

    public Semdepen() {
    }

    public String getCodDepen() {
        return codDepen;
    }

    public void setCodDepen(String codDepen) {
        this.codDepen = codDepen;
    }

    public String getDesDepen() {
        return desDepen;
    }

    public void setDesDepen(String desDepen) {
        this.desDepen = desDepen;
    }

    public int getNroMesent() {
        return nroMesent;
    }

    public void setNroMesent(int nroMesent) {
        this.nroMesent = nroMesent;
    }

    public String getUsuAlta() {
        return usuAlta;
    }

    public void setUsuAlta(String usuAlta) {
        this.usuAlta = usuAlta;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getUsuUltmod() {
        return usuUltmod;
    }

    public void setUsuUltmod(String usuUltmod) {
        this.usuUltmod = usuUltmod;
    }

    public Date getFecUltmod() {
        return fecUltmod;
    }

    public void setFecUltmod(Date fecUltmod) {
        this.fecUltmod = fecUltmod;
    }
}
