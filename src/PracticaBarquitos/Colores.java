package PracticaBarquitos;

import java.awt.Color;

public class Colores {
	public static final Color[] color = {Color.YELLOW, Color.BLUE, Color.RED, Color.DARK_GRAY};
	public static final int CUBIERTO = 0;
	public static final int AGUA = 1;
	public static final int TOCADO = 2;
	public static final int HUNDIDO = 3;
	
	public static int indice (java.awt.Color c){
		for (int i=0; i<color.length; i++){
			if(color[i].equals(c))
				return i;
		}
		return -1;
	}

}
