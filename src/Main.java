import java.util.Scanner;

public class Main {


    private static Tienda tienda = new Tienda();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Carga inicial de datos
        cargarDatosPrueba();


        menu();

        scanner.close();
    }

    // Menu
    public static void menu() {
        int opcion = 0;

        do {
            System.out.println("==================================================");
            System.out.println("   SISTEMA DE GESTION DE INVENTARIOS - Grupo 1  ");
            System.out.println("==================================================");
            System.out.println("1. Registrar producto en el inventario");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Registrar cliente y llenar carrito");
            System.out.println("5. Mostrar clientes en espera");
            System.out.println("6. Atender siguiente cliente y generar factura");
            System.out.println("7. Salir del sistema");
            System.out.println("==================================================");
            System.out.print("Seleccione una opcion: ");


            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n[Error]: Debe ingresar un numero valido.\n");
                continue;
            }

            System.out.println();


            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    tienda.mostrarInventario();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    registrarCliente();
                    break;
                case 5:
                    tienda.mostrarClientesEnEspera();
                    break;
                case 6:
                    tienda.atenderSiguienteCliente();
                    break;
                case 7:
                    System.out.println("Te esperamos de nuevo. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.\n");
                    break;
            }


        } while (opcion != 7);
    }



    // Captura datos producto
    private static void registrarProducto() {
        System.out.println("--- REGISTRAR PRODUCTO ---");

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine().trim();

        try {
            System.out.print("Precio unitario (en colones): ");
            double precio = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Categoria: ");
            String categoria = scanner.nextLine().trim();

            System.out.print("Fecha de vencimiento (o 'N/A' si no aplica): ");
            String fechaVencimiento = scanner.nextLine().trim();

            System.out.print("Cantidad en inventario: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());

            Producto producto = new Producto(
                    nombre, precio, categoria, fechaVencimiento, cantidad
            );

            if (tienda.registrarProducto(producto)) {
                System.out.println("Producto registrado con exito.\n");
            } else {
                System.out.println("Ya existe un producto con ese nombre.\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("[Error]: Ingrese valores numericos validos.\n");

        } catch (IllegalArgumentException e) {
            System.out.println("[Error]: " + e.getMessage() + "\n");
        }
    }


    // Buscar producto
    private static void buscarProducto() {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine().trim();

        Producto resultado = tienda.buscarProducto(nombre);

        if (resultado != null) {
            System.out.println("\nProducto encontrado:" + resultado);
        } else {
            System.out.println("\nEl producto '" + nombre + "' no existe en el inventario.\n");
        }
    }


    // Registrar cliente y llenar carrito
    private static void registrarCliente() {
        System.out.println("--- REGISTRAR CLIENTE ---");

        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine().trim();

        System.out.println("Prioridades: 1 = Basico, 2 = Afiliado, 3 = Premium");
        System.out.print("Ingrese la prioridad: ");

        int prioridad;

        try {
            prioridad = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[Error]: Debe ingresar una prioridad numerica.\n");
            return;
        }

        Cliente cliente;

        try {
            cliente = new Cliente(nombre, prioridad);
        } catch (IllegalArgumentException e) {
            System.out.println("[Error]: " + e.getMessage() + "\n");
            return;
        }

        while (true) {
            System.out.println("\n--- CARRITO DE " + cliente.getNombre() + " ---");
            tienda.mostrarInventario();

            System.out.print("Nombre del producto (0 para terminar): ");
            String nombreProducto = scanner.nextLine().trim();

            if (nombreProducto.equals("0")) {
                break;
            }

            System.out.print("Cantidad a comprar: ");
            int cantidad;

            try {
                cantidad = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error]: Debe ingresar una cantidad numerica.");
                continue;
            }

            tienda.agregarProductoAlCarrito(cliente, nombreProducto, cantidad);
        }

        if (tienda.registrarCliente(cliente)) {
            System.out.println("\nCliente agregado a la cola correctamente.");
            System.out.println("Total del carrito: ₡"
                    + cliente.getCarrito().calcularTotal());
        } else {
            System.out.println("\nEl carrito esta vacio.");
            System.out.println("El cliente no fue agregado a la cola.");
        }
    }



    // Datos de prueba
    private static void cargarDatosPrueba() {

        tienda.registrarProducto(new Producto(
                "Leche Semidescremada", 950.0, "Lacteos", "2026-10-15", 40
        ));

        tienda.registrarProducto(new Producto(
                "Arroz Grano Entero", 1350.0, "Granos", "2027-01-20", 25
        ));

        tienda.registrarProducto(new Producto(
                "Manzanas Gala", 450.0, "Frutas", "2026-10-05", 80
        ));
    }

}