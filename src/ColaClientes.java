public class ColaClientes {

    // Atributos
    private NodoCliente frente;
    private NodoCliente fin;
    private int tamanio;

    // Métodos

    // Constructor
    public ColaClientes() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // Operaciones
    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    // Inserta al cliente según su prioridad (en empate respeta el orden de llegada)
    public void encolar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("No se puede encolar un cliente nulo.");
        }

        NodoCliente nuevo = new NodoCliente(cliente);

        if (estaVacia()) {
            // La cola está vacía: el cliente queda como frente y fin
            frente = nuevo;
            fin = nuevo;
        } else if (cliente.tieneMayorPrioridadQue(frente.getCliente())) {
            // Tiene más prioridad que el frente: se coloca de primero
            nuevo.setSiguiente(frente);
            frente = nuevo;
        } else if (!cliente.tieneMayorPrioridadQue(fin.getCliente())) {
            // No supera al último: se coloca al final
            fin.setSiguiente(nuevo);
            fin = nuevo;
        } else {
            // Se busca su posición después de los clientes con prioridad igual o mayor
            NodoCliente actual = frente;
            while (actual.getSiguiente() != null
                    && !cliente.tieneMayorPrioridadQue(actual.getSiguiente().getCliente())) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }

        tamanio++;
    }

    // Atiende y retira al cliente con mayor prioridad (null si la cola está vacía)
    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }

        NodoCliente atendido = frente;
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        atendido.setSiguiente(null);
        tamanio--;
        return atendido.getCliente();
    }

    // Consulta el próximo cliente a atender sin retirarlo
    public Cliente verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getCliente();
    }

    // Mostrar cola
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay clientes en espera.\n");
            return;
        }

        System.out.println("========================================");
        System.out.println("      CLIENTES EN ESPERA (" + tamanio + ")");
        System.out.println("========================================");

        NodoCliente actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getCliente());
            actual = actual.getSiguiente();
            posicion++;
        }

        System.out.println("========================================\n");
    }

    // Clase Nodo
    private static class NodoCliente {

        // Atributos
        private final Cliente cliente;
        private NodoCliente siguiente;

        // Constructor
        public NodoCliente(Cliente cliente) {
            this.cliente = cliente;
            this.siguiente = null;
        }

        // Getters y Setters
        public Cliente getCliente() {
            return cliente;
        }

        public NodoCliente getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(NodoCliente siguiente) {
            this.siguiente = siguiente;
        }
    }
}
