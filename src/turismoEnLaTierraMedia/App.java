package turismoEnLaTierraMedia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
	
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
	

	public String getArchivoEntrada(String entrada) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("Atracciones.txt"));
		String linea;
		
		while ((linea = br.readLine()) != null);
			System.out.println(linea);
		
		return linea;
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
	
	public String getArchivoDeSalida(String salida) throws IOException {
		
		PrintWriter archivoDeSalida = new PrintWriter(new FileWriter("ArchivoDeSalida.txt"));
		
		
		
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