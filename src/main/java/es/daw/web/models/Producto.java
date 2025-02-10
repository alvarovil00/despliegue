package es.daw.web.models;

/**
 * Un JavaBean debe tener un constructor sin argumentos. Tiene declarados todos
 * sus atributos como privados y para cada uno de ellos un método setter y
 * getter. Deben ser serializables. Mediante estos JavaBeans, desarrollamos
 * nuestro modelo de objetos (o modelo de dominio) para la aplicación.
 *
 *
 * CURIOSIDAD: POJO son las iniciales de "Plain Old Java Object", que puede
 * interpretarse c omo "Un objeto Java Plano Antiguo". Un POJO es una instancia
 * de una clase que no extiende ni implementa nada en especial. Por ejemplo, un
 * Controller de Spring tiene que extender de SimpleFormController, e
 * implementar los métodos abstractos de ese tipo: por eso, no es un POJO. Un
 * Servlet, tiene que extender de HttpServlet por lo que tampoco es un POJO. En
 * cambio, si defines una clase Cliente con atributos y unas cuantas
 * operaciones, tienes un simple y modesto POJO.
 *
 * @author daw1a
 */
public class Producto implements Comparable<Producto>{

    private int codigo;
    private String nombre;
    private float precio;
    private int codigo_fabricante;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCodigo_fabricante() {
        return codigo_fabricante;
    }

    public void setCodigo_fabricante(int codigo_fabricante) {
        this.codigo_fabricante = codigo_fabricante;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", codigo_fabricante=" + codigo_fabricante + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return this.codigo == other.codigo;
    }

	@Override
	public int compareTo(Producto p1) {
        // if (codigo == p1.getCodigo())
        //     return 0;
        // else if (codigo > p1.getCodigo())
        //     return 1;
        // else
        //     return -1;

		return Integer.compare(codigo, p1.codigo);
	}

}
