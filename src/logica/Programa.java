package logica;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fraccion f1 = new Fraccion(2, 5);
		Fraccion f2 = new Fraccion(7, 8);
		Fraccion f3 = new Fraccion(5, 7);
		Fraccion f4 = new Fraccion(9, 4);
		
		
		Fraccion f5 = f1.Multiplicacion(f1, f2);
		
		Matriz m1 = new Matriz(3, 3);
		m1.setValor(0, 0, f1);
		m1.setValor(0, 1, f2);
		m1.setValor(0, 2, f1);
		m1.setValor(1, 0, f3);
		m1.setValor(1, 1, f4);
		m1.setValor(1, 2, f1);
		m1.setValor(2, 0, f1);
		m1.setValor(2, 1, f1);
		m1.setValor(2, 2, f2);
		
		
		Logica l1 = new Logica(m1);
		
		f5 = l1.determinante(m1);
		
		//m1.MultiplicarEscalar(f1);
		System.out.println(m1.determinante(m1, 0).get_numerador() + "/" + m1.determinante(m1, 0).get_denominador());
		m1 = m1.inversa();
		
		m1.imprime_matriz();
		
		
		System.out.println(f5.get_numerador());
		System.out.println(f5.get_denominador());

	}

}
