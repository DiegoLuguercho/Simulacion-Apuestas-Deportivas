package Clases;

import java.io.Serializable;

public class Jugador extends Persona implements Serializable{
	
	private String posicion; //delantero, base, pivot, etc
	private boolean lesion; //true: lesion
	private boolean expulsado; //true: expulsion
	private boolean amonestado; //copa = tres expulsion // liga: 5
	private int numeroCamiseta; 
	private int anotacion; //goles: futbol, puntos: basquet y rugby
	private int idEquipo;

	//Constructores
    public Jugador(String nombreyApellido, String nacionalidad, String fechaNacimiento, String posicion, int numeroCamiseta,  int idEquipo) {
		
		super(nombreyApellido, nacionalidad, fechaNacimiento);
		
		this.posicion = posicion;
		this.numeroCamiseta = numeroCamiseta;
		setExpulsado(false);
		lesion = false;
		setAmonestado(false);
		anotacion = 0;
		this.idEquipo=idEquipo;

     }
    public Jugador(String nombreyApellido, String nacionalidad, String fechaNacimiento, String posicion) {
    	
 		super(nombreyApellido, nacionalidad, fechaNacimiento);
 		
 		this.posicion = posicion;
 		numeroCamiseta = 0;
 		setExpulsado(false);
 		lesion = false;
 		setAmonestado(false);
 		anotacion = 0;
      }
	
	//Getters y Setters
    public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public void setNumeroCamiseta(int numeroCamiseta) {
		this.numeroCamiseta = numeroCamiseta;
	}

	public boolean isLesion() {
		return lesion;
	}

	public void setLesion(boolean lesion) {
		this.lesion = lesion;
	}

	public int getAnotacion() {
		return anotacion;
	}

	public void setAnotacion(int anotacion) {
		this.anotacion = anotacion;
	}

	
    
	public int getIdEquipo() {
			return idEquipo;
		}
	public void setIdEquipo(int idEquipo) {
			this.idEquipo = idEquipo;
		}
	public boolean expulsion () {
		
		boolean expulsado = false;
		return expulsado;
	}


	public boolean isExpulsado() {
		return expulsado;
	}


	public void setExpulsado(boolean expulsado) {
		this.expulsado = expulsado;
	}

	public boolean isAmonestado() {
			return amonestado;
		}
	public void setAmonestado(boolean amonestado) {
			this.amonestado = amonestado;
		}
	/**
	 * metodo equals que compara segun la posicion del jugador
	 */
	public boolean equals (Object obj) 
        {
			
			if (obj != null) {
				
				if (obj instanceof Jugador) {
					
					Jugador aux = (Jugador) obj;
					
					if (aux.getPosicion().equals(getPosicion())){
						
						System.out.println(((Jugador) obj).getPosicion());
						return true;
						
					}else{
						return false;
					}
					
				}else{
					return false;
				}
				
			}else{
				return false;
			}
		}

	public String toStringCamiseta () {
		
		return "\n	JUGADOR" + "\n\nNombre :"+getNombreyApellido()+"\nPosicion :"+getPosicion()+"\nNumero camiseta :"+getNumeroCamiseta();
	}
	
	//toString
	public String toString()
           {
        	   return "\n[Jugador]"+"\n Nombre/s y Apellido/s: "+ getNombreyApellido() + "\n Fecha de Nacimiento: " +getFechaNacimiento()+ 
			         "\n Nacionalidad: " +getNacionalidad()+ "\n Posicion: " +getPosicion()+ "\n Numero de Camiseta: " +getNumeroCamiseta();
           }
    /**
     * otro toString que muestra las acciones del jugador luego del partido
     * @return
     */
	public String toStringPosPartido()
    {
        return "\n[Jugador]"+"\n Nombre/s y Apellido/s: "+ getNombreyApellido() + "\n Gol/es convertido/s: " + getAnotacion() 
           	              + "\n Amonestado: " + isAmonestado()+"\n Expulsado: "+isExpulsado();
    }

    /**
     * Mediante un random verifica si el jugador fue amonestado, entre 0 y 30 si, entre 30 y 100 no
     */
	public void probabilidadDeAmonestacion ()
    {
        int numAleatorio = (int) (Math.random() *100);
        if (numAleatorio > 30 && numAleatorio < 40) 
        {
        	setAmonestado(true);
        }
     }

    /**
     * Mediante un random verifica si el jugador fue expulsado, entre 0 y 5 si, entre 5 y 100 no
     */
	public void probabilidadDeExpulsion () 
    {
        int numAleatorio = (int) (Math.random() *100);
        if (numAleatorio > 0 && numAleatorio < 5)
        {
             setExpulsado(true);
        }
     }
    
	/**
	 * Con un random de por medio, primero verifica la posicion del jugador
	 * Segun la posicion tiene mas o menos chances de meter un gol o mas un jugador
	 * El random tira un numero, y segun lo computado se confirma si hizo o no gol/es
	 */
    public void cantidadDeGoles () 
    {
        	  int cantidadGol = 0;
        	  int numAleatorio = (int) (Math.random() *100);
        	  
        	  if (getPosicion().equals("Delantero"))
        	  { 
        		  if( numAleatorio > 0 && numAleatorio <= 6)
        			  cantidadGol = 3;
        		  else if( numAleatorio > 0 && numAleatorio <= 20)
        			  cantidadGol = 2;
        		  else if ( numAleatorio > 0 && numAleatorio <= 40)
        			  cantidadGol = 1;
        		  
        	  }
        	  if (getPosicion().equals("Mediocampista")){
        		  
	        	  if( numAleatorio > 0 && numAleatorio <= 3)
	      			  cantidadGol = 3;
	      		  else if( numAleatorio > 0 && numAleatorio <= 10)
	      			  cantidadGol = 2;
	      		  else if( numAleatorio > 0 && numAleatorio <= 15)
	      			  cantidadGol = 1;
	        	  
	          }
        	  if (getPosicion().equals("Defensor")){
	        	  
	        	  if( numAleatorio > 0 && numAleatorio <= 1)
	      			  cantidadGol = 3;
	      		  else if( numAleatorio > 0 && numAleatorio <= 2)
	      			  cantidadGol = 2;
	      		  else if( numAleatorio > 0 && numAleatorio <= 3)
	      			  cantidadGol = 1;
	          }
	          
        	  if (getPosicion().equals("Arquero")) 
	          {
	        	  if( numAleatorio == 1)
	        		  cantidadGol = 1;
	          }
        	  if (cantidadGol!=0)
        		  setAnotacion(cantidadGol);
	  }
    
}