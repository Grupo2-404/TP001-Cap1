package turismoEnLaTierraMedia;

public class Sistema {
	
	ListadoAtracciones listado;				// No creé TipoDeAtracción tipo porque podría no ser necesario si funciona la implementación nueva del ListadoAtracciones.
	@SuppressWarnings("unused")				// Quitar al utilizar en el código este atributo.
	private String[] SugerenciaDiaria;
	
	// Plantilla base para empezar a crear el código.
	
	public String sugerirVisitas(String visitas) {
		return visitas;
	}
	
	public String getItinerarios(String itinerarios) {
		return itinerarios;
	}
	

	public String getArchivoEntrada(String entrada) {
		return entrada;
	}
	
	public String guardarSugerenciaDiaria(String sugerenciaDiaria) {
		return sugerenciaDiaria;
	}
	
	public String getResumen(String resumen) {
		return resumen;
	}
	
	public int setPresupuesto(int presupuesto) {
		return presupuesto;
	}
	
	public int setTiempoDisponible(int tiempo) {
		return tiempo;
	}
	
	public String getArchivoDeSalida(String salida) {
		return salida;
	}
	
	
	/*		Revisar si alguno de estos métodos es necesario.
	 	public String getPromocionesVigentes(String promo) {		
		return promo;
	}
	
	public String usuariosConSusPreferencias(String usuariosPreferencias) {
		return usuariosPreferencias;
	}
	
	public String atraccionesConSusCaracterísticas(String característicasAtracciones) {
		return característicasAtracciones;
	}
	
	public String sugerirPromoción(String promoSugerida) {
		return promoSugerida;
	}
	 */
	
}