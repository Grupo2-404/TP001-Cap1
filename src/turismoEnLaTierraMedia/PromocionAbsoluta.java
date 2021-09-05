package turismoEnLaTierraMedia;

public class PromocionAbsoluta extends Promocion {

	private final int MONEDAS;

	public PromocionAbsoluta(String nombre, Atraccion[] arrayAtracciones, int monedas) {
		super(nombre, arrayAtracciones);
		this.MONEDAS = monedas;
	}

	@Override
	public int getCostoDeVisita() {

		int costoTotal = 0;

		for (int i = 0; i < super.atraccionesIncluidas.length; i++) {
			costoTotal += atraccionesIncluidas[i].getCostoDeVisita();
		}
		return costoTotal - MONEDAS;
	}

	@Override
	public String getNombre() { // Consultar si es necesario crear el método o lo heredamos directamente.
		return super.getNombre();
	}

}