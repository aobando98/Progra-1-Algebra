package logica;

public class Fraccion {
	
	private int numerador;
	private int denominador;
	
	//Constructor
	
	public Fraccion(int pNumerador, int pDenominador){
		this.numerador = pNumerador;
		this.denominador = pDenominador;
	}
	
	public Fraccion(){
		this.numerador = 0;
		this.denominador = 1;
	}
	
	//Setters y Getters basicos
	
	public int get_numerador() {
        return numerador;
    }

    public void set_numerador(int num) {
        numerador = num;
    }
    
    public int get_denominador(){
    	return denominador;
    }
    
    public void set_denominador(int num){
    	denominador = num;
    }
	
	//Operaciones Basicas
	
	//Minimo comun multiplo
    
    public int MCM(int num1, int num2){
    	if (num1 % num2 == 0 || num2 % num1 == 0){
    		if (num1 > num2){
    			return num1;
    		}else{
    			return num2;
    		}
    	}else{
    		return num1 * num2;
    	}
    }
    
    //Suma
    
    public Fraccion suma(Fraccion A, Fraccion B){
        int dA=A.get_denominador();
        int dB=B.get_denominador();
        int nA=A.get_numerador();
        int nB=B.get_numerador();
        Fraccion F = new Fraccion();
        int N=(nA*dB)+(nB*dA);
        int D=dA*dB;
        F.set_denominador(D);
        F.set_numerador(N);
        F.reduce();
        return F;    
    }
    
    //Resta
    
    public Fraccion resta_in(Fraccion A, Fraccion B){
        int dA=A.get_denominador();
        int dB=B.get_denominador();
        int nA=A.get_numerador();
        int nB=B.get_numerador();
        Fraccion F = new Fraccion();
        int N=(nA*dB)-(nB*dA);
        int D=dA*dB;
        F.set_denominador(D);
        F.set_numerador(N);
        F.reduce();
        return F;    
    }
    
    Fraccion resta(Fraccion A, Fraccion B){
    	int dA=A.get_denominador();
        int dB=B.get_denominador();
        int nA=A.get_numerador();
        int nB=B.get_numerador();
        Fraccion F = new Fraccion();
        int N=(nA*dB)-(nB*dA);
        int D=dA*dB;
        F.set_denominador(D);
        F.set_numerador(N);
        F.reduce();
        return F; 
    }
    
    //Multiplicacion
    
    public Fraccion Multiplicacion(Fraccion A, Fraccion B){
    	int dA=A.get_denominador();
        int dB=B.get_denominador();
        int nA=A.get_numerador();
        int nB=B.get_numerador();
        Fraccion F = new Fraccion();
        int N= nA * nB;
        int D= dA * dB;
        F.set_denominador(D);
        F.set_numerador(N);
        F.reduce();
        return F; 
    }
    
    //Simplificacion
    
    private void reduce() {
        int n = numerador, d = denominador, largest;
        if (numerador < 0) {
            n = -numerador;
        }
        if (n > d) {
            largest = n;
        }
        else {
            largest = d;
        }

        int MCD = 0;
        for (int i = largest; i >= 2; i--) {
            if (numerador % i == 0 && denominador % i == 0) {
                MCD = i;
                break;
            }
        }

        if (MCD != 0) {
            numerador /= MCD;
            denominador /= MCD;
        }
    }

}
