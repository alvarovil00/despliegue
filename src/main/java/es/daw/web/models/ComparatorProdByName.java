package es.daw.web.models;

import java.util.Comparator;
// hay que poner que vas a comparar productos
public class ComparatorProdByName implements Comparator<Producto> {

    @Override
    public int compare(Producto p1, Producto p2) {
        return p1.getNombre().compareTo(p2.getNombre());
    }


} 
