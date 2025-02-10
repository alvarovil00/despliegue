package es.daw.web.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import es.daw.web.bd.DBConnection;
import es.daw.web.bd.Dao;
import es.daw.web.bd.DaoFabricante;
import es.daw.web.bd.DaoProducto;
import es.daw.web.models.Fabricante;
import es.daw.web.models.Producto;
import es.daw.web.util.Utils;

@WebServlet("/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private String pathProperties = "";

    @Override
    public void init() {
        pathProperties = getServletContext().getRealPath("/JDBC.properties");
        System.out.println("pathProperties:" + pathProperties);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Recoger datos enviados desde el formulario
        String nombre = request.getParameter("nombre");
        String precio = request.getParameter("precio");
        String codigo_fabricante = request.getParameter("codigo_fabricante");
        String operacion = request.getParameter("operacion");
        String codigo = request.getParameter("codigo");


        // Pendiente...
        Dao<Producto> daoP;
        try {
            daoP = new DaoProducto(pathProperties);
            Producto producto;
            switch (operacion) {
                case "insert":
                    producto = new Producto();

                    producto.setPrecio(Float.parseFloat(precio));
                    producto.setNombre(nombre);
                    producto.setCodigo_fabricante(Integer.parseInt(codigo_fabricante));

                    daoP.insert(producto);

                    break;
                case "update":
                //hacer codigo en daoProducto de update
                    producto = new Producto();
                    if (!nombre.isEmpty()) {
                        producto.setNombre(nombre);
                    }
                    if (!precio.isEmpty()) {
                        producto.setPrecio(Float.parseFloat(precio));
                    }
                    if (!codigo_fabricante.isEmpty()) {
                        producto.setCodigo_fabricante(Integer.parseInt(codigo_fabricante));
                    }
                    if (!codigo.isEmpty()) {
                        producto.setCodigo(Integer.parseInt(codigo));
                    }
                    System.out.println(producto.getNombre());
                    System.out.println(producto.getCodigo());
                    // tengo que mandar a update el codigo de producto y los valores que quiero cambiar
                    daoP.update(producto);

                    break;
                case "delete":

                    daoP.delete(Integer.parseInt(codigo));
            
                default:
                    break;
            }




        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getErrorCode());
            System.err.println(e.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        // ------------ SALIDA ------------

        
        // Pendiente...

        getServletContext().getRequestDispatcher("/productos/ver").forward(request, response);

    }

    @Override
    public void destroy() {
        super.destroy();

        // try {
        //     DBConnection.closeConnection();
        // } catch (SQLException ex) {
        //     System.err.println("[processRequest][ERROR AL CERRA LA CONEXIÓN]" + ex.getMessage());
        // }

    }
}