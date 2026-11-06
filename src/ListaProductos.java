import java.util.ArrayList;

public class ListaProductos {

    // Atributos
    private NodoProducto primero;

    // Constructor
    public ListaProductos() {
        primero = null;
    }

    // Getters y Setters
    public NodoProducto getPrimero() {
        return primero;
    }

    public void setPrimero(NodoProducto primero) {
        this.primero = primero;
    }


    // Operaciones del carrito


    public boolean estaVacia() {
        return primero == null;
    }


     //Agregar al carrito

    public void agregarAlCarrito(String nombre, double precio, String categoria, String fechaVencimiento, int cantidadComprada) {

        if (cantidadComprada <= 0 || precio < 0) {
            System.out.println("Error: La cantidad a comprar debe ser mayor a 0 y el precio no puede ser negativo.\n");
            return;
        }

        NodoProducto nuevoProducto = new NodoProducto(nombre, precio, categoria, fechaVencimiento, cantidadComprada);

        if (estaVacia()) {
            setPrimero(nuevoProducto);
            return;
        }

        NodoProducto actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevoProducto);
    }


     // Eliminar del carrito

    public NodoProducto eliminarDelCarrito(String nombre) {
        if (estaVacia() || nombre == null) {
            System.out.println("El carrito está vacío.\n");
            return null;
        }

        if (nombre.equalsIgnoreCase(primero.getNombre())) {
            NodoProducto eliminado = primero;
            setPrimero(primero.getSiguiente());
            eliminado.setSiguiente(null);
            System.out.println("Se sacó '" + nombre + "' del carrito.\n");
            return eliminado;
        }

        NodoProducto anterior = primero;
        NodoProducto temp = primero.getSiguiente();

        while (temp != null && !nombre.equalsIgnoreCase(temp.getNombre())) {
            anterior = temp;
            temp = temp.getSiguiente();
        }

        if (temp != null) {
            anterior.setSiguiente(temp.getSiguiente());
            temp.setSiguiente(null);
            System.out.println("Se sacó '" + nombre + "' del carrito.\n");
            return temp;
        } else {
            System.out.println("El producto '" + nombre + "' no está en el carrito.\n");
            return null;
        }
    }



    // Operaciones de facturación


    // Calcula el monto total a pagar por los productos en el carrito.
    public double calcularTotal() {
        double total = 0.0;
        NodoProducto actual = primero;

        while (actual != null) {
            total += (actual.getPrecio() * actual.getCantidad());
            actual = actual.getSiguiente();
        }
        return total;
    }

    // Genera la factura
    public void generarFactura() {
        if (estaVacia()) {
            System.out.println("El carrito está vacío. Factura en ₡0.0\n");
            return;
        }

        NodoProducto actual = primero;
        int contador = 1;

        double totalCalculado = 0.0;

        System.out.println("==================================================");
        System.out.println("               FACTURA DE COMPRA                  ");
        System.out.println("==================================================");

        while (actual != null) {
            double subtotal = actual.getPrecio() * actual.getCantidad();
            totalCalculado += subtotal;

            System.out.println(contador + ". " + actual.getNombre());
            System.out.println("   Cantidad: " + actual.getCantidad() + " x ₡" + actual.getPrecio());
            System.out.println("   Subtotal: ₡" + subtotal);

            actual = actual.getSiguiente();
            contador++;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("TOTAL A PAGAR: ₡" + totalCalculado);
        System.out.println("==================================================\n");
    }


    // clase nodo

    public class NodoProducto {
        private String nombre;
        private double precio;
        private String categoria;
        private String fechaVencimiento;
        private int cantidad;
        private ArrayList<String> listaImagenes;
        private NodoProducto siguiente;

        public NodoProducto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
            this.fechaVencimiento = fechaVencimiento;
            this.cantidad = cantidad;
            this.listaImagenes = new ArrayList<>();
            this.siguiente = null;
        }

        // Getters
        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }
        public String getCategoria() { return categoria; }
        public String getFechaVencimiento() { return fechaVencimiento; }
        public int getCantidad() { return cantidad; }
        public ArrayList<String> getListaImagenes() { return listaImagenes; }
        public NodoProducto getSiguiente() { return siguiente; }

        // Setters
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setPrecio(double precio) { this.precio = precio; }
        public void setCategoria(String categoria) { this.categoria = categoria; }
        public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public void setListaImagenes(ArrayList<String> listaImagenes) { this.listaImagenes = listaImagenes; }
        public void setSiguiente(NodoProducto siguiente) { this.siguiente = siguiente; }
    }
}