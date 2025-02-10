package es.daw.web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import es.daw.web.bd.DaoFabricante;
import es.daw.web.bd.DaoProducto;
import es.daw.web.models.ComparatorProdByName;
import es.daw.web.models.Fabricante;
import es.daw.web.models.Producto;
import es.daw.web.util.Utils;


@WebServlet("/productos/ver")
public class ListarProductosServlet extends HttpServlet {

    private String pathProperties = "";

    @Override
    public void init() {
        pathProperties = getServletContext().getRealPath("/JDBC.properties");
        System.out.println("pathProperties:"+pathProperties);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        DaoProducto daoP;
        DaoFabricante daoF;


        List<Producto> productos = null;
        List<Fabricante> fabricantes = null;


        

        try {
            daoP = new DaoProducto(pathProperties);

            daoF = new DaoFabricante(pathProperties);

            productos = daoP.selectAll();
            fabricantes = daoF.selectAll();
            productos.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            System.err.println(e.getErrorCode());


            //response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            request.setAttribute("error", e.getMessage() );
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);

        }



        // ------------ SALIDA ------------
        response.setContentType("text/html<charset=UTF-8");


        // ORDENACIONES========================================
        //Collections.sort(productos); //ordenacion por codigo ascendente
        //Collections.reverse(productos); // productos por codigo descendente
        //productos.sort(Comparator.naturalOrder()); // por codigo ascendente
        //productos.sort(Comparator.reverseOrder());; // por codigo descendente

        //ORDENAR POR NOMBRE DE PRODUCTO
        //productos.sort(new ComparatorProdByName()); // por nombre ascendente
        //productos.sort(new ComparatorProdByName().reversed()); // por nombre descendente

        //productos.sort((p1,p2) -> p1.getNombre().compareTo(p2.getNombre()));

        //productos.sort((p1,p2) -> Float.compare(p1.getPrecio(), p2.getPrecio()));
        
        productos.sort(new ComparatorProdByName().thenComparing((p1,p2) -> Float.compare(p1.getPrecio(),p2.getPrecio())));
        
        StringBuilder sb = new StringBuilder();
        for (Producto producto : productos) {
            sb.append("<tr><td>").append(producto.getCodigo()).append("</td>")
            .append("<td>").append(producto.getNombre()).append("</td>")
            .append("<td>").append(producto.getPrecio()).append("</td>");

            String nombreFabricante = Utils.obtenerNombreFabricante(fabricantes, producto.getCodigo_fabricante());
            sb.append("<td>").append(nombreFabricante).append("</td>");
        }

        // Pendiente...

        request.setAttribute("filas", sb.toString());
        getServletContext().getRequestDispatcher("/informe.jsp").forward(request, response);

    }

    @Override
    public void destroy() {
    }
}