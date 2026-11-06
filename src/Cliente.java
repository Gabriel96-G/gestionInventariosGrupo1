public class Cliente {

    public static final int PRIORIDAD_ALTA = 1;
    public static final int PRIORIDAD_MEDIA = 2;
    public static final int PRIORIDAD_BAJA = 3;

    private static int contadorLlegada = 0;

    private final String nombre;
    private final int prioridad;
    private final int numeroLlegada;
    private final ListaProductos carrito;

    public Cliente(String nombre, int prioridad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        }
        if (!esPrioridadValida(prioridad)) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (alta), 2 (media) o 3 (baja).");
        }
        contadorLlegada++;
        this.nombre = nombre.trim();
        this.prioridad = prioridad;
        this.numeroLlegada = contadorLlegada;
        this.carrito = new ListaProductos();
    }

    public static boolean esPrioridadValida(int prioridad) {
        return prioridad >= PRIORIDAD_ALTA && prioridad <= PRIORIDAD_BAJA;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getNumeroLlegada() {
        return numeroLlegada;
    }

    public ListaProductos getCarrito() {
        return carrito;
    }

    public String getDescripcionPrioridad() {
        return switch (prioridad) {
            case PRIORIDAD_ALTA -> "Alta";
            case PRIORIDAD_MEDIA -> "Media";
            default -> "Baja";
        };
    }

    public boolean tieneMayorPrioridadQue(Cliente otro) {
        return this.prioridad < otro.prioridad;
    }

    @Override
    public String toString() {
        return "Cliente #" + numeroLlegada + ": " + nombre
                + " | Prioridad: " + prioridad + " (" + getDescripcionPrioridad() + ")";
    }
}
