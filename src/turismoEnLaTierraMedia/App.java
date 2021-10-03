package turismoEnLaTierraMedia;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {

	private List<Promocion> promocionesVigentes = new ArrayList<Promocion>();
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private static List<Sugerible> sugerencias = new ArrayList<Sugerible>();

	public void agregarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void agregarAtraccion(Atraccion atraccion) {
		this.atracciones.add(atraccion);
	}

	/**
	 * Recibimos los parámetros para crear una promo y agregarla al listado de
	 * promocionesVigentes.En
	 * 
	 * @param nombre
	 * @param listaDeAtracciones
	 * @param descuento
	 * @param tipo
	 */
	public void agregarPromocion(Promocion promocion) {
		this.promocionesVigentes.add(promocion);
	}
	
	public void ordenar(TipoDeAtraccion preferida) {
		Collections.sort(sugerencias, new ComparadorDeSugeribles(preferida));
	}
	
	public void listaSugeribles(Usuario usuario) {
		
		this.sugerencias.addAll(this.promocionesVigentes);
		this.sugerencias.addAll(this.atracciones);
		this.ordenar(usuario.getAtraccionPreferida());
	}
	
	/**
	 * Tenemos una lista de atracciones y una de promociones. El printWriter del
	 * método cargar promociones, va a leer y cargar la lista de las promociones, lo
	 * mismo con las atracciones, las cuales van a estar divididas entre Promociones
	 * preferidas y no preferidas, y atracciones aceptadas y no aceptadas, [tenemos
	 * en cuenta que esto puede reducirse con la implementación de Sugerible] estos
	 * arrays van a ir a un ciclo for con el siguiente orden: promociones
	 * preferidas, atracciones preferidas, promociones no preferidas y atracciones
	 * no preferidas. En cada ciclo, vamos a comprobar que el usuario tenga oro y
	 * tiempo para acceder a la atracción, si los tiene, le va a consultar en cada
	 * ciclo al usuario si acepta la atracción. Si no acepta, pasamos al siguiente
	 * elemento y volvemos a hacer la comprobación y la consulta. Si acepta, vamos a
	 * restar 1 cupo en la atracción, vamos a guardar en una variable el dato del
	 * costo y el tiempo necesario para hacer esa atracción, y se los restamos al
	 * usuario que la aceptó, guardamos la atracción aceptada en un array que vamos
	 * a mostrar en el retorno.
	 * 
	 * @param usuario
	 * @return
	 * @throws IOException
	 * @throws InvalidNumberException
	 */
	public void ofertarMientrasQueHayaOroYtiempo(Usuario usuario) throws IOException, InvalidNumberException {

		List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();
		List<Sugerible> itinerario = new ArrayList<Sugerible>();
		
		this.listaSugeribles(usuario);
		mensajeBienvenida();

		for (int i = 0; i < sugerencias.size(); i++) {

			if (sugerencias.get(i).esPromocion()) {
				if (!seRepiteAtraccionEnPromocion((Promocion) sugerencias.get(i), atraccionesAceptadas) // contains
						&& sugerencias.get(i).getTiempoNecesario() <= usuario.tiempoDisponible
						&& sugerencias.get(i).getCostoDeVisita() <= usuario.presupuesto
						&& sugerencias.get(i).comprobarCupo()) {

					aceptaOferta(usuario, atraccionesAceptadas, itinerario, i);
				}
			} else {
				if (!estaAtraccionEnAtracciones((Atraccion) sugerencias.get(i), atraccionesAceptadas)
						&& sugerencias.get(i).getTiempoNecesario() <= usuario.tiempoDisponible
						&& sugerencias.get(i).getCostoDeVisita() <= usuario.presupuesto
						&& sugerencias.get(i).comprobarCupo()) {

					aceptaOferta(usuario, atraccionesAceptadas, itinerario, i);
				}
			}
		}
		usuario.recibirItinerario(itinerario);	
		this.imprimirItinerario(usuario);
	}

	private void mensajeBienvenida() {
		System.out.println("");
		System.out.println("Bienvenid@ a nuestra empresa de Turismo. ¡Esperamos que disfrute su estadía!");
		System.out.println(
				"El 5% de lo recaudado en la actividad, será donado para la investigación de métodos sofisticados de limpieza e higiene personal");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
	}

	private void aceptaOferta(Usuario usuario, List<Atraccion> atraccionesAceptadas, List<Sugerible> itinerario,
			int i) {
		if (usuario.aceptaOferta(sugerencias.get(i))) {
			sugerencias.get(i).restarCupo();
			usuario.aceptoOfertaSugeridaYseDescontoTiempoYpresupuesto(sugerencias.get(i));
			itinerario.add(sugerencias.get(i));
			System.out.println("Su presupuesto actual es de: " + usuario.getPresupuesto());
			System.out.println("Su tiempo disponible es de: " + usuario.getTiempoDisponible());
			System.out.println("------------------------------------------------------------");
			sugerencias.get(i).agregarAtraccion(sugerencias.get(i), atraccionesAceptadas);
		}
	}

	public void ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios() throws IOException, InvalidNumberException {

		for (int i = 0; i < usuarios.size(); i++) {
			System.out.println("Bienvenid@: " + usuarios.get(i).getNombre());
			ofertarMientrasQueHayaOroYtiempo(usuarios.get(i));
		}
	}

	/**
	 * Revisamos entre las promociones y atracciones que entre las promos sugeridas
	 * si se repiten o no, devolvemos un boolean. Funciona, crean en nosotros.
	 * 
	 * @param unaAtraccion
	 * @return
	 */
	public boolean estaAtraccionEnAtracciones(Atraccion atraccion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion Atr : atraccionesAceptadas) {
	
				if (Atr.equals(atraccion)) {
					return true;		
			}
		}
		return false;
	}

	public boolean seRepiteAtraccionEnPromocion(Promocion promocion, List<Atraccion> atraccionesAceptadas) {

		for (Atraccion unaAtraccion : promocion.getArrayAtracciones()) { // atracciones incluídas.

			if (estaAtraccionEnAtracciones(unaAtraccion, atraccionesAceptadas)) {
				return true;
			}
		}
		return false;
	}

	public void cargarAtracciones() {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("Atracciones.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoAtracciones = linea.split(",");

					String nombre = archivoAtracciones[0];
					if (nombre.equals("") || nombre.equals(null))
						throw new InvalidNumberException("El nombre no puede quedar vacío");

					int costo = Integer.parseInt(archivoAtracciones[1]);
					if (costo <= 0)
						throw new InvalidNumberException("El costo no puede ser negativo o 0");

					double tiempo = Double.parseDouble(archivoAtracciones[2]);
					if (tiempo <= 0)
						throw new InvalidNumberException("El tiempo no puede ser negativo o 0");

					int cupo = Integer.parseInt(archivoAtracciones[3]);
					if (cupo <= 0)
						throw new InvalidNumberException("El cupo no puede ser negativo o 0");

					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(archivoAtracciones[4]);

					Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
					this.agregarAtraccion(atraccion);
					System.out.println("La atracción " + atraccion.getNombre() + " se creó correctamente");

				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción no es correcto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void cargarUsuarios() {

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader("Usuarios.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoUsuarios = linea.split(",");

					String nombre = archivoUsuarios[0];

					TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(archivoUsuarios[1]);

					int presupuesto = Integer.parseInt(archivoUsuarios[2]);
					if (presupuesto <= 0)
						throw new InvalidNumberException("El presupuesto no puede ser negativo o 0");

					double tiempo = Double.parseDouble(archivoUsuarios[3]);
					if (tiempo <= 0)
						throw new InvalidNumberException("El tiempo no puede ser negativo o 0");

					Usuario usuario = new Usuario(nombre, tipo, presupuesto, tiempo);
					this.agregarUsuario(usuario);
					System.out.println("El usuario " + usuario.getNombre() + " se creó correctamente");
				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción preferida no es correcto");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) { // Si fr es distinto de null, o sea, si existe, vamos a cerrar el archivo.
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void cargarPromociones() {

		FileReader fr = null;
		BufferedReader br = null;
		Promocion promo = null;

		try {
			fr = new FileReader("Promociones.txt");
			br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				try {
					String[] archivoPromociones = linea.split(",");

					TipoDePromocion tipo = TipoDePromocion.valueOf(archivoPromociones[0]);
					if (tipo == TipoDePromocion.ABSOLUTA) {

						String nombre = archivoPromociones[1];

						int costo = Integer.parseInt(archivoPromociones[2]);
						if (costo <= 0) {
							throw new InvalidNumberException("test");
						}

						String[] listaAtracciones = new String[archivoPromociones.length - 3];
						for (int i = 3, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];
						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.size(); k++) {
								if (listaAtracciones[i].equals(atracciones.get(k).getNombre())) {
									atraccionesIncluidas[i] = atracciones.get(k);
								}
							}
						}

						promo = new PromocionAbsoluta(nombre, atraccionesIncluidas, costo);
					}

					if (tipo == TipoDePromocion.PORCENTUAL) {

						String nombre = archivoPromociones[1];

						double costo = Double.parseDouble(archivoPromociones[2]);
						if (costo <= 0) {
							throw new InvalidNumberException("test");
						}

						String[] listaAtracciones = new String[archivoPromociones.length - 3];
						for (int i = 3, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];
						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.size(); k++) {
								if (listaAtracciones[i].equals(atracciones.get(k).getNombre())) {
									atraccionesIncluidas[i] = atracciones.get(k);

								}
							}
						}
						promo = new PromocionPorcentual(nombre, atraccionesIncluidas, costo);
					}

					if (tipo == TipoDePromocion.AXB) {

						String nombre = archivoPromociones[1];

						String[] listaAtracciones = new String[archivoPromociones.length - 2];
						for (int i = 2, k = 0; i < archivoPromociones.length; i++, k++) {
							listaAtracciones[k] = archivoPromociones[i];
						}

						Atraccion[] atraccionesIncluidas = new Atraccion[listaAtracciones.length];
						for (int i = 0; i < listaAtracciones.length; i++) {
							for (int k = 0; k < atracciones.size(); k++) {
								if (listaAtracciones[i].equals(atracciones.get(k).getNombre())) {
									atraccionesIncluidas[i] = atracciones.get(k);
								}
							}
						}
						promo = new PromocionAxB(nombre, atraccionesIncluidas);
					}
					this.agregarPromocion(promo);
					System.out.println("La promoción " + promo.getNombre() + " se creó correctamente");
				} catch (InvalidNumberException ine) {
					System.err.println(ine.getMessage());

				} catch (NumberFormatException nfe) {
					System.err.println("Uno de los datos leídos no tiene el formato correcto");

				} catch (IllegalArgumentException iae) {
					System.err.println("El tipo de atracción preferida no es correcto");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("-------------------------------------------------------");
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * El método almacena el costo y el tiempo de cada atracción o promoción
	 * aceptada y los suma hasta terminar de recorrer la lista, mostramos ambos
	 * resultados al final de la ejecución. Luego, mostramos en consola (y al mismo
	 * tiempo almacenamos en el archivo de salida) el nombre de cada atracción o
	 * promoción aceptada. El DateTimeFormatter y el LocalDateTime se utilizan para
	 * generar un nombre de archivo único (en base a la fecha y hora), junto con el
	 * nombre de usuario.
	 * 
	 * @throws IOException
	 */
	public void imprimirItinerario(Usuario usuario) throws IOException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();

		int costoTotal = 0;
		double tiempoTotal = 0.0;
		PrintWriter salida = new PrintWriter(
				new FileWriter(dtf.format(now) + " " + usuario.getNombre() + " Ticket.out"));

		salida.println("Ud. ha adquirido: ");
		System.out.println("Ud. ha adquirido: ");
		for (int i = 0; i < usuario.getItinerario().size(); i++) {
			if (usuario.getItinerario().get(i) != null) {
				costoTotal += usuario.getItinerario().get(i).getCostoDeVisita();
				tiempoTotal += usuario.getItinerario().get(i).getTiempoNecesario();
				salida.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase());
				System.out.println((i + 1) + ". " + usuario.getItinerario().get(i).getNombre().toUpperCase());
			}
		}
		salida.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
		salida.println("El tiempo necesario para realizar su itinerario es: " + tiempoTotal + " horas.");
		salida.println("Muchas gracias por haber elegido nuestros servicios.¡Esperamos que disfrute su recorrido!");
		System.out.println("El costo total de su itinerario es: " + costoTotal + " monedas.");
		System.out.println("La duración total de su itinerario es de: " + tiempoTotal + " horas.");
		System.out.println("Muchas gracias por haber elegido nuestros servicios.¡Esperamos que disfrute su recorrido!");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		salida.close();
	}

	public static void main(String[] args) throws InvalidNumberException, IOException {

		App sistema = new App();
		//Usuario usuario = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 10000, 12.00);

		sistema.cargarUsuarios();
		sistema.cargarAtracciones();
		sistema.cargarPromociones();
		//sistema.listaSugeribles(usuario);
		// sistema.atraccionesPreferidas(usuario);
		// sistema.atraccionesNoPreferidas(usuario);

		 // for (int i = 0; i < sugerencias.size(); i++) {
		 //System.out.println(sugerencias.get(i).getNombre().toUpperCase());
		  
		  //System.out.println((i + 1) + ". " +
		  //getNombreAtraccion(getArrayAtracciones()[i]).toUpperCase()); 
		  //  }
		sistema.ofertarMientrasQueHayaOroYtiempoAtodosLosUsuarios();
	}
}