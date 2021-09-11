package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import turismoEnLaTierraMedia.*;

public class AtraccionTest {

	@Test
	public void QueAtraccionOrdenePorTipoDeAtraccionDespuesPorCostoYdespuesPorTiempo() throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);

		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Moria);

		Atraccion[] arrayEsperado = new Atraccion[2];

		// arrayEsperado[0] = LaComarca;
		// arrayEsperado[2] = Erebor;
		arrayEsperado[0] = Mordor;
		arrayEsperado[1] = Erebor;

		Atraccion[] atraccionPreferida;
		// Atraccion[]atraccionNoPreferida;

		atraccionPreferida = sistema.atraccionesPreferidas(Camila);
		// atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);

		Atraccion.ordenarPorMayorCostoYtiempo(atraccionPreferida);
		// Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);

		assertArrayEquals(arrayEsperado, atraccionPreferida);
		// Assert.assertArrayEquals(arrayEsperado2,atraccionNoPreferida);
	}

	@Test
	public void QueAtraccionOrdenePorTipoDeAtraccionNoPreferidaDespuesPorCostoYdespuesPorTiempo()
			throws InvalidNumberException {

		App sistema = new App(4, 4, 2);

		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);

		Atraccion Moria = new Atraccion("Moria", 10, 2, 6, TipoDeAtraccion.AVENTURA);
		Atraccion Mordor = new Atraccion("Mordor", 25, 3, 4, TipoDeAtraccion.PAISAJE);
		Atraccion Erebor = new Atraccion("Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE);
		Atraccion LaComarca = new Atraccion("LaComarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION);

		sistema.agregarAtraccion(Erebor);
		sistema.agregarAtraccion(Mordor);
		sistema.agregarAtraccion(LaComarca);
		sistema.agregarAtraccion(Moria);

		Atraccion[] arrayEsperado = new Atraccion[2];

		// arrayEsperado[0] = LaComarca;
		// arrayEsperado[2] = Erebor;
		arrayEsperado[0] = Moria;
		arrayEsperado[1] = LaComarca;

		Atraccion[] atraccionNoPreferida;
		// Atraccion[]atraccionNoPreferida;

		atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);
		// atraccionNoPreferida = sistema.atraccionesNoPreferidas(Camila);

		Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);
		// Atraccion.ordenarPorMayorCostoYtiempo(atraccionNoPreferida);

		assertArrayEquals(arrayEsperado, atraccionNoPreferida);
		// Assert.assertArrayEquals(arrayEsperado2,atraccionNoPreferida);
	}
	
}
