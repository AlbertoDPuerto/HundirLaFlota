package PracticaBarquitos;

public interface ITablero {
	
	public void borra();
	public int getPosicion(int x, int y);
	public int[][] getTablero();
	public int getTamanio();
	public int ocupacion();
	public void setPosicion(int x, int y, int valor);
	public void setTablero(int[][] tablero);
	
}
