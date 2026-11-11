
public class Main {
    public static void main(String[] args) {

        ProductoDao producto = new ProductoDao();

        System.out.println("Eliminando producto...");

        producto.eliminarProducto(3);

        System.out.println("\nProductos registrados:");

        producto.consultarProductos();
    }
}


