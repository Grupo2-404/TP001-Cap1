package turismoEnLaTierraMedia;

import java.util.List;

public interface Sugerible  {
	
	public String getNombre();
	
	public int getCostoDeVisita();
	
	public double getTiempoNecesario();
	
	//Este método trae el tipo de la atracción o promoción. No el preferido del usuario.
	public TipoDeAtraccion getTipo();
	
	public boolean comprobarCupo();	
	
	public void restarCupo();
	
	public void imprimirOferta();
	
	public boolean esPromocion();
	
	public void agregarAtraccion(Sugerible sugerible, List<Atraccion> atraccionesAceptadas);

}