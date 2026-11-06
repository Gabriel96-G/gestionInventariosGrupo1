public class ArbolProductos
{
    private NodoArbol raiz;
 
    public boolean estaVacia() { return raiz == null; }
 
    // Devuelve false (sin insertar) si ya existe un producto con ese nombre
    public boolean insertar(Producto productoNuevo)
    {
        if (productoNuevo == null || buscar(productoNuevo.getNombre()) != null) return false;
        raiz = insertarEnNodo(raiz, productoNuevo);
        return true;
    }
 
    private NodoArbol insertarEnNodo(NodoArbol nodoActual, Producto productoNuevo)
    {
        if (nodoActual == null) return new NodoArbol(productoNuevo);
        int comparacion = productoNuevo.getNombre().compareToIgnoreCase(nodoActual.producto.getNombre());
        if (comparacion < 0) nodoActual.hijoIzquierdo = insertarEnNodo(nodoActual.hijoIzquierdo, productoNuevo);
        else nodoActual.hijoDerecho = insertarEnNodo(nodoActual.hijoDerecho, productoNuevo);
        return nodoActual;
    }
 
    // Devuelve el Producto o null si no existe
    public Producto buscar(String nombreBuscado)
    {
        if (nombreBuscado == null) return null;
        NodoArbol nodoActual = raiz;
        while (nodoActual != null)
        {
            int comparacion = nombreBuscado.trim().compareToIgnoreCase(nodoActual.producto.getNombre());
            if (comparacion == 0) return nodoActual.producto;
            nodoActual = comparacion < 0 ? nodoActual.hijoIzquierdo : nodoActual.hijoDerecho;
        }
        return null;
    }
 
    // Imprime el inventario en orden alfabético
    public void mostrar()
    {
        if (estaVacia())
        {
            System.out.println("El inventario está vacío.\n");
            return;
        }
        System.out.println("========================================");
        System.out.println("      INVENTARIO DE LA TIENDA           ");
        System.out.println("========================================");
        mostrarEnOrden(raiz);
        System.out.println("========================================\n");
    }
 
    private void mostrarEnOrden(NodoArbol nodoActual)
    {
        if (nodoActual == null) return;
        mostrarEnOrden(nodoActual.hijoIzquierdo);
        System.out.println(nodoActual.producto);
        mostrarEnOrden(nodoActual.hijoDerecho);
    }
 
    // Clase Nodo
    private static class NodoArbol
    {
        private final Producto producto;
        private NodoArbol hijoIzquierdo;
        private NodoArbol hijoDerecho;
 
        private NodoArbol(Producto producto) { this.producto = producto; }
    }
}