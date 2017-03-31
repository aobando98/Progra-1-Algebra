package logica;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fraccion f1 = new Fraccion(2, 5);
		Fraccion f2 = new Fraccion(7, 8);
		
		Fraccion f3 = f1.Multiplicacion(f1, f2);
		
		System.out.println(f3.get_numerador());
		System.out.println(f3.get_denominador());

	}

}
