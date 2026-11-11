package papeleria;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {

    private ProductoDao productoDao = new ProductoDao();

    // Método GET: Se usa para consultas o redirecciones de navegación
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Mandamos un mensaje o la lista hacia la vista JSP
        request.setAttribute("mensaje", "Consultando inventario de Papelería DAMAE...");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Método POST: Recibe los datos del formulario HTML y los guarda en la BD
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Capturamos los datos que envió el formulario HTML
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));

        // Usamos tu DAO para insertar en MySQL
        productoDao.insertarProducto(nombre, descripcion, precio, stock, categoriaId);

        // Enviamos un mensaje de éxito a la vista JSP
        request.setAttribute("resultado", "¡El producto '" + nombre + "' se registró con éxito!");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}