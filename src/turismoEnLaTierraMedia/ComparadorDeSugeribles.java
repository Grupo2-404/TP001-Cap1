package turismoEnLaTierraMedia;

import java.util.Comparator;

public class ComparadorDeSugeribles implements Comparator<Sugerible> {

	private TipoDeAtraccion preferida;
	
	public ComparadorDeSugeribles (TipoDeAtraccion tipo) {
		this.preferida = tipo;
	}
	
	@Override
	public int compare(Sugerible sugerible, Sugerible otra) {

		if (sugerible.getTipo() == preferida && otra.getTipo() == preferida) {
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
		if (sugerible.getTipo() != preferida && otra.getTipo() != preferida) {
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

}