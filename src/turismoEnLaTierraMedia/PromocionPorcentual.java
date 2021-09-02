package turismoEnLaTierraMedia;

public class PromocionPorcentual {

	private int porcentajeDescuento;
	
	public int nuevaPromocionPorcentual(String nombreDePromocion, Atraccion[] arrayAtracciones, int monedas, TipoDeAtraccion tipo) {
		  int sumaDeCostosDeAtracciones = arrayAtracciones[0].getCostoDeVisita() + arrayAtracciones[1].getCostoDeVisita();
	        int precioFinalPromocionAbsoluta = sumaDeCostosDeAtracciones - monedas;
	        return precioFinalPromocionAbsoluta;
	   }

}