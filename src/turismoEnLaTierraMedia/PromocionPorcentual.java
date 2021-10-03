package turismoEnLaTierraMedia;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private final double PORCENTAJE_DESCUENTO;

	public PromocionPorcentual(String nombre, List<Atraccion> listaAtracciones, double porcentaje) {
		super(nombre, listaAtracciones);
		this.PORCENTAJE_DESCUENTO = porcentaje;
	}

	/**
	 * Como el porcentaje estaba definido como int, no estaba realizando el cálculo
	 * correctamente al dividir por 100. 
	 */
	@Override
	public int getCostoDeVisita() {

		double costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.size(); i++) {
				costoTotal += atraccionesIncluidas.get(i).getCostoDeVisita();
			}
		return (int) Math.round(costoTotal * (1 - PORCENTAJE_DESCUENTO / 100));
	}

}