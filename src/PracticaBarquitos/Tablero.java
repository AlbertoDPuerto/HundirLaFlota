package PracticaBarquitos;
import java.util.Observable;

public class Tablero implements ITablero{
	
	private int tamanio;
	private int[][] tablero;
	
	public Tablero(int tamanio, boolean jugador){
		this.tamanio=tamanio;
		this.tablero = new int [tamanio][tamanio];
		if(!jugador) 
			this.borra(); //Rellenar tablero de agua
		else this.cubierto();
	}
	
	public void borra(){
		for(int i=0; i<tamanio; i++)
			for(int j=0; j<tamanio; j++){
				tablero[i][j]=Colores.AGUA;
			}
	}
	
	public void cubierto() {
		for(int i=0; i<tamanio; i++)
			for(int j=0; j<tamanio; j++){
				tablero[i][j]=Colores.CUBIERTO;
			}
	}
	
	public int getPosicion(int x, int y){
		return tablero[x][y];
	}

	public int[][] getTablero(){
		return tablero;
	}
	
	public int getTamanio(){
		return tamanio;
	}
	
	public int ocupacion(){
		int cont=0;
		for(int i=0; i<tamanio; i++)
			for(int j=0; j<tamanio; j++){
				if(tablero[i][j]!=Colores.AGUA)
					cont++;
			}
		return cont;
	}
	
	public void setPosicion(int x, int y, int valor){
		this.tablero[x][y]=valor;
	}
	
	public void setTablero(int[][] tablero){
		for(int i=0; i<tamanio; i++)
			for(int j=0; j<tamanio; j++){
				this.tablero[i][j]=tablero[i][j];
			}
	}
}
