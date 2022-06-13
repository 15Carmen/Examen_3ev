package Modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Factura implements Serializable, Comparable<Object> {
    private String cif;
    private String nombreFarmaceutica;
    private String fechaEnvio;
    private String codigoEnvio;
    private String[][] concepto = new String[4][3];

    public Factura() {
    }

    public Factura(String cif, String nombreFarmaceutica, String fechaEnvio, String codigoEnvio, String[][] concepto) {
        this.cif = cif;
        this.nombreFarmaceutica = nombreFarmaceutica;
        this.fechaEnvio = fechaEnvio;
        this.codigoEnvio = codigoEnvio;
        this.concepto = concepto;
    }

    public Factura(String cif, String nombreFarmaceutica, String fechaEnvio, String codigoEnvio) {
        this.cif = cif;
        this.nombreFarmaceutica = nombreFarmaceutica;
        this.fechaEnvio = fechaEnvio;
        this.codigoEnvio = codigoEnvio;
    }


    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreFarmaceutica() {
        return nombreFarmaceutica;
    }

    public void setNombreFarmaceutica(String nombreFarmaceutica) {
        this.nombreFarmaceutica = nombreFarmaceutica;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getCodigoEnvio() {
        return codigoEnvio;
    }

    public void setCodigoEnvio(String codigoEnvio) {
        this.codigoEnvio = codigoEnvio;
    }

    public String[][] getConcepto() {
        return concepto;
    }

    @Override
    public String toString() {
        return cif +
                "," + nombreFarmaceutica +
                "," + fechaEnvio +
                "," + codigoEnvio +
                "," + Arrays.toString(concepto);
    }

    public int compareTo(Object o) {
        int resultado = 0;
        if(o instanceof Factura){
            Factura empresa = (Factura) o;
            if (this.cif.compareTo(empresa.getCif()) == 0){
                if (this.fechaEnvio.compareTo(empresa.fechaEnvio) == 0){
                    if(this.codigoEnvio.compareTo(empresa.codigoEnvio) == 0){
                        resultado = 0;
                    } else {
                        resultado = this.codigoEnvio.compareTo(empresa.codigoEnvio);
                    }
                }
                else {
                    resultado = this.fechaEnvio.compareTo(empresa.fechaEnvio);
                }
            } else {
                resultado = this.cif.compareTo(empresa.getCif());
            }
        }
        return resultado;
    }
}
