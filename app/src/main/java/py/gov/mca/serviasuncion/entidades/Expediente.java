package py.gov.mca.serviasuncion.entidades;

/**
 * Created by vinsfran on 12/4/16.
 */
public class Expediente {

    private int numero;
    private String anio;

    public Expediente() {
    }

    public Expediente(int numero, String anio) {
        this.setNumero(numero);
        this.setAnio(anio);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}
