package es.daw.web.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.daw.web.models.Producto;

public class DaoProducto implements Dao<Producto> {

    private Connection con;

    public DaoProducto(String dbSettingsPropsFilePath) throws SQLException {
        super();
        con = DBConnection.getConnection(dbSettingsPropsFilePath);
    }

    @Override
    public Producto select(int id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    // lo hereda de Dao y devuelve siempre un List<Producto>
    @Override
    public List<Producto> selectAll() throws SQLException {

        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM producto");
                ResultSet rs = ps.executeQuery()) {

            List<Producto> productos = new ArrayList<>();

            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getInt("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getFloat("precio"));
                p.setCodigo_fabricante(rs.getInt("codigo_fabricante"));
                productos.add(p);
            }
            return productos;
        }
    }

    @Override
    public void insert(Producto t) throws SQLException {

        try (PreparedStatement ps = con
                .prepareStatement("INSERT INTO PRODUCTO (nombre,precio,codigo_fabricante) VALUES (?,?,?)")) {
            ps.setString(1, t.getNombre());
            ps.setFloat(2, t.getPrecio());
            ps.setInt(3, t.getCodigo_fabricante());

            ps.executeUpdate();
        }
    }

    @Override
    public void update(Producto t) throws SQLException {
        String sql = "UPDATE PRODUCTO SET nombre= ?, precio= ?, codigo_fabricante = ? WHERE codigo = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getNombre());
            ps.setFloat(2, t.getPrecio());
            ps.setInt(3, t.getCodigo_fabricante());
            ps.setInt(4, t.getCodigo());

            // Logging en lugar de System.out.println
            // Logging manual con valores sustituidos
            System.out.println("Ejecutando query: UPDATE PRODUCTO SET nombre = '" + t.getNombre() +
                    "', precio = " + t.getPrecio() + ", codigo_fabricante = " + t.getCodigo_fabricante() +
                    " WHERE codigo = " + t.getCodigo());
            ps.executeUpdate();
        }
    }
    @Override
    public void delete(Producto t) throws SQLException {
       
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM PRODUCTO WHERE codigo= ?";
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
    
            // Ejecutar la consulta
            int rowsAffected = ps.executeUpdate();
    
            // Verificar si se eliminó algún producto
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún producto con el código especificado.");
            }
    
        }
    }

}