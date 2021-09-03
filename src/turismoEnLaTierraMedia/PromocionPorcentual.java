package turismoEnLaTierraMedia;

public class PromocionPorcentual extends Promocion {

	private int porcentajeDescuento;
	
	
	
	public PromocionPorcentual(String nombre, Atraccion[] arrayAtracciones, int porcentaje, TipoDeAtraccion tipo) {
		super.setNombre(nombre);
		this.porcentajeDescuento = porcentaje;
		super.setArrayAtracciones(arrayAtracciones);
		super.setTipo(tipo);
	}
		

	public int nuevaPromocionPorcentual(String nombreDePromocion, Atraccion[] arrayAtracciones, int descuento, TipoDeAtraccion tipo){
        int sumaDeCostosAtracciones = arrayAtracciones[0].getCostoDeVisita() + arrayAtracciones[1].getCostoDeVisita();
        int costoFinalPromocionPorcentual = sumaDeCostosAtracciones*(1- descuento/100);
        return costoFinalPromocionPorcentual;
    }

}