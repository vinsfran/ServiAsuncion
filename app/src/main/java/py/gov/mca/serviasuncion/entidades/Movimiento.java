package py.gov.mca.serviasuncion.entidades;

/**
 * Created by vinsfran on 12/4/16.
 */
public class Movimiento {

    private int numero;
    private String descripcion;

    public Movimiento() {
    }

    public Movimiento(int numero, String descripcion) {
        this.setNumero(numero);
        this.setDescripcion(descripcion);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
