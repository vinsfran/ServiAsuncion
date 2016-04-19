package py.gov.mca.serviasuncion.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vinsfran on 12/4/16.
 */
public class Semexpediente implements Serializable {

    private int nroExpediente;
    private int indEjefisexp;
    private Sebestexp nroEstexp;
    private Semdepen codDepen;
    private Sempersona nroTitular;
    private Sempersona nroRepresentante;
    private Sempersona nroFuncionario;
    private Date fecUltmod;
    private String usuUltmod;
    private Date fecIniexp;
    private String obsExpediente;
    private int indEjefiscar;
    private Date fecAlta;
    private String usuAlta;
    private int nroCarpeta;
    private String indPrioridad;
    private int nroTarea;
    private Date fecUltmov;
    private int nroTipexp;
    private String desExpediente;
    private int nroMesent;

    public Semexpediente() {
    }

    public int getNroExpediente() {
        return nroExpediente;
    }

    public void setNroExpediente(int nroExpediente) {
        this.nroExpediente = nroExpediente;
    }

    public int getIndEjefisexp() {
        return indEjefisexp;
    }

    public void setIndEjefisexp(int indEjefisexp) {
        this.indEjefisexp = indEjefisexp;
    }

    public Sebestexp getNroEstexp() {
        return nroEstexp;
    }

    public void setNroEstexp(Sebestexp nroEstexp) {
        this.nroEstexp = nroEstexp;
    }

    public Semdepen getCodDepen() {
        return codDepen;
    }

    public void setCodDepen(Semdepen codDepen) {
        this.codDepen = codDepen;
    }

    public Sempersona getNroTitular() {
        return nroTitular;
    }

    public void setNroTitular(Sempersona nroTitular) {
        this.nroTitular = nroTitular;
    }

    public Sempersona getNroRepresentante() {
        return nroRepresentante;
    }

    public void setNroRepresentante(Sempersona nroRepresentante) {
        this.nroRepresentante = nroRepresentante;
    }

    public Sempersona getNroFuncionario() {
        return nroFuncionario;
    }

    public void setNroFuncionario(Sempersona nroFuncionario) {
        this.nroFuncionario = nroFuncionario;
    }

    public Date getFecUltmod() {
        return fecUltmod;
    }

    public void setFecUltmod(Date fecUltmod) {
        this.fecUltmod = fecUltmod;
    }

    public String getUsuUltmod() {
        return usuUltmod;
    }

    public void setUsuUltmod(String usuUltmod) {
        this.usuUltmod = usuUltmod;
    }

    public Date getFecIniexp() {
        return fecIniexp;
    }

    public void setFecIniexp(Date fecIniexp) {
        this.fecIniexp = fecIniexp;
    }

    public String getObsExpediente() {
        return obsExpediente;
    }

    public void setObsExpediente(String obsExpediente) {
        this.obsExpediente = obsExpediente;
    }

    public int getIndEjefiscar() {
        return indEjefiscar;
    }

    public void setIndEjefiscar(int indEjefiscar) {
        this.indEjefiscar = indEjefiscar;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public String getUsuAlta() {
        return usuAlta;
    }

    public void setUsuAlta(String usuAlta) {
        this.usuAlta = usuAlta;
    }

    public int getNroCarpeta() {
        return nroCarpeta;
    }

    public void setNroCarpeta(int nroCarpeta) {
        this.nroCarpeta = nroCarpeta;
    }

    public String getIndPrioridad() {
        return indPrioridad;
    }

    public void setIndPrioridad(String indPrioridad) {
        this.indPrioridad = indPrioridad;
    }

    public int getNroTarea() {
        return nroTarea;
    }

    public void setNroTarea(int nroTarea) {
        this.nroTarea = nroTarea;
    }

    public Date getFecUltmov() {
        return fecUltmov;
    }

    public void setFecUltmov(Date fecUltmov) {
        this.fecUltmov = fecUltmov;
    }

    public int getNroTipexp() {
        return nroTipexp;
    }

    public void setNroTipexp(int nroTipexp) {
        this.nroTipexp = nroTipexp;
    }

    public String getDesExpediente() {
        return desExpediente;
    }

    public void setDesExpediente(String desExpediente) {
        this.desExpediente = desExpediente;
    }

    public int getNroMesent() {
        return nroMesent;
    }

    public void setNroMesent(int nroMesent) {
        this.nroMesent = nroMesent;
    }
}
