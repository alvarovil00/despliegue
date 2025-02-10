package es.daw.web.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.daw.web.models.Fabricante;

public class DaoFabricante implements Dao<Fabricante> {

    private Connection con;

    public DaoFabricante(String dbSettingsPropsFilePath) throws SQLException {
        super();
        con = DBConnection.getConnection(dbSettingsPropsFilePath);
    }

    @Override
    public Fabricante select(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'select'");

    }

    @Override
    public List<Fabricante> selectAll() throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM fabricante");
                ResultSet rs = ps.executeQuery()) {

            List<Fabricante> result = null;

            while (rs.next()) {
                if (result == null) {
                    result = new ArrayList<>();
                }

                Fabricante f = new Fabricante();
                f.setCodigo(rs.getInt("codigo"));
                f.setNombre(rs.getString("nombre"));
                result.add(f);

            }
            return result;
        }

    }

    @Override
    public void insert(Fabricante t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Fabricante t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Fabricante t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
