package logica;

public class Matriz {
	
	private int Filas;
	private int Columnas;
	private Fraccion[][] arreglo;

	
	//Constructor
	
	public Matriz(int pFilas, int pColumnas){
		this.Columnas = pColumnas;
		this.Filas = pFilas;
		this.arreglo = new Fraccion[pFilas][pColumnas];
	}
	
	//Setters 
	
	public void setFilas(int pFilas){
		this.Filas = pFilas;
	}
	
	public void setColumnas(int pColumnas){
		this.Columnas = pColumnas;
	}
	
	public void setArreglo(Fraccion[][] pArreglo){
		this.arreglo = pArreglo;
	}
	
	//Getters
	
	public int getFilas(){
		return this.Filas;
	}
	
	public int getColumnas(){
		return this.Columnas;
	}
	
	public Fraccion[][] getArreglo(){
		return this.arreglo;
	}
	
	//Otras Funciones
	
	//Get y Set PRINCIPAL
    public Fraccion getValor(int f, int c) {
        return arreglo[f][c];
    }

    public void setValor(int f, int c, Fraccion num) {
        arreglo[f][c] = num;
    }

    public Matriz MultiplicarEscalar (double num){
        Matriz MatrizA = new Matriz(Filas, Columnas);
        for (int f = 0; f< Filas; f++){
            for (int c= 0; c< Columnas; c++){
                //arreglo [f][c]*=num;
            }
        }
        return MatrizA;
    }
	
	

}
