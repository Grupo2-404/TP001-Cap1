package turismoEnLaTierraMedia;

public class PromocionAbsoluta extends Promocion {
	

	public int crearPromocionAbsoluta(String nombreDePromocion, Atraccion[] arrayAtracciones, int monedas, TipoDeAtraccion tipo) {	
	
        int sumaDeCostosDeAtracciones = arrayAtracciones[0].getCostoDeVisita() + arrayAtracciones[1].getCostoDeVisita();
        int precioFinalPromocionAbsoluta = sumaDeCostosDeAtracciones - monedas;
        return precioFinalPromocionAbsoluta;
    }
	

	@Override
	public String getNombre() {		// Consultar si es necesario crear el método o lo heredamos directamente.
		return super.getNombre();
	}
	
}