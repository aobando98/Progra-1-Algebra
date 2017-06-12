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

    
    //Operaciones sobre filas
    
    /**
     * Intercambio de una fila por otra
     * @param pMatriz
     * @param pFila
     * @param otra
     * @return La matriz con las filas cambiadas
     */
    public Matriz Intercambia(Matriz pMatriz,int pFila, int otra){
        Matriz nueva= new Matriz(pMatriz.getFilas(),pMatriz.getFilas());
        pFila -= 1;
        otra -= 1;
        Fraccion tmp= new Fraccion(1,1);
        for(int i=0;i<pMatriz.getFilas();i++){
            for(int j=0;j<pMatriz.getColumnas();j++){
                if(i==pFila){
                   tmp=pMatriz.getValor(i, j);
                   nueva.setValor(otra, j, tmp);     
                }else if(i==otra){
                   tmp=pMatriz.getValor(i, j);
                   nueva.setValor(pFila, j, tmp);
                }else{
                    tmp=pMatriz.getValor(i,j);
                    nueva.setValor(i, j, tmp);
                }
            }
        }
        return nueva;
    }
    
    /**
     * Multiplicacion de una fila por un escalar mas otra fila
     * @param pMatriz
     * @param escalar
     * @param pfila
     * @param destino
     * @return
     */
    public Matriz EscFaF(Matriz pMatriz,Fraccion escalar, int pfila, int destino){
        pfila -= 1;
        destino -= 1;
        for(int i=0;i<pMatriz.getFilas(); i++){
            for(int j=0; j<pMatriz.getColumnas(); j++){
                if(pfila==i){
                    Fraccion tmp= new Fraccion(1,1);
                    tmp=tmp.Multiplicacion(pMatriz.getValor(i,j),escalar);
                    tmp=pMatriz.getValor(destino,j);
                    pMatriz.setValor(destino,j,tmp);
                }
            }
        }
        return pMatriz;
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
    
    public Matriz Multiplicacion(Matriz pMatrizA, Matriz pMatrizB){
		Matriz result = null;
		
		if (pMatrizA.getColumnas() != pMatrizB.getFilas()){
			System.out.println("No se puede efectuar la multiplicacion");
			return result;
		}else{
			Matriz Resultado = new Matriz(pMatrizA.getFilas(), pMatrizB.getColumnas());
			for (int i = 0; i < pMatrizA.getFilas(); i++){
				for (int j = 0; j < pMatrizB.getColumnas(); j++){
					Fraccion valor = new Fraccion();
					for (int k =1; k <= pMatrizA.getColumnas(); k++){
						Fraccion temp = new Fraccion();
						temp = temp.Multiplicacion(pMatrizA.getValor(i, k), pMatrizB.getValor(k, i));
						valor = valor.suma(valor, temp);
					}
					Resultado.setValor(i, j, valor);
				}
			}
			result = Resultado;
		}
		
		return result;
	}
	
	public Matriz Resta(Matriz pMatrizA, Matriz pMatrizB){
		
		if (pMatrizA.getFilas() != pMatrizB.getFilas() || pMatrizA.getColumnas() != pMatrizB.getColumnas()){
			return null;
			
		}else{
			Matriz Resultado = new Matriz(pMatrizA.getFilas(), pMatrizA.getColumnas());
			for (int i = 0; i < pMatrizA.getFilas(); i++){
				for (int j = 0; j < pMatrizB.getColumnas(); j++){
					Fraccion valor = new Fraccion();
					valor = valor.resta_in(pMatrizA.getValor(i, j), pMatrizB.getValor(i, j));
					Resultado.setValor(i, j, valor);
				}
			}
			return Resultado;
		}
	}
	
	public Matriz Suma(Matriz pMatrizA, Matriz pMatrizB){
			
			if (pMatrizA.getFilas() != pMatrizB.getFilas() || pMatrizA.getColumnas() != pMatrizB.getColumnas()){
				return null;
				
			}else{
				Matriz Resultado = new Matriz(pMatrizA.getFilas(), pMatrizA.getColumnas());
				for (int i = 0; i < pMatrizA.getFilas(); i++){
					for (int j = 0; j < pMatrizB.getColumnas(); j++){
						Fraccion valor = new Fraccion();
						valor = valor.suma(pMatrizA.getValor(i, j), pMatrizB.getValor(i, j));
						Resultado.setValor(i, j, valor);
					}
				}
				return Resultado;
			}
		}
	
	public Matriz Despejar(Matriz pMatrizA, Matriz pMatrizB, Matriz pMatrizC){
		Matriz MatrizX = new Matriz(pMatrizA.getFilas(), pMatrizA.getColumnas());
		
		Matriz InversaA = pMatrizA.inversa();
		Matriz C_B = pMatrizC.Resta(pMatrizC, pMatrizB);
		
		MatrizX = InversaA.Multiplicacion(InversaA, C_B);
		
		return MatrizX;
	}

	//Funciones Extra Para la Progra de Arqui
	public boolean matrizFila(Matriz pMatriz){
		boolean result = false;

		if (pMatriz.getFilas == 1){
			result = true;
			return result;
		}

		return result;
	}

	public boolean matrizColumna(Matriz pMatriz){
		boolean result = false;

		if (pMatriz.getColumnas == 0){
			result = true;
			return result;
		}

		return result;
	}

	public boolean matrizRectangular(Matriz pMatriz){
		boolean result = false;

		if (pMatriz.getColumnas != pMatriz.getFilas){
			result = true;
			return result;
		}

		return result;
	}

	public boolean matrizCuadrada(Matriz pMatriz){
		boolean result = false;

		if (pMatriz.getColumnas == pMatriz.getFilas){
			result = true;
			return result;
		}
	}

	public boolean matrizNula(Matriz pMatriz){
		boolean result = false

		for (int i = 0; i < pMatrizA.getFilas(); i++){
			for (int j = 0; j < pMatrizB.getColumnas(); j++){
				if (pMatriz.getValor(i, j) != 0){
					return result;
				}
			}
		}
		result = true;
		return result;
	}

	public boolean matrizEscalar(Matriz pMatriz){
		boolean result = false

		int valorDiagonal = pMatriz.getValor(0, 0);
		for (int i = 0; i < pMatrizA.getFilas(); i++){
			for (int j = 0; j < pMatrizB.getColumnas(); j++){
				if (i == j){
					if (pMatriz.getValor(i, j) == valorDiagonal){
						result = true;
					}else{
						result = false;
					}
				}else{
					if (pMatriz.getValor != 0){
						result = false;
						return result;
					}else{
						result = true;
					}
				}
			}
		}
		return result;
	}

	public boolean matrizIdentidad(Matriz pMatriz){
		boolean result = false

		for (int i = 0; i < pMatrizA.getFilas(); i++){
			for (int j = 0; j < pMatrizB.getColumnas(); j++){
				if (i == j){
					if (pMatriz.getValor(i, j) == 1){
						result = true;
					}else{
						result = false;
					}
				}else{
					if (pMatriz.getValor != 0){
						result = false;
						return result;
					}else{
						result = true;
					}
				}
			}
		}
		return result;
	}

	//Revisar
	public boolean matrizTriangularSuperior(Matriz pMatriz){
		boolean result = false;

		for (int i = 0; i < pMatrizA.getFilas(); i++){
			for (int j = 0; j < pMatrizB.getColumnas(); j++){
				if (i > j){
					if (pMatriz.getValor(i, j) == 0){
						result = true;
					}else{
						result = false;
					}
				}else{
					if (pMatriz.getValor != 0){
						result = true;;
						return result;
					}else{
						result = false;
					}
				}
			}
		}
		return result;
	}

	//Revisar
	public boolean matrizTriangularInferior(Matriz pMatriz){
		boolean result = false;

		for (int i = 0; i < pMatrizA.getFilas(); i++){
			for (int j = 0; j < pMatrizB.getColumnas(); j++){
				if (i < j){
					if (pMatriz.getValor(i, j) == 0){
						result = true;
					}else{
						result = false;
					}
				}else{
					if (pMatriz.getValor != 0){
						result = true;;
						return result;
					}else{
						result = false;
					}
				}
			}
		}
		return result;
	}
    	
	

}
