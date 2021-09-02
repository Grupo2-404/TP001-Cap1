package turismoEnLaTierraMedia;

public class PromocionAbsoluta {
	
	
	public int crearPromocionAbsoluta(String nombreDePromocion, Atraccion[] arrayAtracciones, int monedas, TipoDeAtraccion tipo) {	
	
        int sumaDeCostosDeAtracciones = arrayAtracciones[0].getCostoDeVisita() + arrayAtracciones[1].getCostoDeVisita();
        int precioFinalPromocionAbsoluta = sumaDeCostosDeAtracciones - monedas;
        return precioFinalPromocionAbsoluta;
    }
	
}