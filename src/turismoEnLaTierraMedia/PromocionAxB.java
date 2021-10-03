package turismoEnLaTierraMedia;

import java.util.List;

public class PromocionAxB extends Promocion {

	public PromocionAxB(String nombre, List<Atraccion> arrayAtracciones) {
		super(nombre, arrayAtracciones);
	}

	@Override
	public int getCostoDeVisita() {

		int costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.size() - 1; i++) {
			costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
		}
		return costoTotal;
	}

}