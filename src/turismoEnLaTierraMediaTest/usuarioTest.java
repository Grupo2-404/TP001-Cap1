package turismoEnLaTierraMediaTest;

import static org.junit.Assert.*;
import org.junit.Test;
import turismoEnLaTierraMedia.*;

public class usuarioTest {

	@Test
	public void queAlCrearUnUsuarioSeCreeCorrectamente() throws InvalidNumberException {

		Usuario Axel = new Usuario("Axel", TipoDeAtraccion.AVENTURA, 10000, 12.00);
		Usuario Bruno = new Usuario("Bruno", TipoDeAtraccion.DEGUSTACION, 5000, 4.00);
		Usuario Camila = new Usuario("Camila", TipoDeAtraccion.PAISAJE, 22000, 8.00);
		Usuario Claudia = new Usuario("Claudia", TipoDeAtraccion.AVENTURA, 12000, 6.00);
		Usuario Gimena = new Usuario("Gimena", TipoDeAtraccion.PAISAJE, 15000, 2.00);

		assertEquals(10000, Axel.getPresupuesto(), 0.001);
		assertEquals(Bruno.getAtraccionPreferida(), TipoDeAtraccion.DEGUSTACION);
		assertEquals(Camila.getTiempoDisponible(), 8.00, 0.001);
		assertEquals(Claudia.getNombre(), "Claudia");
		assertEquals(15000, Gimena.getPresupuesto(), 0.001);
	}

	
}
