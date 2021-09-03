package turismoEnLaTierraMedia;

public abstract class Promocion {
	
	private Atraccion [] atraccionesIncluidas;
	
	private String nombre;
	private TipoDeAtraccion tipo;
	
	
	@SuppressWarnings("unused")			
	private String atraccionesIncluídas;
	 
		
		//this.atraccionesIncluidas = atraccionesIncluidas;
	
	
	//???????????.atraccionesIncluidas[0].getCostoDeVisita();
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setArrayAtracciones(Atraccion[]atraccionesIncluidas) {
		this.atraccionesIncluidas = atraccionesIncluidas;
	}
	
	public void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}
	
	
	
	// Atraccion[] arrayAtracciones
	
}