
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao {

    // 1. Registrar un producto (Create)
    public void insertarProducto(String nombre, String descripcion, double precio, int stock, int categoriaId) {

        String sql = "INSERT INTO producto (nombre_producto, descripcion, precio, stock, categoria_id) VALUES (?, ?, ?, ?, ?)";

        Connection con = ConexionBD.obtenerConexion();

        if (con == null) {
            System.out.println("No se pudo realizar el registro porque no hay conexión.");
            return;
        }

        try (Connection conexion = con;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, stock);
            ps.setInt(5, categoriaId);

            ps.executeUpdate();

            System.out.println("Producto guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    // 2. Consultar productos (Read)
    public void consultarProductos() {

        String sql = "SELECT * FROM producto";

        Connection con = ConexionBD.obtenerConexion();

        if (con == null) {
            System.out.println("No se pudo realizar la consulta porque no hay conexión.");
            return;
        }

        try (Connection conexion = con;
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id_producto") +
                    " | Nombre: " + rs.getString("nombre_producto") +
                    " | Stock: " + rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
    }

    // 3. Actualizar un producto (Update)
    public void actualizarProducto(int id, String nombre, String descripcion,
                                   double precio, int stock, int categoriaId) {

        String sql = "UPDATE producto SET nombre_producto = ?, descripcion = ?, precio = ?, stock = ?, categoria_id = ? WHERE id_producto = ?";

        Connection con = ConexionBD.obtenerConexion();

        if (con == null) {
            System.out.println("No se pudo actualizar porque no hay conexión.");
            return;
        }

        try (Connection conexion = con;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setDouble(3, precio);
            ps.setInt(4, stock);
            ps.setInt(5, categoriaId);
            ps.setInt(6, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró el producto.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
        
    // 4. Eliminar un producto (Delete)
    public void eliminarProducto(int id) {

        String sql = "DELETE FROM producto WHERE id_producto = ?";

        Connection con = ConexionBD.obtenerConexion();

        if (con == null) {
            System.out.println("No se pudo eliminar porque no hay conexión.");
            return;
        }

        try (Connection conexion = con;
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró el producto.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

}

