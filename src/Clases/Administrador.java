package Clases;

import java.util.Scanner;

public class Administrador extends Usuario{


	public Administrador(String Usuario, String nombreyApellido, String nacionalidad, String fechaNacimiento, Password contraseña) {
		super(Usuario, nombreyApellido, nacionalidad, fechaNacimiento, contraseña);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Administrador [toString()=" + super.toString() + "]";
	}
	/**
	 * Credenciales estandar para admin. Este metodo se llama cuando el sistema no detecta usuario admin al iniciar
	 * @return
	 */
    public static Administrador proveerDefaultAdmin() {
        return new Administrador("Administrador", "ADMIN", "Argentino", "01/01/1990", new Password("123456"));
    }

	/**
	 * Metodo para crear un equipo
	 * @param scanner
	 * @param programa
	 * @return
	 * @throws Exception
	 */
    public Equipo crearEquipo(Scanner scanner, Programa programa) throws Exception 
	{
        String nombreEquipo = null;
        int i = programa.getEquipos().size();
        System.out.println("Ingresar Nombre de Equipo: ");
        nombreEquipo = scanner.nextLine();
        if (programa.existeEquipo(nombreEquipo)) 
        {
            throw new Exception("El Nombre del Equipo ya existe\n");    
        }
        Equipo e = new Equipo(i+1, nombreEquipo);
        
        return e;
	}
	
    /**
     * Metodo para crear un jugador
     * @param scanner
     * @param programa
     * @return
     * @throws Exception
     */
    public Jugador crearJugador(Scanner scanner, Programa programa) throws Exception 
	{
       boolean rta = false;
       String nombreYapellido=null;
       String nacionalidad=null;
       String fechaNacimiento=null;
       String posicion=null;
       
       while(!rta)
       {
			System.out.println("\nIngresar Nombre y Apellido:");
			nombreYapellido = scanner.nextLine();
			System.out.println("\nIngresar Nacionalidad:");
			nacionalidad = scanner.nextLine();
			System.out.println("\nIngresar fecha de nacimiento(../../....):");
			fechaNacimiento = scanner.nextLine();
			System.out.println("\nIngresar Posicion(Delantero, MedioCampista, Defensor, Arquero):");
			posicion = scanner.nextLine();
			rta=true;
       }
       Jugador j = new Jugador(nombreYapellido, nacionalidad, fechaNacimiento, posicion);
       
       return j;
       
	}
	
    /**
     * Metodo que a partir de un id, se busca un equipo con el id ingresado por teclado
     * @param scanner
     * @param programa
     * @return
     */
    private Equipo obtenerEquipoPorId(Scanner scanner, Programa programa) {
        Equipo e;
        System.out.println("Lista de Equipos:\n");
        programa.mostrarEquipos();

        System.out.println("Seleccione el ID del Equipo: ");
        int i = scanner.nextInt();
        e = programa.encontrarEquipoPorID(i);

        return e;
    }
	
    /**
     * Metodo para eliminar un equipor por id, se llama al metodo obtenerEquipoPorId
     * @param programa
     * @param scanner
     */
    public void eliminarEquipo(Programa programa, Scanner scanner) {
        
            Equipo aEliminar = obtenerEquipoPorId(scanner, programa);

            System.out.println("Esta seguro que desea eliminar el siguiente Equipo: "+ aEliminar.getNombreDelEquipo()+ "\n");
            System.out.print("Presione 's' para confirmar: ");
            String opcion = scanner.nextLine();

            if (opcion.equals("S") || opcion.equals("s")) {
                programa.removerEquipo(aEliminar.getId());
                System.out.println("El Equipo se ha eliminado exitosamente.");
            } else {
                System.out.println("El Equipo no se ha eliminado");
            }

    }
	public void agregarJugadorAEquipo(Programa programa, Scanner scanner) 
	{
		
			Jugador jSeleccionado = obtenerJugadorPorNombre(scanner, programa);
			System.out.println("SELECCIONAR EQUIPO A AGREGAR \n");
			Equipo aAgregar = obtenerEquipoPorId(scanner, programa);
			System.out.println("Esta seguro que desea agregar el jugador "+jSeleccionado.getNombreyApellido()+" en "+aAgregar.getNombreDelEquipo()+".\n");
            System.out.println();          
            System.out.print("Presione 's' para confirmar: ");
            String opcion = scanner.nextLine();
            if (opcion.equals("S") || opcion.equals("s")) {
            	jSeleccionado.setIdEquipo(aAgregar.getId());
            	programa.agregarJugadorParticularAEquipo(jSeleccionado);
                System.out.println("El jugador se agrego existosamente.");
            } else {
                System.out.println("El jugador no se agrego. ");
            }
		
	}
	private Jugador obtenerJugadorPorNombre(Scanner scanner, Programa programa) {
        Jugador j;
        System.out.println("Lista de Jugadores sin Equipos:\n");
        programa.mostrarJugadoresSinEquipo();

        System.out.println("Escribe nombre de Jugador: ");
        int key = scanner.nextInt();
        j = programa.encontrarJugadorPorNombre(key);

        return j;
    }


}
