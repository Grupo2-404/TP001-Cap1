package turismoEnLaTierraMedia;

public class Atracciones {
	

	private int costo;
	private double tiempo;
	private int cupo;
	private String atracción;
	
	/*								Estos datos los leemos directamente desde el Enum, a eliminar en versiones futuras si funciona correctamente lo que estoy intentando..
	private final int MORIA = 10;
	private final int MINAS_TIRITH = 5;
	private final int LA_COMARCA = 3;
	private final int MORDOR = 25;
	private final int ABISMO_DE_HELM = 5;
	private final int LOTHLÓRIEN = 35;
	private final int EREBOR = 12;
	private final int BOSQUE_NEGRO = 3;
	*/
	
	
	public int costoDeVisita(int costo) {
		return costo;
	}

	public double promedioTiempoNecesario(double tiempo) {
		return tiempo;
	}
	
	public int cupoDePersonas(int cupo) {
		return cupo;
	}
	
	public String tipoDeAtracción(String atracción) {
		return atracción;
	}

	
	public String getRegistro() {
		return "[" + "Nombre de la atracción: " + this.atracción + "," +  "Costo: " + this.costo + "," + "tiempo estimado: " + this.tiempo + "," + "cupo: " + this.cupo + "]";
	}
	
}