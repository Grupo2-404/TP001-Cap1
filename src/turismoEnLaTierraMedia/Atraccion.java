package turismoEnLaTierraMedia;

import java.util.Arrays;

public class Atraccion implements Comparable<Atraccion> {
	
	private String nombre;
	protected int costoDeVisita;
	protected double tiempoNecesario;
	protected int cupoDePersonas;
	private TipoDeAtraccion tipo;
	
	
	public Atraccion (String nombre, int costo, double tiempo, int cupo, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costo;
	    this.tiempoNecesario = tiempo;
	    this.cupoDePersonas = cupo;
	    this.tipo = tipo;
	}
	
	
	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	
	@Override
	public int compareTo (Atraccion otra){
		   if (costoDeVisita == otra.costoDeVisita) {
		 return (int) (this.tiempoNecesario - otra.tiempoNecesario) * -1;  // revisar como es la conversión a int por riesgo de que redondee a 0.
		}	
		 return (this.costoDeVisita - otra.costoDeVisita) * -1;
	}
	
	
	 static void ordenarPorMayorCostoYtiempo(Atraccion[] arrayAtracciones) {
	        Arrays.sort(arrayAtracciones);
	        for(int i = 0 ; i < arrayAtracciones.length; i++) {
	            System.out.println((i + 1) + ". " + arrayAtracciones[i].costoDeVisita);
	            System.out.println((i + 1) + ". " + arrayAtracciones[i].tiempoNecesario);
	            System.out.println("-------------------");
	        }
	    }
	
	
	 
	   public static void main(String[] args) {
	     Atraccion [] arrayAtracciones = new Atraccion[3];
	     arrayAtracciones[0] = new Atraccion("Atracción 1", 30, 2, 40, TipoDeAtraccion.AVENTURA);
	     arrayAtracciones[1] = new Atraccion("Atracción 2",10, 2, 50, TipoDeAtraccion.PAISAJE);
	     arrayAtracciones[2] = new Atraccion("Atracción 3", 20, 2, 20, TipoDeAtraccion.AVENTURA);

	     ordenarPorMayorCostoYtiempo(arrayAtracciones);
	    }

	   
	   
	   /*
		public String getRegistro() {
			return "[" + "Nombre de la atracción: " + this.atraccion + "," +  "Costo: " + this.costoDeVisita + "," + "tiempo estimado: " + this.tiempoNecesario + "," + "cupo: " + this.cupoDePersonas + "]";
		}
	   */
}