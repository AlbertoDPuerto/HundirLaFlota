package PracticaBarquitos;

public interface IVista {
	
	public static final int COMIENZO_X = 40;
	public static final int COMIENZO_Y = 40;
	public static final int LADO = 30;
	
	public void paint (java.awt.Graphics g);
	public void update (java.util.Observable o, java.lang.Object arg);

}
