package Clases;

public class Partido {
	
	private int dimension;
	private int validos;
	private int [] codigos;
	//Constructor
	public Partido () {
		
		dimension = 500;
		validos = 0;
		codigos = new int [dimension];
		//jugados = new ArrayList <Equipo>();
	}
	
	/**
	 * verifica si el codigo ingresado esta en el arreglo
	 * @param codigo
	 * @return true si el codigo ya fue generado.
	 */
	public boolean estaEnArreglo (int codigo) {
		
		boolean flag = false;
		
		for (int i = 0; i<dimension; i++) {
			
			if (codigo == codigos[i]) {
				
				flag = true;
			}
		}
		return flag;
	}
	
	/** 
	 * @param equipo
	 * @param equipo2
	 * @return codigo correcto para el partido asignado por dados equipos
	 */
	public int asignarCodigo (Equipo equipo, Equipo equipo2) {
		
		int codigo = equipo.getId() * 2 + equipo2.getId() *25 / 137;
		boolean flag = true;
		
		while (flag == true) {
			
			codigo = codigo+1;
			flag = estaEnArreglo(codigo);
		}
		
		return codigo;
	}
	
	/**
	 * agrega el codigo generado y aumenta el valido+1
	 * @param codigo
	 */
	public void agregarToArreglo (int codigo) {
		
		codigos[validos] = codigo;
		validos = validos +1;
	}
}