
public class Tienda {

    // Atributos
    private ArbolProductos inventario;
    private ColaClientes colaClientes;

    // Constructor
    public Tienda() {
        inventario = new ArbolProductos();
        colaClientes = new ColaClientes();
    }

    // Getters
    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaClientes() {
        return colaClientes;
    }

    // Operaciones del inventario
    public boolean registrarProducto(Producto producto) {
        return inventario.insertar(producto);
    }

    public Producto buscarProducto(String nombre) {
        return inventario.buscar(nombre);
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }

    // Agrega al carrito solamente productos disponibles en el inventario.
    public boolean agregarProductoAlCarrito(Cliente cliente, String nombre, int cantidad) {
        if (cliente == null || nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Debe indicar un cliente y un producto validos.");
            return false;
        }

        Producto producto = buscarProducto(nombre);
        if (producto == null) {
            System.out.println("El producto no existe en el inventario.");
            return false;
        }

        if (!producto.descontarStock(cantidad)) {
            System.out.println("Cantidad no valida o inventario insuficiente. Disponible: "
                    + producto.getCantidad());
            return false;
        }

        cliente.getCarrito().agregarAlCarrito(
                producto.getNombre(), producto.getPrecio(), producto.getCategoria(),
                producto.getFechaVencimiento(), cantidad);

        System.out.println("Producto agregado al carrito correctamente.");
        return true;
    }

    // Inserta al cliente en la cola de prioridad cuando tiene una compra.
    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getCarrito().estaVacia()) {
            return false;
        }

        colaClientes.encolar(cliente);
        return true;
    }

    public void mostrarClientesEnEspera() {
        colaClientes.mostrar();
    }

    // Atiende al cliente de mayor prioridad e imprime su factura.
    public void atenderSiguienteCliente() {
        Cliente cliente = colaClientes.desencolar();
        if (cliente == null) {
            System.out.println("No hay clientes pendientes de atencion.\n");
            return;
        }

        System.out.println("\n--- ATENCION DEL SIGUIENTE CLIENTE ---");
        System.out.println(cliente);
        cliente.getCarrito().generarFactura();
    }
}
