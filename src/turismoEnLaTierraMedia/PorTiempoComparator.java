package turismoEnLaTierraMedia;

import java.util.Comparator;

public class PorTiempoComparator implements Comparator<Atraccion> {

	   @Override
	    public int compare(Atraccion uno, Atraccion otro) {
	        return (int) ((uno.tiempoNecesario - otro.tiempoNecesario) * - 1);
	    }

	}