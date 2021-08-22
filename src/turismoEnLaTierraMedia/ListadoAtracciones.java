package turismoEnLaTierraMedia;

public enum ListadoAtracciones {

	MORIA (10, 2, 6, "Aventura"),
	MINAS_TIRITH (5, 2.5, 25 , "Paisaje"),
	LA_COMARCA (3, 6.5, 150, "Degustación"),
	MORDOR (25, 3, 4, "Aventura"),
	ABISMO_DE_HELM (5, 2, 15, "Paisaje"),
	LOTHLÓRIEN (35, 1, 30, "Degustación"),
	EREBOR (12, 3, 32, "Paisaje"),
	BOSQUE_NEGRO (3, 4, 12, "Aventura");
	
	
	@SuppressWarnings("unused")		//quitar anotación cuando se utilice este valor en otra clase.
	private final int COSTO;
	@SuppressWarnings("unused")		//quitar anotación cuando se utilice este valor en otra clase.
	private final double HORAS;
	@SuppressWarnings("unused")		//quitar anotación cuando se utilice este valor en otra clase.
	private final int CUPO;
	@SuppressWarnings("unused")		//quitar anotación cuando se utilice este valor en otra clase.
	private final String TIPO;
	
	ListadoAtracciones (int costo, double horas, int cupo, String tipo){
		this.COSTO = costo;
		this.HORAS = horas;
		this.CUPO = cupo;
		this.TIPO = tipo;
	}
	
}