package Controlador;

import Modelo.Factura;
import Modelo.FacturaOrdenada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

public class Utilidades {
    private static final String RUTA_MASTER = ".\\src\\Fichero\\facturasmezcladas.txt";
    private static final String RUTA_FINAL_BIN = ".\\src\\Fichero\\facturasParaPagar.bin";

    /***
     * public static TreeSet<FacturaOrdenada> leerTxt()
     * metodo el cual lee un fichero de texto, el cual recorre linea a linea capturando el objeto
     * una vez creado el objeto, lo añadimos a una lista que ordena y elimina los datos duplicados
     * @return listaFactura
     */
    public static TreeSet<FacturaOrdenada> leerTxt() {
        TreeSet<FacturaOrdenada> listaFactura = new TreeSet<>();
        Factura entrada;
        FacturaOrdenada salida;
        try (BufferedReader bf = new BufferedReader(new FileReader(RUTA_MASTER))){
            String linea;
            linea = bf.readLine();
            while(linea != null){
                //Crea el objeto factura
                entrada = construirObjetoCompleto(linea);
                //Crea el objeto FacturaOrdenada a partir de entrada, la diferencia es que los datos
                //que le paso crean los atributos de ella
                salida = new FacturaOrdenada(entrada.getCif(), entrada.getNombreFarmaceutica(), entrada.getFechaEnvio(), entrada.getCodigoEnvio(), entrada.getConcepto());
                //Añadimos a la lista
                listaFactura.add(salida);
                linea = bf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaFactura;
    }

    /***
     * public static void escribirBinario(TreeSet<FacturaOrdenada> listaFactura)
     * metodo que escribir en el fichero binario el contenido de la lista que le pasamos
     * @param listaFactura
     */
    public static void escribirBinario(TreeSet<FacturaOrdenada> listaFactura){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_FINAL_BIN, true))){
            for (FacturaOrdenada factura: listaFactura) {
                oos.writeObject(factura);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * public static void leerBinario()
     * metodo que lee un fichero binario de objetos, comprueba si el objeto es null
     */
    public static void leerBinario() {
        boolean salir = false;
        FacturaOrdenada factura;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(RUTA_FINAL_BIN))){
            factura = (FacturaOrdenada) objectInputStream.readObject();
            while (!salir) {
                System.out.println(factura);
                try {
                    factura = (FacturaOrdenada) objectInputStream.readObject();
                } catch (Exception e) {
                    salir = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /***
     * public static Factura construirObjetoCompleto(String linea)
     * metodo que transforma una linea pasada por parametros a objetos
     * recorre la linea y crea el objeto segun la posicion del array linea
     * @param linea
     * @return Factura
     */
    public static Factura construirObjetoCompleto(String linea) {
        boolean salir = false;
        int pos = 4;
        //Lo convierto en array para su facil manejo
        String[] lineaObjeto = linea.split(" ");
        String[][] concepto = new String[4][3];
        //En la linea hay varios elemetos que tiene la misma estructura (COD, SIN IVA, CON IVA)
        //Estos for recorren el arrayBi para añadir todos los datos que encuentre en su orden correspondiente
        //Es decir: concepto[0][0] = cod, concepto[0][1] = sin iva, concepto[0][2] = con iva
        for (int i = 0; !salir && i < concepto.length; i++) {
            for (int j = 0; j < concepto[i].length; j++) {
                if (lineaObjeto[pos + j] != null) {
                    concepto[i][j] = lineaObjeto[pos + j];
                } else {
                    salir = true;
                }
            }
            pos = pos + concepto[i].length;
        }
        //Aqui creo el objeto de salida
        return new Factura(lineaObjeto[0], lineaObjeto[1], lineaObjeto[2], lineaObjeto[3], concepto);
    }
}
