package logica;

public class Logica {
	
	/**
	 * El objetivo de la funcion es cambiar la fila de una matriz por otra fila de la misma
	 * @param pMatriz = matriz por cambiar
	 * @return La matriz con las filas cambiadas
	 */
	public Matriz cambia_filas(Matriz pMatriz, int pfila_1, int pfila_2){
		Matriz result = null;
		
		
		
		return result;
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
	
	public Fraccion determinante(Matriz pMatriz){
        Fraccion mult= new Fraccion(-1,1);
        Fraccion cons=new Fraccion(-1,1);
        Fraccion det=new Fraccion(0,1);
        while(pMatriz.getFilas()>2){
            for(int i=0;i<pMatriz.getFilas();i++){
                mult=mult.Multiplicacion(mult,cons);
                det=det.suma(det, det.Multiplicacion(mult, det.Multiplicacion(pMatriz.getValor(i,0),determinante(Elimina(pMatriz,i,0)))));
            }
            return det;
        }
        return det.resta(det.Multiplicacion(pMatriz.getValor(0,0), pMatriz.getValor(1,1)),det.Multiplicacion(pMatriz.getValor(0,1), pMatriz.getValor(1,0)));
    }

}
