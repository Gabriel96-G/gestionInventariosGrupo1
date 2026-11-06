import java.util.ArrayList;
 
public class Producto
{
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;
 
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad)
    {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("El nombre del producto no puede estar vacio.");
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        this.nombre = nombre.trim();
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
        this.listaImagenes = new ArrayList<>();
    }
 
    // Descuenta del inventario lo que el cliente mete al carrito (false si no alcanza o la cantidad no es válida)
    public boolean descontarStock(int cantidadComprada)
    {
        if (cantidadComprada <= 0 || cantidadComprada > cantidad) return false;
        cantidad -= cantidadComprada;
        return true;
    }
 
    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public ArrayList<String> getListaImagenes() { return listaImagenes; }
 
    // Setters (el nombre no se cambia porque es la llave del árbol)
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
 
    @Override
    public String toString() { return "\nNombre: " + nombre + "\nPrecio: ₡" + precio + "\nCategoría: " + categoria + "\nFecha de Vencimiento: " + fechaVencimiento + "\nCantidad: " + cantidad + "\nImágenes: " + listaImagenes + "\n"; }
}
 