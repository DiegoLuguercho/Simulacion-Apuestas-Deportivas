package Clases;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import org.omg.CORBA.portable.IndirectionException;
	
public class Torneo implements Serializable{
		
		Partido partido;
		private String nombreTorneo;
		private int numeroEquipos;
		private ArrayList <Equipo> equipos;
		//Constructor
		public Torneo(String nombreTorneo, int numeroEquipos, ArrayList<Equipo> equipos) 
		{
			super();
			partido = new Partido ();
			this.setNombreTorneo(nombreTorneo);
			this.numeroEquipos = numeroEquipos;
			this.equipos = equipos;
		}
		//Getters y Setters
		public ArrayList <Equipo> getEquipos() {
			return equipos;
		}

		public void setEquipos(ArrayList <Equipo> equipos) {
			this.equipos = equipos;
		}
		
		public int getNumeroEquipos() {
			return numeroEquipos;
		}

		public void setNumeroEquipos(int numeroEquipos) {
			this.numeroEquipos = numeroEquipos;
		}	
		
        public String getNombreTorneo() {
			return nombreTorneo;
		}

		public void setNombreTorneo(String nombreTorneo) {
			this.nombreTorneo = nombreTorneo;
		}
		
		/**
		 * Retorna posicion del equipo autogenerado por id
		 * @return pos
		 */
        public int retornaPosAleatoriaPorId () {
			
			int i = 0;
			int pos = 0;
			Random rd = new Random();
			int num = rd.nextInt(16) ; 
			
			for (Equipo e : equipos) {
				
				if (e.getId() == num) {
						
					pos = i;
				}
			i++;
			}
			return pos;
		}

        /**
         * Genera todos los partidos y los devuelve con un StringBuilder, para que luego los pueda ver el usuario
         * @return
         */
        public StringBuilder verFecha () {
			
			StringBuilder builder = new StringBuilder ();
			Equipo e = null;
			Equipo e2 = null;
			int codigo = 0;
			int j = 0;
			
			while (j != numeroEquipos/2) {	
				
				int pos = retornaPosAleatoriaPorId();
				e = equipos.get(pos);
				int pos2 = retornaPosAleatoriaPorId();
				e2 = equipos.get(pos2);
				
				if (pos != pos2 && !e.equals(e2) && e != null && e2 != null) { 
					
						if (e.getCodigoPartido() == 0 && e2.getCodigoPartido() == 0) {
							
							codigo = partido.asignarCodigo(e, e2);
							partido.agregarToArreglo(codigo);
			
							e.setCodigoPartido(codigo);
							e2.setCodigoPartido(codigo);
							
							e.setElegido(true);
							e2.setElegido(true);
								
							builder.append(e.getNombreDelEquipo().concat("  - VS -  ").concat(e2.getNombreDelEquipo().concat("\n                                                        "
							+ "CODIGO: "+e.getCodigoPartido()).concat("\n\n\n")));
							j++;
						}
				}
			}
			return builder;
		}
        
        /**
         * Mediante un codigo pasado por id, se selecciona un partido
         * @param codigoPartido
         * @return
         */
        public String verID (int codigoPartido) {
			
			StringBuilder builder = new StringBuilder ();
			
			Equipo e1=null;
			boolean rta=false;
			for (Equipo e : equipos) 
			{
				if (e.getCodigoPartido() == codigoPartido) 
				{		
					e.jugarPartido();
					e.golesDelPartido();
					e1 = e;
					rta = true;
				}
			}
			if(rta==true)
			{
                  for (Equipo f : equipos)
                  {
					
					if(!e1.equals(f)) 
					{
						if(f.getCodigoPartido() == codigoPartido) 
						{
							
							f.jugarPartido();
							f.golesDelPartido();
							//f.getCantidadGoles();
							
							builder.append("\n"+"ID :"+ f.getId()+"  "+f.getNombreDelEquipo()+"  - VS -  "+e1.getNombreDelEquipo()+"  ID :"+""+e1.getId()+"\n");
						}
					}
					
                  }
			}
			else
			{
					builder.append("no");
			}
			return builder.toString();
		}
        
