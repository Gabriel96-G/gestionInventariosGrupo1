public class Cliente {

    // Constantes de prioridad (3 es la más alta)
    public static final int PRIORIDAD_BASICO = 1;
    public static final int PRIORIDAD_AFILIADO = 2;
    public static final int PRIORIDAD_PREMIUM = 3;

    // Contador para asignar el número de llegada de cada cliente
    private static int contadorLlegada = 0;

    // Atributos
    private final String nombre;
    private final int prioridad;
    private final int numeroLlegada;
    private final ListaProductos carrito;

    // Métodos

    // Constructor
    public Cliente(String nombre, int prioridad) {
        // Validación del nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        }
        // Validación de la prioridad
        if (!esPrioridadValida(prioridad)) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (basico), 2 (afiliado) o 3 (premium).");
        }
        contadorLlegada++;
        this.nombre = nombre.trim();
        this.prioridad = prioridad;
        this.numeroLlegada = contadorLlegada;
        // Cada cliente inicia con su carrito vacío
        this.carrito = new ListaProductos();
    }

    // Verifica que la prioridad esté entre 1 y 3
    public static boolean esPrioridadValida(int prioridad) {
        return prioridad >= PRIORIDAD_BASICO && prioridad <= PRIORIDAD_PREMIUM;
    }

    // Getters
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

    // Devuelve el tipo de cliente según su prioridad
    public String getTipoCliente() {
        return switch (prioridad) {
            case PRIORIDAD_PREMIUM -> "Premium";
            case PRIORIDAD_AFILIADO -> "Afiliado";
            default -> "Basico";
        };
    }

    // Compara prioridades: true si este cliente debe atenderse antes que el otro
    public boolean tieneMayorPrioridadQue(Cliente otro) {
        return this.prioridad > otro.prioridad;
    }

    // toString()
    @Override
    public String toString() {
        return "Cliente #" + numeroLlegada + ": " + nombre
                + " | Prioridad: " + prioridad + " (" + getTipoCliente() + ")";
    }
}
