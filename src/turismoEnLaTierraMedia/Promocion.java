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

		for (int i = 0; i < atraccionesIncluidas.length; i++) {
			if (atraccionesIncluidas[i] != null) {
				atraccionesIncluidas[i].restarCupo();
			}
		}
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean comprobarCupo() {

		for (int i = 0; i < atraccionesIncluidas.length; i++) {
			if (atraccionesIncluidas[i] != null) {
				if (!atraccionesIncluidas[i].comprobarCupo()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Obtenemos el tipo de la atracción de la posición 0, ya que todas las
	 * atracciones van a tener el mismo tipo.
	 */
	@Override
	public TipoDeAtraccion getTipo() {
		return this.atraccionesIncluidas[0].getTipo();
	}

	@Override
	public double getTiempoNecesario() {
		double tiempoTotal = 0;

		for (int i = 0; i < atraccionesIncluidas.length; i++) {
			if (atraccionesIncluidas[i] != null) {
				tiempoTotal += atraccionesIncluidas[i].getTiempoNecesario();
			}
		}
		return tiempoTotal;
	}

	/**
	 * Se realiza comparación de promociones, los métodos para los cálculos se
	 * encuentran en la propia clase.
	 * 
	 */
	@Override
	public int compareTo(Promocion otra) {

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
	}

	@Override
	public void imprimirOferta() {

		System.out.println("Usted está accediendo a la promoción: " + this.getNombre().toUpperCase() + ".");
		System.out.println("Esta promo incluye las siguientes atracciones:");

		for (int i = 0; i < this.getArrayAtracciones().length; i++) {
			if (getArrayAtracciones()[i] != null) {
				System.out.println((i + 1) + ". " + this.getNombreAtraccion(this.getArrayAtracciones()[i]).toUpperCase());			
			}
		}

		System.out.println("El costo de la promoción es: " + this.getCostoDeVisita() + " monedas.");
		System.out.println("La duración aproximada del recorrido es de: " + this.getTiempoNecesario() + " horas.");
		System.out.println("-----------------------------------------------------------------");
	}

	public static void ordenarPorMayorCostoYtiempo(Sugerible[] arrayPromociones) { // evaluar si pasar a app tanto el de
																					// atracciones como el de promos.
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

	public static void main(String[] args) {

		Atraccion Mordor = new Atraccion("Mordor", 12, 3, 10, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 15, 3, 30, TipoDeAtraccion.PAISAJE);
		Atraccion MinasTirith = new Atraccion("MinasTirith", 5, 2.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion LaComarca = new Atraccion("LaComarca", 23, 6.5, 20, TipoDeAtraccion.DEGUSTACION);
		Atraccion Lothlorien = new Atraccion("Lothlorien", 13, 4, 60, TipoDeAtraccion.DEGUSTACION);
		Atraccion AbismoDeHelm = new Atraccion("AbismoDeHelm", 33, 6.5, 50, TipoDeAtraccion.DEGUSTACION);

		Atraccion[] arrayPromoPorcentual = new Atraccion[2];
		arrayPromoPorcentual[0] = Mordor;
		arrayPromoPorcentual[1] = Erebor;

		Atraccion[] arrayPromoPorcentual2 = new Atraccion[2];
		arrayPromoPorcentual2[0] = MinasTirith;
		arrayPromoPorcentual2[1] = LaComarca;

		Atraccion[] arrayPromoPorcentual3 = new Atraccion[2];
		arrayPromoPorcentual3[0] = Lothlorien;
		arrayPromoPorcentual3[1] = AbismoDeHelm;

		Promocion Promocion1 = new PromocionPorcentual("PromoPorcentual1", arrayPromoPorcentual, 10);
		Promocion Promocion2 = new PromocionPorcentual("PromoPorcentual2", arrayPromoPorcentual2, 10);
		Promocion Promocion3 = new PromocionPorcentual("PromoPorcentual3", arrayPromoPorcentual3, 10);

		Promocion1.imprimirOferta();
		Mordor.imprimirOferta();

	}

}