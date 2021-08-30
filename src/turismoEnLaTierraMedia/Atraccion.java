package turismoEnLaTierraMedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Atraccion implements Comparable<Atraccion> {
	
	private int costoDeVisita;
	protected double tiempoNecesario;
	private int cupoDePersonas;
	private String atracción;
	
	private Usuario usuario;
	private boolean aceptar;
	private int costoAtraccionAceptada;
	private double tiempoAtraccionAceptada;
	
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
	
	 Atraccion atraccion1 = new Atraccion(costoDeVisita, tiempoNecesario, cupoDePersonas);
	
	
	
	
	  public Atraccion (int costo, double tiempo, int cupo) {
	        this.costoDeVisita = costo;
	        this.tiempoNecesario = tiempo;
	        this.cupoDePersonas = cupo;
	    }
	
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
		return "[" + "Nombre de la atracción: " + this.atracción + "," +  "Costo: " + this.costoDeVisita + "," + "tiempo estimado: " + this.tiempoNecesario + "," + "cupo: " + this.cupoDePersonas + "]";
	}

	@Override
	public int compareTo (Atraccion otra){
		 return (this.costoDeVisita - otra.costoDeVisita) * -1;	//Si da entero negativo, significa que el costo de la otra es mayor, si da 0, el costo es el mismo, si es entero positivo, el costo de la primera es mayor.
		}	
	
	
	// Crear un for con un if dentro, que revise si al usuario le queda oro y tiempo suficiente para acceder a una atracción, cuando el oro o el tiempo sean menores 
	//que el menor tiempo o costo de la atracción que menor oro o tiempo requiera, se sale del for.
	
	
	private void oroActual() {
		//VALIDAR
		usuario.presupuesto -= costoAtraccionAceptada;	
	}
	
	private void tiempoActual() {
		//VALIDAR
		 usuario.tiempoDisponible -= tiempoAtraccionAceptada;
	}
	
	private boolean aceptarOferta() {
		return this.aceptar;
	}
	
	public void ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException {				// ver si es private o public.
		
		Atraccion[] arrayDePrueba = new Atraccion[3];
		
	//	PrintWriter sugerenciaDiaria = new PrintWriter(new FileWriter("SugerenciaDiaria.txt"));
		
		for (int i = 0; i > arrayDePrueba.length; i++) {
			if(arrayDePrueba[i].tiempoNecesario <= usuario.tiempoDisponible && arrayDePrueba[i].costoDeVisita <= usuario.presupuesto) {
		//		aceptarOferta();
		//		if(aceptar == true) {		// Como realizar interacción con el usuario?!
					cupoDePersonas --;
					tiempoAtraccionAceptada = arrayDePrueba[i].tiempoNecesario;
					this.tiempoActual();
					costoAtraccionAceptada = arrayDePrueba[i].costoDeVisita;
					this.oroActual();
				//	sugerenciaDiaria.println(arrayDePrueba[i]);
				//	sugerenciaDiaria.close();
				}
			}
		//	aceptar = false;				// esto servirá de algo?!
		}
//	}


	
	
	public static Atraccion[] priorizarAtracciones() {
        Atraccion[] arrayAtracciones = new Atraccion[3];
        Arrays.sort(arrayAtracciones);

        return arrayAtracciones;
    }
	
	   static void imprimeArrayAtracciones(Atraccion[] arrayAtracciones) {
	        for(int i = 0 ; i < arrayAtracciones.length; i++) {
	            System.out.println((i + 1) + ". " + arrayAtracciones[i].costoDeVisita);
	        }
	    }
	   
	   public static void main(String[] args) {
	        Atraccion [] arrayAtracciones = new Atraccion[3];
	        Atraccion [] arrayDePrueba = new Atraccion[4];
	        arrayAtracciones[0] = new Atraccion(30, 2, 40);
	        arrayAtracciones[1] = new Atraccion(10, 2, 50);
	        arrayAtracciones[2] = new Atraccion(20, 2, 20);
	        arrayDePrueba[0] = new Atraccion(10, 2, 50);
	        arrayDePrueba[1] = new Atraccion(20, 2, 20);
	        arrayDePrueba[2] = new Atraccion(30, 2, 40);
	        //priorizarAtracciones();
	        imprimeArrayAtracciones(arrayAtracciones);
	        imprimeArrayAtracciones(arrayDePrueba);
	        
	    }
	   
}