        /**
         * Simula toda la fecha, juegan los equipos y devuelve los equipos con sus goles
         * Esta funcion tambien modifica los jugadores, si es que hacen gol, son amonestados o los expulsan
         * @param codigoPartido
         * @return
         */
		public StringBuilder simularFecha (int codigoPartido) {
			
			StringBuilder builder = new StringBuilder ();
			Equipo e1=null;

			for (Equipo e : equipos) {
				
				if (e.getCodigoPartido() == codigoPartido) {
					
					e.jugarPartido();
					e.golesDelPartido();
					e1 = e;
				}
			}
				for (Equipo f : equipos) {
					
					if(!e1.equals(f) && f.getCodigoPartido() == codigoPartido) {
						
						f.jugarPartido();
						f.golesDelPartido();
						
						
						builder.append("\n"+""+ f.getCantidadGoles()+"  "+f.getNombreDelEquipo()+"  - VS -  "+e1.getNombreDelEquipo()+"  "+e1.getCantidadGoles()+"\n");
						builder.append(""+f.verActividadEnPartido()+"\n"+e1.verActividadEnPartido());
					}
				}
			    return builder;
		}
		
        public void simularFecha2 () {

			for (Equipo e : equipos) 
			{
				e.jugarPartido();
				e.golesDelPartido();
			}
		}
        
        public String verFechaConResultados() {
        	
        	StringBuilder builder = new StringBuilder ();
        	
            for (Equipo e : equipos){
            	
				for(Equipo f : equipos){
					
					if ((e.getCodigoPartido() == f.getCodigoPartido() && !e.getNombreDelEquipo().equals(f.getNombreDelEquipo()))) {
						
						builder.append("\n"+""+ f.getCantidadGoles()+"  "+f.getNombreDelEquipo()+"  - VS -  "+e.getNombreDelEquipo()+"  "+e.getCantidadGoles()+"\n");
						builder.append("\n"+f.getNombreDelEquipo()+": Actividad Jugadores: \n");
			            builder.append(""+f.verActividadEnPartido());
			            builder.append("\n--\n");
			            builder.append("\n"+e.getNombreDelEquipo()+": Actividad Jugadores: \n");
			            builder.append(""+e.verActividadEnPartido());	
			            builder.append("\n----------------------\n");
					}
					
				}
				
			}
            
        	return builder.toString();
        }
		
        
		/**
		 * Se pasa por parametro un id, busca el equipo con ese id
		 * Se carga el builder con todos los jugadores de ese equipo, para mostras su nombre y numero de camiseta 
		 * @param idEquipo
		 * @return
		 */
        /*
        public StringBuilder verCamisetaJugadores (int idEquipo) {
			
			StringBuilder builder = new StringBuilder ();
			int i = 0;
			Equipo e1 = null;
			
			for (Equipo e : equipos) {
				
				e1 = e;
				if (idEquipo == e.getId()) {
					
					e1.setJugadores(e.getJugadores());
				}/*else {
					
					continue;
				}
			}
			
			for (Jugador j : e1.getJugadores().values()) {
				
				System.out.println(i);
				builder.append(j.toString());
				i++;
			}
			return builder;
        }*/
        
        public boolean equipoApostado(int id, Equipo e2, Equipo e1)
        {
        	boolean rta=false;
        	if(id==e1.getId())
        	{
        		if(e1.getCantidadGoles()>e2.getCantidadGoles())
        		{
        			rta=true;
        		}
        	}
        	else if(id==e2.getId())
        	{
        		if(e2.getCantidadGoles()>e1.getCantidadGoles())
        		{
        			rta=true;
        		}
        	}
        	return rta;
        }
       
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
       
        public int idPartidoPorCodigo (int codigo, int id) {
			
			int idE2 = 0;
			for (Equipo e : equipos) {
				
				if (e.getCodigoPartido() == codigo && e.getId() != id) {
					
					idE2 = e.getId();
				}
			}
			return idE2;
		}
        
        public boolean existePartido (int id) {
        	
        	boolean flag = false;
        	Equipo e = getEquipoPorId(id);
        	
        	if (e != null) {
        		
        		flag = true;
        	}
        	return flag;
        }
        
		public void agregarJugadoresDePrograma(Programa programa) {
			
			for (Equipo e : programa.getEquipos()) {
				
				for(Jugador j : programa.getJugadores().values()) {
					
					if (e.getId() == j.getIdEquipo()) {
						
						//System.out.println(e.getJugadores().toString());
						e.agregarJugador(j);
					}
				}
			}
		}
}