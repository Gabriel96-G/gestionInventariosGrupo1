public class ColaClientes {

    private NodoCliente frente;
    private NodoCliente fin;
    private int tamanio;

    public ColaClientes() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void encolar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("No se puede encolar un cliente nulo.");
        }

        NodoCliente nuevo = new NodoCliente(cliente);

        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else if (cliente.tieneMayorPrioridadQue(frente.getCliente())) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
        } else if (!cliente.tieneMayorPrioridadQue(fin.getCliente())) {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        } else {
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

    public Cliente verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getCliente();
    }

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

    private static class NodoCliente {

        private final Cliente cliente;
        private NodoCliente siguiente;

        public NodoCliente(Cliente cliente) {
            this.cliente = cliente;
            this.siguiente = null;
        }

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
