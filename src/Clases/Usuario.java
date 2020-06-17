package Clases;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Usuario extends Persona{
	
	private String usuario; //nombre generico del usuario
	private String ladoApostado;	/// localVisitante
	private float cuenta;
	private float cantidadApostado;
	private static final AtomicInteger count = new AtomicInteger(); 
	private final int numeroAfiliado;
	private Password password;
	private int codigoPartido;
	private int idEquipo;
	//Constructor
	public Usuario(String usuario, String nombreyApellido, String nacionalidad, String fechaNacimiento, Password password) {
		super(nombreyApellido, nacionalidad, fechaNacimiento);
		this.usuario = usuario;
		cuenta = 0;
		cantidadApostado = 0;
		this.password = password;
		codigoPartido=0;
		idEquipo = 0;
		numeroAfiliado = count.incrementAndGet();	
	}
	//toString
	@Override
	public String toString() {
		return "Usuario [Usuario=" + usuario + ", ladoApostado=" + ladoApostado + ", cuenta=" + cuenta
				+ ", cantidadApostado=" + cantidadApostado + ", numeroAfiliado=" + numeroAfiliado + ", contraseña="
				+ getPassword() + "[toString()=" + super.toString() + "]";
	}

	
    //Getters y Setters
	public float getCantidadApostado() {
		return cantidadApostado;
	}

	public void setCantidadApostado(float cantidadApostado) {
		this.cantidadApostado = cantidadApostado;
	}

	public String getLadoApostado() {
		return ladoApostado;
	}
	public void setLadoApostado(String ladoApostado) {
		this.ladoApostado = ladoApostado;
	}
	public int getCodigoPartido() {
		return codigoPartido;
	}
	public void setCodigoPartido(int codigoPartido) {
		this.codigoPartido = codigoPartido;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	public String getPassword() {
        return password.getClave();
    }
	
	public float getCuenta() {
		return cuenta;
	}

	public void setCuenta(float cuenta) {
		this.cuenta = cuenta;
	}
	
	public int getNumAfiliado() {
		
		return numeroAfiliado;
	}
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * Se suma a cuenta el float pasado por parametro
	 * @param dinero
	 * @return
	 */
    public boolean agregarDinero (float dinero) {
		
		boolean flag = false;
		float verifica = cuenta;
		cuenta = cuenta + dinero;
		
		if (verifica != cuenta)
			flag = true;
		
		return flag;
	}
	/**
	 * Pasa por parametro un float, este float descontara a cuenta
	 * @param dinero
	 */
	public void descontarDinero (float dinero) {
		
		cuenta = cuenta - dinero;
	}
	/**
	 * Si cantidad es menor o igual a cuenta llama a descontarDinero y por ende lo resta
	 * @param cantidad
	 * @return
	 */
	public boolean apostar (float cantidad) {
		
		boolean flag = false;
		
		if (cantidad <= cuenta) {
			cantidadApostado = cantidad;
			flag = true;
		}
		
		return flag;
	}
    
	/**
	 * Si el boolean de equipo gano es igual a true devuelve un true
	 * @param equipo
	 * @return
	 */
	public boolean gano (Equipo equipo) {
		
		boolean flag = false;
		
		if (equipo.isGano() == true) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * Se hace un analisis de los 2 equipos que juegan y el que tiene mas apuestas es el que menos ganancia dara
	 * @param equipo
	 * @param equipo1
	 * @return
	 */
    public float gananciaDeApuestaPorCantidadDeUsuarios (Equipo equipo, Equipo equipo1) {	
		
		float ganancia = 0;
		
		if (idEquipo == equipo.getId()) {
			
			if (equipo.getCantidadApuestas() > equipo1.getCantidadApuestas())
				
				ganancia = (float) (cantidadApostado * 2.35); 
			else 
				ganancia = (float) (cantidadApostado * 3.28);
			
		}else {
				
			if (equipo.getCantidadApuestas() > equipo1.getCantidadApuestas())
				
				ganancia = (float) (cantidadApostado * 2.35); 
			else 
				ganancia = (float) (cantidadApostado * 3.28);
		}
		
		return ganancia;
	}
	
	/**
	 * devuelve un string con un fin de ver lo apostado
	 * @return
	 */
	public String verApuesta () {
		
		String mensaje = "\nAPUESTA\n"+"Cantidad apostado :" +cantidadApostado+ "\nCodigo del partido :" +codigoPartido+ "\nCuenta ahora :" +cuenta;
		return mensaje;
	}
	/**
	 * Se hace un analisis de los 2 equipos que juegan y el que tiene mas apuestas es el que menos ganancia dara
	 * @param equipo
	 * @param equipo1
	 * @return
	 */
    public boolean gananciaDeApuestaPorCantidadDeUsuarios (int idEquipo1, int idEquipo2, Programa programa) {	
		
    	boolean flag = false;
    	Equipo equipo1 = programa.getEquipoPorId(idEquipo1);
    	Equipo equipo2 = programa.getEquipoPorId(idEquipo2);
    	
		float ganancia = 0;
		
		if (getIdEquipo() == equipo1.getId()) {
			
			if (equipo1.isGano() == true) {
				
				flag = true;
				if (equipo1.getCantidadApuestas() > equipo2.getCantidadApuestas())
				
					ganancia = (float) (cantidadApostado * 2.35); 
				else 
					ganancia = (float) (cantidadApostado * 3.28);
			}
			
		}else if (getIdEquipo() == equipo2.getId()){
			
			if (equipo2.isGano() == true) {
				
				flag = true;
				if (equipo2.getCantidadApuestas() > equipo1.getCantidadApuestas())
					
					ganancia = (float) (cantidadApostado * 2.35); 
				else 
					ganancia = (float) (cantidadApostado * 3.28);
			}
		}
		if (ganancia != 0)
			cuenta = cuenta + ganancia;
		return flag;
	}
	
public float gananciaPorCantGoles (int cantGoles, int cantApostado) {
    	
    	float ganancia = 0;
    	
    	if (cantGoles == 1) {
    		
    		ganancia = (float) (cantidadApostado * 2);
    	}
    	
		if (cantGoles == 2) {
		    		
		    ganancia = (float) (cantidadApostado * 3);
		}
		
		if (cantGoles == 3) {
			
			ganancia = (float) (cantidadApostado * 4.2);
		}
		
		if (cantGoles >= 4) {
			
			ganancia = (float) (cantidadApostado * 5.9);
		}
    	
    	return ganancia;
    }
    
	public boolean gananciaDeApuestasPorGolDeJugadores (int numCamiseta, int idEquipo, int golesApostados, Programa programa) {
		
		boolean flag = false;
		int goles = 0;
		
		Equipo e = programa.getEquipoPorId(idEquipo);
		TreeMap <String, Jugador> jugadores;
		
		if (e != null) {
			
			jugadores = e.getJugadores();
			
			for (Jugador j : jugadores.values()) {
				
				if (j.getNumeroCamiseta() == numCamiseta) {
					
					goles = j.getAnotacion();
				}
			}
		}
		
		if(goles>0) {
			
			flag = true;
		}
		cuenta = cuenta + gananciaPorCantGoles(goles, golesApostados);
		
		return flag;
	}
}