package turismoEnLaTierraMedia;

import java.util.Arrays;
import java.util.Objects;

public abstract class Promocion implements Comparable<Promocion>, Sugerible {

	protected Atraccion[] atraccionesIncluidas;
	private String nombre;

	public Promocion(String nombreDePromocion, Atraccion[] arrayAtracciones) {
		this.setNombre(nombreDePromocion);
		this.setArrayAtracciones(arrayAtracciones);
	}


	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public void restarCupo() {

		for (int i = 0; i > atraccionesIncluidas.length; i++) {
			atraccionesIncluidas[i].restarCupo();
		}
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean comprobarCupo() {

		for (int i = 0; i < atraccionesIncluidas.length; i++) {

			if (!atraccionesIncluidas[i].comprobarCupo()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Obtenemos el tipo de la atracción de la posición 0, ya que todas las
	 * atracciones van a tener el mismo tipo.
	 * 
	 */
	@Override
	public TipoDeAtraccion getTipo() {
		return this.atraccionesIncluidas[0].getTipo();
	}

	@Override
	public double getTiempoNecesario() {
		int tiempoTotal = 0;

		for (int i = 0; i < atraccionesIncluidas.length; i++) {
			tiempoTotal += atraccionesIncluidas[i].getTiempoNecesario();
		}
		return tiempoTotal;
	}

	/**
	 * Se realiza comparación de promociones, los métodos para los cálculos se
	 * encuentran en la propia clase.
	 * 
	 */

	@Override
	public int compareTo(Promocion otra) { // Implementar este método en promociones. (O mejor aún, en la interfaz)

		if (this.getCostoDeVisita() == otra.getCostoDeVisita()) {
			return (int) (this.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
		}
		return (this.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
	}

	/**
	 * Pasamos por parámetro las atracciones que estarán incluídas en la promoción.
	 * 
	 * @param atraccionesIncluidas
	 */
	public void setArrayAtracciones(Atraccion[] atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}

	public Atraccion[] getArrayAtracciones() {
		return this.atraccionesIncluidas;
	}

	public String getNombreAtraccion(Atraccion atraccion) {
		return atraccion.getNombre();
		/*
		 * String imprimir = (("[" + "Nombre de atracción 1: " + atraccionesIncluidas[0]
		 * + "," + "Nombre de atracción 2: " + atraccionesIncluidas[1] + "]"));
		 * 
		 * return imprimir;
		 */
	}

	public static void ordenarPorMayorCostoYtiempo(Promocion[] arrayPromociones) {
		Arrays.sort(arrayPromociones);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(atraccionesIncluidas);
		result = prime * result + Objects.hash(nombre);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Arrays.equals(atraccionesIncluidas, other.atraccionesIncluidas) && Objects.equals(nombre, other.nombre);
	}

}