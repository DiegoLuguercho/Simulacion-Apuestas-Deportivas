package Clases;

import java.io.Serializable;
import java.util.TreeMap;

public class Equipo implements Serializable{
	
	///id, nombreDelEquipo, nombredelequipo, cantidaddepuntos, cantidadapuestas, cantidad goles, codigo partido, treemap jugadores
	private boolean eliminado;	// si pierde se pasa a eliminado //
	private boolean elegido;
	private int id;
	private String nombreDelEquipo;
	private int cantidadPuntos; // puntos en liga
	private int cantidadApuestas; // cuanto mas gente apuesta menos valor da la ganancia
	public int cantidadGoles; //siempre esta en 0, se juega la fecha cambia y vuelve a 0 para la siguiente fecha
	private int codigoPartido;
	private TreeMap<String, Jugador> jugadores;	
	private boolean gano;
	
	
	@Override
	public String toString() {
		
		return "\n[Equipo]"+"\n Id: " + id + "\n Nombre del Equipo: " + nombreDelEquipo + "\n CantidadPuntos: " + cantidadPuntos
				+ "\nCantidad de apuestas: " + cantidadApuestas + "\n Cantidad de Goles: " + cantidadGoles + "\nElegido: " + elegido;
	}

	public Equipo (int id, String nombreDelEquipo) 
	{
		setElegido(false);
		setEliminado(false);
		this.setId(id);
		this.setNombreDelEquipo(nombreDelEquipo);
		setCodigoPartido(0);
		cantidadPuntos = 0;
		setCantidadApuestas(0);
		cantidadGoles = 0;
		setJugadores(new TreeMap<>());
		setGano(false);
	}
	
	public int getCantidadPuntos() {
		return cantidadPuntos;
	}
	
	public void setCantidadPuntos(int cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}

	public int getCantidadApuestas() {
		return cantidadApuestas;
	}

	public void setCantidadApuestas(int cantidadApuestas) {
		this.cantidadApuestas = cantidadApuestas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreDelEquipo() {
		return nombreDelEquipo;
	}

	public void setNombreDelEquipo(String nombreDelEquipo) {
		this.nombreDelEquipo = nombreDelEquipo;
	}
	public boolean equals (Object obj) {
		
		if (obj != null) {
			
			if (obj instanceof Equipo) {
				
				Equipo aux = (Equipo) obj;
				
				if (aux.getNombreDelEquipo().equals(getNombreDelEquipo()))
				{
					
					System.out.println(((Equipo) obj).getNombreDelEquipo());
					return true;
					
				}
				else
				{
					return false;
				}
				
			}else
			{
				return false;
			}
			
		}else
		{
			return false;
		}
	}
	
	public StringBuilder mostrarJugadores() 
	{
		StringBuilder builder =new StringBuilder();
		for(Jugador j : jugadores.values())
		{
			builder.append(j.toString());
		}
		return builder;
	}
	
	public void setJugadores(TreeMap<String, Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void agregarJugador(Jugador j)
	{
		jugadores.put(nombreDelEquipo, j);
	}

	public boolean isElegido() {
		return elegido;
	}

	public void setElegido(boolean elegido) {
		this.elegido = elegido;
	}
	
	public int getCodigoPartido() {
		return codigoPartido;
	}

	public void setCodigoPartido(int codigoPartido) {
		this.codigoPartido = codigoPartido;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	
	public void setCantidadGoles(int cantidadGoles) {
		this.cantidadGoles = cantidadGoles;
	}
	
	public int getCantidadGoles() {
		return cantidadGoles;
	}
	
	public void jugarPartido() {
		
		for (Jugador j : jugadores.values()) {
			
			j.probabilidadDeAmonestacion();
			j.probabilidadDeExpulsion();
			j.cantidadDeGoles();
		}
	}
	
	public StringBuilder verActividadEnPartido () {
		
		StringBuilder builder = new StringBuilder ();
		for (Jugador j : jugadores.values()) {
			
			if (j.getAnotacion() > 0) {
				
				builder.append("\n"+j.getNombreyApellido()+" : anotacion/es : "+j.getAnotacion());
			}
			
			if (j.isExpulsado()==true) {
				
				builder.append("\n"+j.getNombreyApellido()+" : Expulsado");
				
			}else if (j.isAmonestado()==true && !j.isExpulsado()) {
				
				builder.append("\n"+j.getNombreyApellido()+" : Amonestado");
			}
		}
		return builder;
	}
	
	public boolean estaJugador (int numCamiseta) {
		
		boolean flag = false;
		
		for (Jugador j : jugadores.values()) {
			
			if (j.getNumeroCamiseta() == numCamiseta) {
				
				flag = true;
			}
		}
		return flag;
	}
	
	/*public Jugador retornaJugadorPorNumCamiseta (int numCamiseta) {
		
		boolean flag = estaJugador(numCamiseta);
		Jugador jug = null;
		
		if (flag == true) {
			
			for (Jugador j : jugadores.values()) {
				
				if (j.getNumeroCamiseta() == numCamiseta) {
					
					jug = j;
				}
			}
		}
		return jug;
	}*/
	
    public void golesDelPartido () {
    	
		int goles = 0;
		for (Jugador j : jugadores.values()) 
		{			
			goles = goles +j.getAnotacion();
		}
		if (goles != 0)
		{
			setCantidadGoles(goles);
		}	
	}

	public boolean isGano() {
		return gano;
	}

	public void setGano(boolean gano) {
		this.gano = gano;
	}
	public TreeMap<String, Jugador> getJugadores()
	{
		return jugadores;
	}
}