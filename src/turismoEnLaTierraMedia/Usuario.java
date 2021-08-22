package turismoEnLaTierraMedia;

public abstract class Usuario {

	@SuppressWarnings("unused")						//quitar anotación cuando se utilice este valor en otra clase.
	private String nombre = "";
	@SuppressWarnings("unused")						//quitar anotación cuando se utilice este valor en otra clase.
	private int presupuesto = 0;
	@SuppressWarnings("unused")						//quitar anotación cuando se utilice este valor en otra clase.
	private String atracciónPreferida = "";
	@SuppressWarnings("unused")						//quitar anotación cuando se utilice este valor en otra clase.
	private double tiempoDisponible;
	
	
	
	
	public String getNombre(String nombre) {
		return this.nombre = nombre;
	}
	
	public String getAtracciónPreferida(String atracciónPreferida) {
		return this.atracciónPreferida = atracciónPreferida;
	}
	
	public int getPresupuesto(int presupuesto) {
		return this.presupuesto = presupuesto;
	}
	
	public double getTiempoDisponible(double tiempoDisponible) {
		return this.tiempoDisponible = tiempoDisponible;
	}
	
	/*		
	 public Usuario (String nombre, String atracciónPreferida, int Presupuesto, double tiempoDisponible){
	 	this.nombre = nombre;
	 	this.atracciónPreferida = atracciónPreferida;
	 	this.presupuesto = presupuesto;
	 	this.tiempoDisponible = tiempoDisponible;
	 }
	 
	 Usuario[] registroUsuario = new registroUsuario[] {String nombre, String atracciónPreferida, int presupuesto, double tiempoDisponible};	
	 
	 
	 */
	
	//	ArrayList<String> registro = new ArrayList<String>();
	
	public String getRegistro() {
		return "[" + "Nombre: " + this.nombre + "," +  "Atracción preferida: " + this.atracciónPreferida + "," + "Presupuesto: " + this.presupuesto + "," + "Tiempo disponible: " + this.tiempoDisponible + "]";
	}
	
	
}