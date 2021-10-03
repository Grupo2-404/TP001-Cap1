package turismoEnLaTierraMedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparadorDeSugeribles implements Comparable<Sugerible> {
	private Sugerible sugerible;
	private Usuario usuario;
	
	@Override
	public int compareTo(Sugerible otra) {

		TipoDeAtraccion preferida = this.usuario.getAtraccionPreferida();

		if (this.sugerible.getTipo() == preferida && otra.getTipo() == preferida) {
			if (sugerible instanceof Promocion && otra instanceof Promocion) {
				if (this.sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (this.sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (this.sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion && otra instanceof Atraccion) {
				if (this.sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (this.sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (this.sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion) {
				return -1;
			}
			return 1;
		}
		if (this.sugerible.getTipo() != preferida && otra.getTipo() != preferida) {
			if (sugerible instanceof Promocion && otra instanceof Promocion) {
				if (this.sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (this.sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (this.sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion && otra instanceof Atraccion) {
				if (this.sugerible.getCostoDeVisita() == otra.getCostoDeVisita()) {
					return (int) (this.sugerible.getTiempoNecesario() - otra.getTiempoNecesario()) * -1;
				}
				return (this.sugerible.getCostoDeVisita() - otra.getCostoDeVisita()) * -1;
			} else if (sugerible instanceof Atraccion) {
				return -1;
			}
			return 1;
		}
		if (this.sugerible.getTipo() != preferida && otra.getTipo() == preferida) {
			return -1;
		}
		return 1;
	}

	public static void ordenar(List<Sugerible> listaSugeribles) {
		Collections.sort(listaSugeribles);
	}

}