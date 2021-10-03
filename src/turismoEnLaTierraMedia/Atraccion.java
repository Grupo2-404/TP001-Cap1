package turismoEnLaTierraMedia;

import java.util.List;
import java.util.Objects;

public class Atraccion implements  Sugerible { // Comparable<Atraccion>,

	private String nombre;
	protected int costoDeVisita;
	protected double tiempoNecesario;
	protected int cupoDePersonas;
	private TipoDeAtraccion tipo;
	private Usuario usuario;

	public Atraccion(String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo)  { 
																								
		this.nombre = nombre;;
		this.costoDeVisita = costo;
		this.tiempoNecesario = tiempo;
		this.cupoDePersonas = cupo;
		this.tipo = tipo;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	@Override
	public boolean comprobarCupo() {
		return this.cupoDePersonas > 0;
	}

	@Override
	public TipoDeAtraccion getTipo() {
		return tipo;
	}

	@Override
	public double getTiempoNecesario() {
		return this.tiempoNecesario;
	}

	@Override
	public void restarCupo() {
		this.cupoDePersonas--;
	}
	
	@Override
	public boolean esPromocion() {
		return false;
	}
	
	@Override 
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> lista) {
		lista.add((Atraccion) sugerible);
	}
/*
	@Override
	public int compareTo(Atraccion otra) {

		if (costoDeVisita == otra.costoDeVisita) {
			return (int) (this.tiempoNecesario - otra.tiempoNecesario) * -1;
		}
		return (this.costoDeVisita - otra.costoDeVisita) * -1;
	}
	*/
	
	@Override
	public void imprimirOferta() {
		
		System.out.println("Usted está accediendo a la atracción: " + this.getNombre().toUpperCase() + ".");
		System.out.println("El costo de la atracción es: " + this.getCostoDeVisita() + " monedas.");
		System.out.println("La duración aproximada del recorrido es de: " + this.getTiempoNecesario() + " horas.");
		System.out.println("-----------------------------------------------------------------");
	}
	
	/*
	public static void ordenarPorMayorCostoYtiempo(List<Atraccion> arrayAtracciones) {		
		// El sort obtiene el método de órden de compareTo, por lo tanto actualmente
		// debe ordenar por TipoDeAtraccion, costo y luego por tiempo.
		Collections.sort(arrayAtracciones);
	}
*/
	
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

}