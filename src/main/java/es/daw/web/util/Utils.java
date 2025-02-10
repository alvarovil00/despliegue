package es.daw.web.util;

import java.util.List;

import es.daw.web.models.Fabricante;

public class Utils {
    public static String obtenerNombreFabricante(List<Fabricante> fabricantes, int codigo) {

        // FORMA 1: IMPERATIVA
        
        for (Fabricante f: fabricantes) {
            if (f.getCodigo() == codigo)
                return f.getNombre();
            }
        return "";
        

        // FORMA 2: STREAM()
        // return fabricantes.stream()
        //         .filter(f -> f.getCodigo() == codigo)
        //         .findFirst()
        //         .get().getNombre();
    }
}
