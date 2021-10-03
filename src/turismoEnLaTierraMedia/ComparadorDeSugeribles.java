package turismoEnLaTierraMedia;

import java.util.Comparator;

public class ComparadorDeSugeribles implements Comparator<Sugerible> {

	private TipoDeAtraccion preferida;
	
	public ComparadorDeSugeribles (TipoDeAtraccion tipo) {
		this.preferida = tipo;
	}
	
	/**
	 * Comparamos si los tipos son iguales o diferentes, lo que nos interesa, es que la comprobación nos devuelva
	 * un true o false, si la igualdad se cumple, comprobamos si los 2 sugeribles comprobados tienen la misma clase (ya 
	 * sea atracción o promoción)si es el caso, comprobamos si los costos de visita son iguales, si son iguales, 
	 * ordenamos en base al tiempo necesario, sino, ordenamos por el costo de visita. 
	 *  En caso de que los tipos o las clases no sean iguales, las ordenamos en base al tipo o clase, según corresponda.
	 *  
	 */
	@Override
	public int compare(Sugerible sugerible, Sugerible otra) {
		
		if ((sugerible.getTipo() == preferida) == (otra.getTipo() == preferida)){ 
			if ((sugerible instanceof Promocion) == (otra instanceof Promocion)) {
				if (sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion) {
				return 1;
			}
			return -1;
		}
		if (sugerible.getTipo() != preferida){
			return 1;
		}
		return -1;
	}

	/*
	  public int compare(Sugerible sugerible, Sugerible otra) {
		//if ((sugerible.getTipo() == preferida && otra.getTipo() == preferida) ||
		// (sugerible.getTipo() != preferida && otra.getTipo() != preferida)){	Equivalente a línea 18.
		
		if ((sugerible.getTipo() == preferida) == (otra.getTipo() == preferida)){
		
			if (sugerible instanceof Promocion && otra instanceof Promocion) {
				if (sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion && otra instanceof Atraccion) {
				if (sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion) {
				return 1;
			}
			return -1;
		}
		if (sugerible.getTipo() != preferida){
			return 1;
		}
		return -1;
	}
	 */
	
}