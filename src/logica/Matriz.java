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

    
    //Otras Funciones
    
    public Matriz Elimina(Matriz pMatriz, int pFila, int pColumna){
		Matriz Resultado = new Matriz(pMatriz.getFilas() - 1, pMatriz.getColumnas() - 1);
		int i_actual = 0;
		int j_actual = 0;
		
		for (int i = 0; i < pMatriz.getFilas(); i++){
			if (i != pFila){
				j_actual = 0;
				
				for (int j = 0; j < pMatriz.getColumnas(); j++){
					if (j != pColumna){
						Fraccion valor = new Fraccion();
						valor = pMatriz.getValor(i, j);
						Resultado.setValor(i_actual, j_actual, valor);
						j_actual++;
					}
				}
				i_actual++;
			}
		}
		return Resultado;
	}
    
    public Fraccion determinante(Matriz pMatriz, int pFila){
        Fraccion mult= new Fraccion(-1,1);
        Fraccion cons=new Fraccion(-1,1);
        Fraccion det=new Fraccion(0,1);
        
        while(pMatriz.getFilas()>2){
            for(int j=0;j<pMatriz.getColumnas();j++){
                mult = mult.Multiplicacion(mult,cons);
                det = det.suma(det, det.Multiplicacion(mult, det.Multiplicacion(pMatriz.getValor(pFila,j), determinante(Elimina(pMatriz,pFila,j), pFila))));
            }
            return det;
        }
        return det.resta(det.Multiplicacion(pMatriz.getValor(0,0), pMatriz.getValor(1,1)),det.Multiplicacion(pMatriz.getValor(0,1), pMatriz.getValor(1,0)));
    }
    
    public Matriz cofactores(Matriz pMatriz){
        Matriz Resultado=new Matriz(pMatriz.getFilas(),pMatriz.getColumnas());
        Fraccion cons=new Fraccion(-1,1);
        Fraccion mult= new Fraccion(1,1);
        
        for(int i=0;i<pMatriz.getFilas();i++){
            for(int j=0;j<pMatriz.getColumnas();j++){
            	
                Matriz tmp = new Matriz(pMatriz.getFilas()-1,pMatriz.getColumnas()-1);
                tmp = tmp.Elimina(pMatriz, i, j);
                Fraccion nuevo = new Fraccion(1,1);
                nuevo = nuevo.Multiplicacion(mult, tmp.determinante(tmp, i));
                Resultado.setValor(i, j, nuevo);
                mult = cons.Multiplicacion(mult, cons);
                
            }
        }
        return Resultado;
    }
    
    public void imprime_matriz(){
    	for (int i = 0; i < Filas; i++){
    		for (int j = 0; j < Columnas; j++){
    			Fraccion entrada = this.getValor(i, j);
    			System.out.println("Entrada: " + i + j + "= " + entrada.get_numerador() + "/" + entrada.get_denominador());
    		}
    	}
    }
	
    public Matriz adjunta(){
    	Matriz resultado = this.cofactores(this);
    	resultado = resultado.Transpuesta(resultado);
    	return resultado;
    }
    
    public Matriz Transpuesta(Matriz pMatriz){
		Matriz Resultado = new Matriz(pMatriz.getColumnas(), pMatriz.getFilas());
		for (int i = 0; i < pMatriz.getFilas(); i++){
			for (int j = 0; j < pMatriz.getColumnas(); j++){
				Fraccion valor = new Fraccion();
				valor = pMatriz.getValor(i, j);
				Resultado.setValor(j, i, valor);
			}
		}
		return Resultado;
	}
    
    public Matriz MultiplicarEscalar (Fraccion num){
        Matriz MatrizA = new Matriz(Filas, Columnas);
        for (int f = 0; f< Filas; f++){
            for (int c= 0; c< Columnas; c++){
                arreglo [f][c]=arreglo[f][c].Multiplicacion(arreglo[f][c], num);
            }
        }
        return MatrizA;
    }
    
    public Matriz inversa(){
    	Matriz adjunta = this.adjunta();   //Calcula la adjunta de la matriz
    	Fraccion f1 = this.determinante(this, 0);  //Calcula el determinante de la matriz
    	f1 = f1.inversa();  //Pasa el determinate a su forma inversa = 1/det
    	
    	adjunta.MultiplicarEscalar(f1);   //Multiplica la adjunta por el determinate inverso de la matriz
    	
    	return adjunta;
    }
    	
	

}
