package turismoEnLaTierraMedia;

public class PromocionAbsoluta extends Promocion {

	private final int MONEDAS;

	public PromocionAbsoluta(String nombre, Atraccion[] arrayAtracciones, int monedas) {
		super(nombre, arrayAtracciones);
		this.MONEDAS = monedas;
	}

	
	/** En este método, tenemos 2 ideas distintas sobre como debería funcionar, por lo tanto, dejamos ambos escritos, uno funcionando actualmente, y el otro comentado.
	 *  Por un lado, entendemos que las promociones absolutas obtienen un descuento a partir de una cantidad de monedas que ingresa desde el archivo (caso funcionando actualmente).
	 *  Por otro lado, interpretamos que también puede significar que el costo final va a ser el de las monedas que ingresen desde el archivo (sería el caso del return MONEDAS).
	 */
	
	@Override
	public int getCostoDeVisita() {
		
		int costoTotal = 0;
		
		for (int i = 0; i < super.atraccionesIncluidas.length; i++) {
			costoTotal += atraccionesIncluidas[i].getCostoDeVisita();
		}
		return costoTotal - MONEDAS;
		// return MONEDAS;
	}

}