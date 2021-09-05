package turismoEnLaTierraMedia;

public class PromocionAxB extends Promocion {

	public PromocionAxB(String nombre, Atraccion[] arrayAtracciones) {
		super(nombre, arrayAtracciones);
	}

	@Override
	public int getCostoDeVisita() {

		int costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.length - 1; i++) {
			costoTotal += atraccionesIncluidas[i].getCostoDeVisita();
		}
		return costoTotal;
	}

}
