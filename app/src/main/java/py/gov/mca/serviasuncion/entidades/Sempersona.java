package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vinsfran on 9/4/16.
 */
public class Sempersona implements Serializable {

    private int nroPersona;
    private String nroDocide;
    private Sebtipdocide sebtipdocide;
    private String obsPersona;
    private String indTipper;
    private String emailPrincipal;
    private String indSexo;
    private String usuAlta;
    private String telPrincipal;
    private Date fecUltmod;
    private String nomFantasia;
    private String usuUltmod;
    private String desPersona;
    private String dirPrincipal;
    private String indEstciv;
    private Date fecAlta;
    private Date fecNaccon;
    private String indTipsoc;
    private String indActivo;

    public Sempersona() {
    }

    public int getNroPersona() {
        return nroPersona;
    }

    public void setNroPersona(int nroPersona) {
        this.nroPersona = nroPersona;
    }

    public String getNroDocide() {
        return nroDocide;
    }

    public void setNroDocide(String nroDocide) {
        this.nroDocide = nroDocide;
    }

    public Sebtipdocide getSebtipdocide() {
        return sebtipdocide;
    }

    public void setSebtipdocide(Sebtipdocide sebtipdocide) {
        this.sebtipdocide = sebtipdocide;
    }

    public String getObsPersona() {
        return obsPersona;
    }

    public void setObsPersona(String obsPersona) {
        this.obsPersona = obsPersona;
    }

    public String getIndTipper() {
        return indTipper;
    }

    public void setIndTipper(String indTipper) {
        this.indTipper = indTipper;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getIndSexo() {
        return indSexo;
    }

    public void setIndSexo(String indSexo) {
        this.indSexo = indSexo;
    }

    public String getUsuAlta() {
        return usuAlta;
    }

    public void setUsuAlta(String usuAlta) {
        this.usuAlta = usuAlta;
    }

    public String getTelPrincipal() {
        return telPrincipal;
    }

    public void setTelPrincipal(String telPrincipal) {
        this.telPrincipal = telPrincipal;
    }

    public Date getFecUltmod() {
        return fecUltmod;
    }

    public void setFecUltmod(Date fecUltmod) {
        this.fecUltmod = fecUltmod;
    }

    public String getNomFantasia() {
        return nomFantasia;
    }

    public void setNomFantasia(String nomFantasia) {
        this.nomFantasia = nomFantasia;
    }

    public String getUsuUltmod() {
        return usuUltmod;
    }

    public void setUsuUltmod(String usuUltmod) {
        this.usuUltmod = usuUltmod;
    }

    public String getDesPersona() {
        return desPersona;
    }

    public void setDesPersona(String desPersona) {
        this.desPersona = desPersona;
    }

    public String getDirPrincipal() {
        return dirPrincipal;
    }

    public void setDirPrincipal(String dirPrincipal) {
        this.dirPrincipal = dirPrincipal;
    }

    public String getIndEstciv() {
        return indEstciv;
    }

    public void setIndEstciv(String indEstciv) {
        this.indEstciv = indEstciv;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public Date getFecNaccon() {
        return fecNaccon;
    }

    public void setFecNaccon(Date fecNaccon) {
        this.fecNaccon = fecNaccon;
    }

    public String getIndTipsoc() {
        return indTipsoc;
    }

    public void setIndTipsoc(String indTipsoc) {
        this.indTipsoc = indTipsoc;
    }

    public String getIndActivo() {
        return indActivo;
    }

    public void setIndActivo(String indActivo) {
        this.indActivo = indActivo;
    }
}
