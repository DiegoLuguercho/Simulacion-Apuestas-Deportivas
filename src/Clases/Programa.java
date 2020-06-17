package Clases;

import java.util.ArrayList;
import java.util.TreeMap;

import Archivo.Archivo;
import Archivo.ArchivoFile;

public class Programa {
	
	private static Programa instancia = new Programa();
	
	private TreeMap <String, Usuario> usuarios;
	private TreeMap <String, Jugador> jugadores;
	private ArrayList <Equipo> equipos;
	private Administrador admin;
	//Constructor
	public Programa()
	{
		if ((admin = Archivo.leerObjeto(ArchivoFile.ADMIN)) == null) {
            admin = Administrador.proveerDefaultAdmin();
        }
		if((usuarios = Archivo.leerObjeto(ArchivoFile.USUARIOS)) == null)
		{
			usuarios = new TreeMap<>();
		}
		if((equipos = Archivo.leerObjeto(ArchivoFile.EQUIPOS)) == null)
		{
			equipos = new ArrayList<>();
		}
		if((jugadores = Archivo.leerObjeto(ArchivoFile.JUGADORES)) == null)
		{
			jugadores = new TreeMap<>();
		}
	}
	//Getters y Setters
	public static Programa getInstancia() {
        return instancia;
    }
	
