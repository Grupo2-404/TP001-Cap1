package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import turismoEnLaTierraMedia.*;

public class AppTest {

	@Test
	public void crearUnaInstanciaDeLaAppCon50UsuariosY20AtraccionesY10Promociones() {

		App sistema = new App(50, 20, 10);

		assertNotNull(sistema);
		assertEquals(50, sistema.getCapacidadUsuarios());
		assertEquals(20, sistema.getCapacidadAtracciones());
		assertEquals(10, sistema.getCapacidadPromociones());
	}

	@Test
	public void agregarNuevosUsuariosAlSistema() throws InvalidNumberException {

		App sistema = new App(50, 20, 10);

		Usuario Eowyn = new Usuario("Eowyn", TipoDeAtraccion.AVENTURA, 10000, 12.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.AVENTURA, 1000, 12.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 10000, 1.00);

		sistema.agregarUsuario(Eowyn);
		sistema.agregarUsuario(Camila);
		sistema.agregarUsuario(Claudia);

		assertEquals("Eowyn", sistema.getUsuarios()[0].getNombre());
		assertEquals("Camila", sistema.getUsuarios()[1].getNombre());
		assertEquals("Claudia", sistema.getUsuarios()[2].getNombre());
	}

	@Test
	public void agregarNuevaAtraccionAlSistema() {

		App sistema = new App(50, 20, 10);
		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);

		sistema.agregarAtraccion(Moria);

		assertEquals("Moria", sistema.getAtracciones()[0].getNombre());
	}
	
	
	
	
	
}
