package Modelo;

import java.io.Serializable;
import java.util.Arrays;

public class FacturaOrdenada extends Factura implements Serializable {

    private double importeTotalSinIva = 0;
    private double importeTotalConIva = 0;
    private double bonificacion = 0;
    private String[] codigoFacturas = new String[4];

    /***
     * public FacturaOrdenada(String cif, String nombreFarmaceutica, String fechaEnvio, String codigoEnvio, String[][] concepto)
     * Constructor con parametros, el parametro concepto es solo de introduccion, ya que luego el setteo segun lo que me pida
     * ya que los datos que necesito en los sets los saco de concepto
     * @param cif
     * @param nombreFarmaceutica
     * @param fechaEnvio
     * @param codigoEnvio
     * @param concepto
     */
    public FacturaOrdenada(String cif, String nombreFarmaceutica, String fechaEnvio, String codigoEnvio, String[][] concepto) {

        super(cif, nombreFarmaceutica, fechaEnvio, codigoEnvio);
        setImporteTotalSinIva(concepto);
        setImporteTotalConIva(concepto);
        setBonificacion(getImporteTotalConIva());
        setCodigoFacturas(concepto);
    }

    public double getImporteTotalSinIva() {
        return importeTotalSinIva;
    }
    public void setImporteTotalSinIva(String[][] concepto) {
        for (int i = 0; i < concepto.length; i++){
            this.importeTotalSinIva = this.importeTotalSinIva + Double.parseDouble(concepto[i][1]);
        }
    }

    public double getImporteTotalConIva() {
        return importeTotalConIva;
    }
    public void setImporteTotalConIva(String[][] concepto) {
        for (int i = 0; i < concepto.length; i++){
            this.importeTotalConIva = this.importeTotalConIva + Double.parseDouble(concepto[i][2]);
        }
    }

    public double getBonificacion() {
        return bonificacion;
    }
    public void setBonificacion(double importeTotal) {
        if (importeTotal>3000){
            this.bonificacion = importeTotal/15;
        } else if (importeTotal>=1000){
            this.bonificacion = importeTotal/10;
        } else {
            this.bonificacion = 0;
        }
    }

    public String[] getCodigoFacturas() {
        return codigoFacturas;
    }
    public void setCodigoFacturas(String[][] concepto) {
        for (int i = 0; i < concepto.length; i++){
            this.codigoFacturas[i] = concepto[i][0];
        }
    }

    @Override
    public String toString() {
        return super.getCif() +
                "," + getNombreFarmaceutica()+
                "," + getFechaEnvio()+
                "," + importeTotalSinIva +
                "," + importeTotalConIva +
                "," + bonificacion +
                "," + Arrays.toString(codigoFacturas);
    }
}