	public TreeMap<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(TreeMap<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}
	
	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	public TreeMap<String, Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setJugadores(TreeMap<String, Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public Administrador getAdmin() {
		return admin;
	}
	
	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	
	
	/**
	 * Agrega un jugador pasado por parametro al treemap jugadores de Programa
	 * @param j
	 * @return
	 */
	public boolean agregarJugador(Jugador j)
	{
		boolean rta = false;
		if(rta==false)
		{
			jugadores.put(j.getNombreyApellido(), j);
			rta=true;
		}
		return rta;
	}
	
	/**
	 * Agrega un equipo pasado por parametro al arraylist equipos de Programa
	 * @param e
	 */
	public void agregarEquipo(Equipo e)
	{
		equipos.add(e);
	}
	
	/**
	 * Agrega un usuario pasado por parametro al treemap usuario de Programa
	 * @param u
	 */
	public void agregarUsuario(Usuario u)
	{
		usuarios.put(u.getNombreyApellido(), u);
	}

	/**
	 * Devuelve un StringBuilder para mostrar todos los jugadores de un equipo en particular
	 * @param equipo
	 * @return
	 */
	public StringBuilder mostrarJugadores(Equipo equipo) 
	{
		StringBuilder builder =new StringBuilder();
		for(Jugador j : jugadores.values())
		{
			if (equipo.getId() == j.getIdEquipo()){
				
					builder.append((j.toStringCamiseta()));
			}else {
				continue;
			}
		}
		return builder;
	}
	
	 /**
     * Busca un equipo por el id pasado por parametro
     * @param id
     * @return
     */
	public Equipo getEquipoPorId (int id) {
		
		Equipo equipo = null;
		
		for (Equipo e :equipos) {
			
			if (id == e.getId()) {
				
				equipo = e;
			}else {
				
				continue;
			}
		}
		
		return equipo;
	}
	
	/**
	 * Devuelve un StringBuilder para mostrar todos los equipos en el arraylist
	 * @return
	 */
	public StringBuilder mostrarEquipos()
	{
		StringBuilder builder = new StringBuilder ();
		for(Equipo e : equipos)
		{
			builder.append(e.toString());
		}
		return builder;
	}
	
	/**
	 * Agrega todos los jugadores a los equipos, si el id del equipo coincide con el idEquipo del jugador
     * Se agrega a ese equipo llamando el metodo agregarJugador, que esta dentro de la clase equipo
	 */
	public void agregarJugadoresAEquipo()
	{
		for(Equipo e : equipos)
		{
			for(Jugador j : jugadores.values())
			{
				
				if(e.getId()==j.getIdEquipo())
				{
					e.getJugadores().put(j.getNombreyApellido(), j);
				}/*else {
					continue;
				}*/
			}
		}
	}
	
	/**
	 * Agrega un jugador pasado por parametro al equipo
	 * @param j
	 * @return
	 */
	public boolean agregarJugadorParticularAEquipo(Jugador j)
	{
		boolean rta = false;
		for(Equipo e : equipos)
		{
			if(e.getId()==j.getIdEquipo())
			{
				e.agregarJugador(j);
				rta = true;
			}
		}
		return rta;
	}
	
	/**
	 * Comprueba la existencia del usuario
	 * @param key
	 * @return
	 */
	public boolean existeUsuario(String key) {
        return usuarios.containsKey(key);
    }
	/**
	 * Comprueba la existencia del equipo
	 * @param key
	 * @return
	 */
	public boolean existeEquipo(String key) {
        return usuarios.containsKey(key);   
    }
	/**
	 * Busca y devuelve un determinado equipo, si es que coincide su id con el id pasado por parametro
	 * @param id
	 * @return
	 */
	public Equipo encontrarEquipoPorID(int id) {
	    
		Equipo aux = null;
		for(Equipo e : equipos)
		{
			if(e.getId()==id)
			{
				aux=e;
			}
		}
		return aux;
	}
	
	/**
	 * Busca un equipo recorriendo el ArrayList, lo encuentra si el id es igual al id pasado por parametro
	 * Una vez encontrado remueve el equipo del ArrayList
	 * @param id
	 */
	public void removerEquipo(int id) {
	   
		int pos=0;
		for(Equipo e : equipos)
		{
			if(id==pos)
			{
				equipos.remove(pos);
			}
			pos++;
		}
		
	}
	
	/**
	 * Busca un jugador y lo devuelve si es que key coincide con el numero de camiseta del jugador
	 * @param key
	 * @return
	 */
	public Jugador encontrarJugadorPorNombre(int key)
	{
		Jugador aux = null;
		for(Jugador j : jugadores.values())
		{
			if(j.getNumeroCamiseta()==key)
			{
				aux=j;
			}
		}
		return aux;
	}

	/**
	 * Muesta los jugadores que no se encuentran en ningun equipo
	 */
	public void mostrarJugadoresSinEquipo()
	{
		for(Jugador j : jugadores.values())
		{
			if(j.getIdEquipo()==-1)
			{
				System.out.println(j);
			}	
		}
	} 
	
	/**
	 * Muestra el treemap de jugadores
	 */
	public StringBuilder mostrarJugadoresEnPrograma()
	{
		StringBuilder builder = new StringBuilder();
		for(Jugador j : jugadores.values())
		{
			builder.append(j.toString());
		}
		return builder;
	} 
	/**
	 * MUestra el arraylist de equipos
	 */
	public StringBuilder mostrarEquiposEnPrograma()
	{
		StringBuilder builder = new StringBuilder();
		for(Equipo e : equipos)
		{
			builder.append(e.toString());		
		}
		return builder;
	}
	/**
	 * Devuelve un usuario en particular si el string pasado por parametro es igual al 
	 * @param idUsuario
	 * @return
	 */
	public Usuario devuelveUsuario(String idUsuario)
	{
		Usuario usuario=null;
		for(Usuario u:usuarios.values())
		{
			if(u.getUsuario().equals(idUsuario))
			{
				usuario=u;
			}
		}
		return usuario;
		
	}

	public StringBuilder convertioGolesPorId (int idEquipo) {
		
		StringBuilder builder = new StringBuilder();
		Equipo e = getEquipoPorId(idEquipo);
		boolean flag =false;
		
		if (e != null) {
			
			for (Jugador j : e.getJugadores().values()) {
				
				if (j.getAnotacion() > 0) {
					
					builder.append("\nJugador :"+j.getNombreyApellido()+"\nGoles :"+j.getAnotacion());
					flag = true;
				}
			}
		}
		if (flag == false) {
			
			builder.append("false");
		}
		
		return builder;
	}
	
}
