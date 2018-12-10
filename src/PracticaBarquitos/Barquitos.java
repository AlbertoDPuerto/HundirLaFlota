package PracticaBarquitos;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Barquitos extends java.awt.Frame implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int [] numBarcosLong;
	private Tablero tablero;

	public static void main (java.lang.String[] a) {
		
		int [] numBarcosLong = {3,2,1};
		Barquitos barquito = new Barquitos(numBarcosLong,10);
		Tablero cubierto = new Tablero(barquito.getTamanioTablero(),true);
		Scanner in = new Scanner (System.in);
		int x = 0, y = 0;
		barquito.colocarBarcos();
		do {
			for(int i=0; i < cubierto.getTamanio(); i++) {
				for(int p=0; p < cubierto.getTamanio(); p++) 
					System.out.print(cubierto.getPosicion(i, p) + "  ");
				System.out.println("\n");
			}
			System.out.println("COORDENADAS PARA EL DISPARO CAPITÁN");
			System.out.println("X: ");
			x = in.nextInt();
			System.out.println("Y: ");
			y = in.nextInt();
			barquito.disparo(x, y);
			cubierto.setPosicion(x, y, barquito.tablero.getPosicion(x, y));
		} while(!barquito.finPartida());
		barquito.mostrarFinPartida();
		System.out.println("YOU WIN!");
	}
	
	public Barquitos(int[] numBarcosLong, int tamanioTablero){
		this.numBarcosLong=numBarcosLong;
		tablero = new Tablero(tamanioTablero, false);
	}

	public void colocarBarcos(){
		int barco = 4;
		Random r1 = new Random();
		int posx = 0, posy =0, horizontal = 0;
		for(int y = 0; y < numBarcosLong.length;y++) 
			for(int x = 0; x < numBarcosLong[y];x++,barco++) {
				do{
					posx = r1.nextInt(tablero.getTamanio());
					posy = r1.nextInt(tablero.getTamanio());
					horizontal = r1.nextInt(2);
				} while(!entraBarco(y+1,posx,posy,barco,horizontal));
				if(horizontal == 0 && posx!=0)
					tablero.setPosicion(posx-1, posy, -1);
				else if(horizontal == 1 && posy!=0)
					tablero.setPosicion(posx, posy-1, -1);
			}
		for(int i=0; i<getTamanioTablero(); i++)
			for(int j=0; j<getTamanioTablero(); j++){
				if(tablero.getPosicion(i, j)== -1)
					tablero.setPosicion(i, j, Colores.AGUA);
			}
	}
	
	private boolean entraBarco (int tamanioBarco, int posx, int posy, int barco, int horizontal) {
		if(posx < tablero.getTamanio() && posy < tablero.getTamanio()) {
			if(tamanioBarco == 0) {
				if(horizontal == 0 && posx != this.getTamanioTablero() -1)
					tablero.setPosicion(posx, posy, -1);
				else if(horizontal == 1 && posy!= this.getTamanioTablero() -1)
					tablero.setPosicion(posx, posy, -1);
				return true;
			}
			else if(tablero.getPosicion(posx, posy) == Colores.AGUA) {
				if(horizontal == 0) {
					if(entraBarco(tamanioBarco-1,posx+1,posy,barco,horizontal)) {
						tablero.setPosicion(posx, posy, barco);
						if(posy!= this.getTamanioTablero()-1)
							tablero.setPosicion(posx, posy+1, -1);
						if(posy!= 0)
							tablero.setPosicion(posx, posy-1, -1);
						return true;
					} else return false;
				} else {
					if(entraBarco(tamanioBarco-1,posx,posy+1,barco,horizontal)) {
						tablero.setPosicion(posx, posy, barco);
						if(posx!= this.getTamanioTablero()-1)
							tablero.setPosicion(posx+1, posy, -1);
						if(posx!= 0)
							tablero.setPosicion(posx-1, posy, -1);
						return true;
					} else return false;
				} 
			} else return false;
			
		} else return false;
	}
	
	void disparo (int x, int y) {
		if(tablero.getPosicion(x, y) == 1) 
			System.out.println("AGUA");
		else if(tablero.getPosicion(x, y) > 3){
			if(hundido(x,y)) {
				System.out.println("TOCADO Y HUNDIDO");
				hundir(x,y); 
			} else {
				System.out.println("TOCADO");
				tocar(x,y);
			}
		}
		
	}
	
	private boolean hundido(int x, int y) {
		int coincidenciax = 0, coincidenciay = 0;
		for(int i=0; i < tablero.getTamanio(); i++) {
			if(tablero.getPosicion(x, y) == tablero.getPosicion(i, y))
				coincidenciax++;
		}
		if (coincidenciax==1) {
			for(int p=0; p < tablero.getTamanio(); p++) {
				if(tablero.getPosicion(x, y) == tablero.getPosicion(x, p))
					coincidenciay++;
			}
		}
		if(coincidenciax == 1 && coincidenciay == 1)
			return true;
		else return false;
	}
	
	private void tocar(int x, int y) {
		tablero.setPosicion(x, y, Colores.TOCADO);
	}
	
	private void hundir(int x, int y) {
		tablero.setPosicion(x, y, Colores.HUNDIDO);
				
		if(y!= this.getTamanioTablero()-1) {			
			if(tablero.getPosicion(x, y+1) == 2) 
				hundir(x,y+1);
		}
		if(y!= 0) {
			if(tablero.getPosicion(x, y-1) == 2) 
				hundir(x,y-1);
		}
		if(x!= this.getTamanioTablero()-1) {
			if(tablero.getPosicion(x+1, y) == 2) 
				hundir(x+1,y);
		}
		if(x!= 0) {
			if(tablero.getPosicion(x-1, y) == 2) 
				hundir(x-1,y);
		}
	}
	
	public boolean finPartida() {
		for(int i=0; i < tablero.getTamanio(); i++) { 
			for(int p=0; p < tablero.getTamanio(); p++) {
				if(tablero.getPosicion(i, p) > 3)
					return false;
			}
		}
		return true;
	}

	public int getTamanioTablero() {
		return tablero.getTamanio();
	}
	
	public void mostrarFinPartida() {
		for(int i=0; i < getTamanioTablero(); i++) {
			for(int p=0; p < getTamanioTablero(); p++) 
				System.out.print(tablero.getPosicion(i, p) + " ");
			System.out.println("\n");
		}
	}

}
