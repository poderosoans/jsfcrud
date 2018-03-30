/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.DaoCliente;
import bean.Cliente;
import util.SqlConecta;

/**
 *
 * @author DENNYZ
 */
public class PersistenciaBDDaoCliente implements DaoCliente {

    private final SqlConecta conecta;

    public PersistenciaBDDaoCliente() {
        this.conecta = new SqlConecta();
    }

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = null;

        String sql = "SELECT "
                + "codigo, "
                + "nombre, "
                + "apellido, "
                + "genero, "
                + "profesion, "
                + "numeroMovil, "
                + "operadorMovil, " //añadido
                + "publicidad, "
                + "correo "
                + "FROM cliente";
        Connection cn = conecta.connection();
        if (cn != null) {
            System.out.println("CONEXION NO NULL");
            try {
                Statement st = cn.createStatement();
                System.out.println("1 " + sql);
                ResultSet rs = st.executeQuery(sql);
                System.out.println("HAY RESULTADOS");
                clientes = new ArrayList<Cliente>();

                while (rs.next()) {
                    Cliente c = new Cliente();

                    c.setCodigo(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellido(rs.getString(3));
                    c.setGenero(rs.getString(4));
                    c.setProfesion(rs.getString(5));
                    c.setNumeroMovil(rs.getString(6));
                    c.setOperadorMovil(rs.getString(7)); // añadido
                    c.setPublicidad(rs.getBoolean(8));
                    c.setCorreo(rs.getString(9));

                    System.out.println("AGREGANDO CLIENTE");
                    clientes.add(c);
                }

            } catch (SQLException e) {
                System.out.println("mensaje de error: " + e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                }
            }
        }
        System.out.println("tamaño de lista " + clientes.size());
        return clientes;
    }

    @Override
    public String insertarCliente(Cliente cliente) {
        String result = null;
        String sql = "INSERT INTO cliente("
                + "nombre,"
                + "apellido,"
                + "genero,"
                + "profesion,"
                + "numeromovil,"
                + "operadormovil," // añadido
                + "publicidad,"
                + "correo"
                + ") VALUES(?,?,?,?,?,?,?,?)"; // se añadio un (?)

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getGenero());
                ps.setString(4, cliente.getProfesion());
                ps.setString(5, cliente.getNumeroMovil());
                ps.setString(6, cliente.getOperadorMovil()); // añadido
                ps.setBoolean(7, cliente.getPublicidad());
                ps.setString(8, cliente.getCorreo());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    throw new SQLException("0 filas afectadas");
                }

            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }

        return result;
    }

    @Override
    public String eliminarCliente(int id) {
        String result = null;
        String sql = "DELETE FROM cliente WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                int ctos = ps.executeUpdate();
            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }
        return result;
    }

    @Override
    public Cliente obtenerCliente(Integer idCliente) {
        Cliente cliente = null;
        String sql = "SELECT "
                + "codigo,"
                + "nombre,"
                + "apellido,"
                + "genero,"
                + "profesion,"
                + "numeroMovil,"
                + "operadorMovil," // añadido
                + "publicidad,"
                + "correo "
                + "FROM cliente "
                + "WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idCliente);
                System.out.println("sentencia " + sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cliente = new Cliente();

                    cliente.setCodigo(rs.getInt(1));
                    cliente.setNombre(rs.getString(2));
                    cliente.setApellido(rs.getString(3));
                    cliente.setGenero(rs.getString(4));
                    cliente.setProfesion(rs.getString(5));
                    cliente.setNumeroMovil(rs.getString(6));
                    cliente.setOperadorMovil(rs.getString(7)); // añadido
                    cliente.setPublicidad(rs.getBoolean(8));
                    cliente.setCorreo(rs.getString(9));
                }

            } catch (SQLException e) {
                System.out.println("Mensaje de error: " + e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.out.println("Mensaje de error: " + e.getMessage());
                }
            }
        }

        return cliente;
    }

    @Override
    public String modificarCliente(Cliente cliente) {
        String result = null;
        String sql = "UPDATE cliente SET "
                + "nombre=?,"
                + "apellido=?,"
                + "genero=?,"
                + "profesion=?,"
                + "numeroMovil=?,"
                + "operadorMovil=?," // añadido
                + "publicidad=?,"
                + "correo=?"
                + "WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getGenero());
                ps.setString(4, cliente.getProfesion());
                ps.setString(5, cliente.getNumeroMovil());
                ps.setString(6, cliente.getOperadorMovil()); // añadido
                ps.setBoolean(7, cliente.getPublicidad());
                ps.setString(8, cliente.getCorreo());
                ps.setInt(9, cliente.getCodigo());

                int ctos = ps.executeUpdate();
                
            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }
        return result;
    }

}
