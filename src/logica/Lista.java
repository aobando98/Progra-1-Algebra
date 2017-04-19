package logica;

public class Lista<T> {
	
	private Nodo<T> Inicio;
	private int Cantidad;
	private Nodo<T> Final;
	
	public Lista()
	{
		Inicio = null;
		Final = null;
		Cantidad = 0;
	}
	
	public boolean vacia()
	{
		return Cantidad==0;
	}
	
	public int longitud()
	{
		return Cantidad;
	}
	
	private void agregar(Nodo<T> pNodo)
	{
		if (vacia()) 
		{
			Inicio = pNodo;
			Final = pNodo;
			Cantidad++;
		} else
		{
			pNodo.setSiguiente(Inicio);
			Inicio = pNodo;
			Cantidad++;
		}
	}

	/*
	 * Agrega un nodo al inicio de la lista
	 */
	private void agregarCool(Nodo<T> pNodo)
	{
		if (!vacia()) 
		{
			pNodo.setSiguiente(Inicio);
		} else 
		{
			Final = pNodo;
		}
		Inicio = pNodo;
		Cantidad++;
	}
	
	/*
	 * Agrega un nodo al final de la lista
	 */
	private void agregarAlFinal(Nodo<T> pNodo)
	{
		if (!vacia())
		{
			Final.setSiguiente(pNodo);
			Final=pNodo;
			Cantidad++;
		} else 
		{
			agregarCool(pNodo);
		}
	}
	
	public void agregar(Nodo<T> pNodo, PosicionInsercion pPos)
	{
		if (pPos==PosicionInsercion.FINAL) 
		{
			agregarAlFinal(pNodo);
		} else
		{
			agregarCool(pNodo);
		}
	}
	
	public int insertar(Nodo<T> pNodo, int pPosition)
	{
		int result = -1;
		int casilla = 0;
		
		Nodo<T> recorrido = Inicio;
		if (!(vacia() || pPosition==0)) 
		{
			while (recorrido.getSiguiente()!=null && casilla+1<pPosition) 
			{
				casilla++;
				recorrido = recorrido.getSiguiente();
			}
			pNodo.setSiguiente(recorrido.getSiguiente());
			recorrido.setSiguiente(pNodo);
			result = casilla+1;
		} else 
		{
			result = 0;
			pNodo.setSiguiente(Inicio);
			Inicio = pNodo;
		}
		
		
		return result;
	}

}
