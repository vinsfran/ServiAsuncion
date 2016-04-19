package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vinsfran on 12/4/16.
 */
public class Sedmovexp implements Serializable {

    private Integer nroSecuencia;
    private Semexpediente nroExpediente;
    private int nroCarpeta;
    private int indEjefiscar;
    private Date fecMovexp;
    private Sebtipmov nroTipmov;
    private int nroMesent;
    private Semdepen codDepen;
    private Sempersona nroFuncionario;
    private int nroTarea;
    private Sebestexp nroEstexp;
    private String usuAlta;
    private Date fecAlta;

    public Sedmovexp() {
    }


    public Integer getNroSecuencia() {
        return nroSecuencia;
    }

    public void setNroSecuencia(Integer nroSecuencia) {
        this.nroSecuencia = nroSecuencia;
    }

    public Semexpediente getNroExpediente() {
        return nroExpediente;
    }

    public void setNroExpediente(Semexpediente nroExpediente) {
        this.nroExpediente = nroExpediente;
    }

    public int getNroCarpeta() {
        return nroCarpeta;
    }

    public void setNroCarpeta(int nroCarpeta) {
        this.nroCarpeta = nroCarpeta;
    }

    public int getIndEjefiscar() {
        return indEjefiscar;
    }

    public void setIndEjefiscar(int indEjefiscar) {
        this.indEjefiscar = indEjefiscar;
    }

    public Date getFecMovexp() {
        return fecMovexp;
    }

    public void setFecMovexp(Date fecMovexp) {
        this.fecMovexp = fecMovexp;
    }

    public Sebtipmov getNroTipmov() {
        return nroTipmov;
    }

    public void setNroTipmov(Sebtipmov nroTipmov) {
        this.nroTipmov = nroTipmov;
    }

    public int getNroMesent() {
        return nroMesent;
    }

    public void setNroMesent(int nroMesent) {
        this.nroMesent = nroMesent;
    }

    public Semdepen getCodDepen() {
        return codDepen;
    }

    public void setCodDepen(Semdepen codDepen) {
        this.codDepen = codDepen;
    }

    public Sempersona getNroFuncionario() {
        return nroFuncionario;
    }

    public void setNroFuncionario(Sempersona nroFuncionario) {
        this.nroFuncionario = nroFuncionario;
    }

    public int getNroTarea() {
        return nroTarea;
    }

    public void setNroTarea(int nroTarea) {
        this.nroTarea = nroTarea;
    }

    public Sebestexp getNroEstexp() {
        return nroEstexp;
    }

    public void setNroEstexp(Sebestexp nroEstexp) {
        this.nroEstexp = nroEstexp;
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
}
