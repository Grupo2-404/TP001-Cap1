package turismoEnLaTierraMedia;

import java.util.Arrays;
import java.util.Objects;

public class Atraccion implements Comparable<Atraccion> {

	private String nombre;
	protected int costoDeVisita;
	protected double tiempoNecesario;
	protected int cupoDePersonas;
	private TipoDeAtraccion tipo;
	private Usuario usuario;

	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costo;
		this.tiempoNecesario = tiempo;
		this.cupoDePersonas = cupo;
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	public Atraccion getAtraccion(Atraccion unaAtraccion) {
		return unaAtraccion;
	}

	@Override
	public int compareTo(Atraccion otra) { // Implementar este método en promociones. (O mejor aún, en la interfaz)

		if (costoDeVisita == otra.costoDeVisita) {
			return (int) (this.tiempoNecesario - otra.tiempoNecesario) * -1;
		}
		return (this.costoDeVisita - otra.costoDeVisita) * -1;
	}

	/*
	 * public int getTipoDeAtraccionValor() { TipoDeAtraccion tipo =
	 * TipoDeAtraccion.ELECCIONDEUSUARIO; return tipo.getValor(); }
	 */

	public static void ordenarPorMayorCostoYtiempo(Atraccion[] arrayAtracciones) {
		Arrays.sort(arrayAtracciones); // El sort obtiene el método de órden de compareTo, por lo tanto actualmente
										// debe ordenar por TipoDeAtraccion, costo y luego por tiempo.
		/*
		 * for (int i = 0; i < arrayAtracciones.length; i++) { System.out.println((i +
		 * 1) + ". " + arrayAtracciones[i].costoDeVisita); System.out.println((i + 1) +
		 * ". " + arrayAtracciones[i].tiempoNecesario);
		 * System.out.println("-------------------");
		 * 
		 * }
		 */
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, nombre, tiempoNecesario, tipo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoDeVisita == other.costoDeVisita && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoNecesario) == Double.doubleToLongBits(other.tiempoNecesario)
				&& tipo == other.tipo && Objects.equals(usuario, other.usuario);
	}

	public TipoDeAtraccion getTipo() {
		return tipo;
	}

	public double getTiempoNecesario() {
		return this.tiempoNecesario;
	}

	public void restarCupo() {
		this.cupoDePersonas--;
	}

	/*
	 * public static void main(String[] args) { Atraccion[] arrayAtracciones = new
	 * Atraccion[3]; arrayAtracciones[0] = new Atraccion("Atracción 1", 30, 2, 40,
	 * TipoDeAtraccion.AVENTURA); arrayAtracciones[1] = new Atraccion("Atracción 2",
	 * 10, 2, 50, TipoDeAtraccion.PAISAJE); arrayAtracciones[2] = new
	 * Atraccion("Atracción 3", 20, 2, 20, TipoDeAtraccion.AVENTURA);
	 * 
	 * Atraccion Moria = new Atraccion("Moria", 33, 31, 40,
	 * TipoDeAtraccion.AVENTURA); Atraccion Mordor = new Atraccion("Mordor", 35, 2,
	 * 50, TipoDeAtraccion.PAISAJE);
	 * 
	 * System.out.println(Moria.compareTo(Mordor));
	 * 
	 * 
	 * //Moria es más grande que Mordor si el resultado es entero positivo
	 * 
	 * 
	 * //Moria es más chica que Mordor si el resultado es entero negativo
	 * //ordenarPorMayorCostoYtiempo(arrayAtracciones); }
	 * 
	 * /* public String getRegistro() { return "[" + "Nombre de la atracción: " +
	 * this.atraccion + "," + "Costo: " + this.costoDeVisita + "," +
	 * "tiempo estimado: " + this.tiempoNecesario + "," + "cupo: " +
	 * this.cupoDePersonas + "]"; }
	 */
}