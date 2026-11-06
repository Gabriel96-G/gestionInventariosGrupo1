public class PruebaColaClientes {

    // Prueba independiente de Cliente y ColaClientes
    public static void main(String[] args) {
        ColaClientes cola = new ColaClientes();

        // Estado inicial de la cola
        System.out.println("Cola vacia al inicio: " + cola.estaVacia());
        System.out.println("Desencolar con la cola vacia: " + cola.desencolar());
        System.out.println();

        // Clientes con prioridades mezcladas
        cola.encolar(new Cliente("Ana", 3));
        cola.encolar(new Cliente("Luis", 1));
        cola.encolar(new Cliente("Maria", 2));
        cola.encolar(new Cliente("Carlos", 1));
        cola.encolar(new Cliente("Sofia", 3));
        cola.encolar(new Cliente("Diego", 2));

        cola.mostrar();

        // Consulta del frente sin retirarlo
        System.out.println("Proximo en ser atendido: " + cola.verFrente());
        System.out.println("Clientes en espera: " + cola.getTamanio());
        System.out.println();

        // Atención de todos los clientes en orden de prioridad
        System.out.println("Orden de atencion:");
        while (!cola.estaVacia()) {
            Cliente atendido = cola.desencolar();
            System.out.println("   Atendiendo a " + atendido);
        }
        System.out.println();

        System.out.println("Cola vacia al final: " + cola.estaVacia());
        System.out.println();

        // Validaciones de datos inválidos
        System.out.println("Validaciones:");
        probarCreacion("", 1);
        probarCreacion("Pedro", 0);
        probarCreacion("Pedro", 4);

        // Verificación del carrito
        Cliente conCarrito = new Cliente("Laura", 2);
        System.out.println("   Carrito de Laura vacio: " + conCarrito.getCarrito().estaVacia());
    }

    // Intenta crear un cliente y muestra si fue aceptado o rechazado
    private static void probarCreacion(String nombre, int prioridad) {
        try {
            new Cliente(nombre, prioridad);
            System.out.println("   Se creo el cliente '" + nombre + "' con prioridad " + prioridad);
        } catch (IllegalArgumentException e) {
            System.out.println("   Rechazado ('" + nombre + "', " + prioridad + "): " + e.getMessage());
        }
    }
}
