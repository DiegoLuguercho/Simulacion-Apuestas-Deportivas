package Controlador;


import java.util.Scanner;
import Archivo.Archivo;
import Archivo.ArchivoFile;
import Menu.Menu;
import Clases.Administrador;
import Clases.Equipo;
import Clases.Jugador;
import Clases.Password;
import Clases.Programa;
import Clases.Torneo;
import Clases.Usuario;


public class Controlador {
	
	private Scanner scanner = new Scanner(System.in);
	private Programa programa;

	
	public Controlador(Programa t)
	{
		this.programa=t;
	}
	
	public void inicio() throws Exception 
	{	
		programa.agregarJugadoresAEquipo();
		Torneo torneo = new Torneo ("Copa", 16, programa.getEquipos());
		torneo.agregarJugadoresDePrograma(programa);
        boolean hayAdmin = true;
        String opcion, opcion1, opcion2, opcion3;
      
        //System.out.println(torneo.verCamisetaJugadores(7));
			do 
            {
            	Menu.MenuInicio();
            	opcion = scanner.nextLine();
            	switch (opcion) 
            	{
            	     case "1"://iniciar sesion
            	  
            	    	System.out.print("\nIngrese Nombre de Usuario: ");
         	            String idUsuario = scanner.nextLine();
         	            System.out.print("Ingrese Contraseña: ");
         	            String idContraseña = scanner.nextLine();
         	            Usuario usuario = programa.devuelveUsuario(idUsuario);
         	            /*Autenticacion de credenciales para Admin*/
         	            if(idUsuario.equals(programa.getAdmin().getUsuario()))
         	            {
         	             
         	            	System.out.println("\nInicio sesion de Administrador");
         	                Administrador admin = programa.getAdmin();
         	                if (admin.equals(Administrador.proveerDefaultAdmin()) )
         	                {
         	                	hayAdmin = false;
         	                }
         	                if (!hayAdmin) 
         	                {
         	                	System.out.println("\nUsted ha iniciado sesion con credenciales por defecto. " + "Se recomienda que estas sean modificadas.");
         	                }
         	                /*Menu de Administrador*/
         	                do 
         	                {
         	                	Menu.MenuAdmin();
         	                	opcion1= scanner.nextLine();
         	                	switch (opcion1) 
         	                	{
                                     case "1"://agregar equipo
                                     	programa.agregarEquipo(admin.crearEquipo(scanner, programa));
                                     	Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);

                                     break;
                                     case "2"://agregar jugador a equipo
                                         admin.agregarJugadorAEquipo(programa, scanner);
                                         Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);
                                           break;
                                     case "3"://crear jugador
                                    	 programa.agregarJugador(admin.crearJugador(scanner, programa));
                                    	 Archivo.escribirObjeto(programa.getJugadores(), ArchivoFile.JUGADORES);
                                     break;
                                     case "4"://eliminar equipo
                                    	 admin.eliminarEquipo(programa, scanner);
                                    	 Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);
                                     break;
                                       case "0":
                                           break;
                                       default:
                                           Menu.opcionIncorrecta();
                                }//switch
         	                  }while (!opcion1.equals("0"));
         	                
         	             }else if(usuario != null) {
         	            	 
         	            	if(usuario.getPassword().equals(idContraseña)) {
         	            		
	         	            	System.out.println("\nHa iniciado sesion exitosamente. Bienvenido " + usuario.getNombreyApellido());
	         	            	//Primer Menu de Usuario//
	         	            	do
	         	 	            {
	         	 	            	Menu.MenuInicioSesion();
	         	 	            	opcion2 = scanner.nextLine();
	         	 	            	boolean flag=true;
                                	boolean flag2=true;
                                	boolean flagSimular=false;
                                	boolean flagSimular2=false;
                                	float cantidad=0;
                                	int numCamiseta=0;
                                	int goles=0;
                                	int id=0;
                                	int idE2=0;
                        
	         	 	            	switch (opcion2) 
	         	 	            	{
		         	                       case "1"://apostar         
		         	                       {
		         	                             if(usuario.getCuenta()>0)
		        	         	                 {
		         	                                 do 
		         	                                 {
		         	                                	 Menu.MenuTipoApuesta();
		         	                                	 opcion3=scanner.nextLine();
		         	                                	 switch(opcion3)
		         	                                	 {
		         	                                	     case"1"://apostar por jugador
		         	                                	     {
		         	                                	    	 if(flag==true)
		         	                                	    	 {

					         	                                     System.out.println("Esta seguro que desea apostar? Pulse 's' para continuar: "); 
					         	                                     String rta = "";
					         	                                     rta = scanner.nextLine();
					         	                                    
					         	                                     if (rta.equals("s")||rta.equals("s")) 
					         	                                     {
					         	                                    	 System.out.println("\nSaldo en su cuenta :"+usuario.getCuenta());
					         	                                    	 System.out.println("\n\nIngrese la cantidad que desea apostar :"); 
					         	                                    	 cantidad = scanner.nextFloat();
					         	                                    	 boolean flagApostar = usuario.apostar(cantidad);
					         	                                    	 
					         	                                    	 if (flagApostar == true) 
					         	                                    	 {
					         	                                    		 System.out.println(torneo.verFecha());
					         	                                    		 System.out.println("\nIngresar codigo del partido: ");
							         	                                     int codigo = scanner.nextInt();
							         	                         
							         	                                     String comp1="no";
							         	                                     String comp=null;
							         	                                    
							         	                                     comp = torneo.verID(codigo);
							         	                                     if(comp1.equals(comp))
							         	                                     {
							         	                                    	System.out.println("\nCodigo mal ingresado");
							         	                                     }
							         	                                     else
							         	                                     {
							         	                                    	 System.out.println(comp);
							         	                                    	 System.out.println("\nIngresar ID a elegir: ");
								         	                                     id = scanner.nextInt();
								         	                                 
								         	                                     //idE2 = torneo.idPartidoPorCodigo(codigo, id);
								         	                                     boolean flagg = torneo.existePartido(id);
								         	                                     
								         	                                     if(flagg == true) 
								         	                                     {	
								         	                                    	 Equipo e = programa.getEquipoPorId(id);
								         	                                         System.out.println(programa.mostrarJugadores(e));
								         	                                         System.out.println("\nIngrese el numero de camiseta del jugador a elegir");
								         	                                         numCamiseta = scanner.nextInt();
								         	                                         flag = e.estaJugador(numCamiseta);
								         	                                         
								         	                                         if (flag == true) {
								         	                                        	 
								         	                                        	 usuario.setIdEquipo(id);
									         	                                         System.out.println("\nIngrese la cantidad de goles que piensa que realizara");
									         	                                         goles = scanner.nextInt();
									         	                                    	 flag = usuario.gananciaDeApuestasPorGolDeJugadores(numCamiseta, usuario.getIdEquipo(), goles, programa);
									         	                                    	 flagSimular = true;
									         	                                    	 programa.agregarUsuario(usuario);
								         	                                    		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
								         	                                    		 flag2=false;
								         	                                    		 flag=false;
								         	                                    		 flagSimular2=true;
								         	                                    		 System.out.println("\nAPUESTA REALIZADA");
								         	                                     	}else {
								         	                                     		System.out.println("\nNo existe el ID");
								         	                                     	}
								         	                                     }
								         	                                     else
								         	                                     {
								         	                                    	 System.out.println("\nID mal ingresado, vuelve a realizar apuesta");
								         	                                     }
						         	                                    		
							         	                                     }

					         	                                    	}else
					        	         	                       		{
					        	         	                       		     System.out.println("\n\nNo posee suficiente dinero en la cuenta");
					        	         	                       		}
					         	                                    	
					         	                                     }
		         	                                	    	 }
		         	                                	    	 else
		         	                                	    	 {
		         	                                	    		 System.out.println("\nYa hecho otro tipo de apuesta o ya ha apostado");
		         	                                	    	 }
		         	                                	     }
		         	                                	     break;
		         	                                	     case "2"://apostar por Equipo
		         	                                	     {
		         	                                	    	 if(flag2==true)
		         	                                	    	 {
		         	                                	    		 flag=false;
					         	                                     System.out.println("Esta seguro que desea apostar? Pulse 's' para continuar: "); 
					         	                                     String rta = "";
					         	                                     rta = scanner.nextLine();
					         	                                    
					         	                                     if (rta.equals("s")||rta.equals("s")) 
					         	                                     {
					         	                                    	 System.out.println("\nSaldo en su cuenta :"+usuario.getCuenta());
					         	                                    	 System.out.println("\n\nIngrese la cantidad que desea apostar :"); 
					         	                                    	 cantidad = scanner.nextFloat();
					         	                                    	 boolean flagApostar = usuario.apostar(cantidad);
					         	                                    	 
					         	                                    	 if (flagApostar == true) 
					         	                                    	 {
					         	                                    		 System.out.println(torneo.verFecha());
					         	                                    		 System.out.println("\nIngresar codigo del partido: ");
							         	                                     int codigo = scanner.nextInt();
							         	                         
							         	                                     String comp1="no";
							         	                                     String comp=null;
							         	                                    
							         	                                     comp=torneo.verID(codigo);
							         	                                     if(comp1.equals(comp))
							         	                                     {
							         	                                    	System.out.println("\nCodigo mal ingresado");
							         	                                     }
							         	                                     else
							         	                                     {
							         	                                    	
							         	                                    	 System.out.println(comp);
							         	                                    	 System.out.println("\nIngresar ID a elegir: ");
								         	                                     id = scanner.nextInt();
								         	                                 
								         	                                     idE2 = torneo.idPartidoPorCodigo(codigo, id);
								         	                                     boolean flagg = torneo.existePartido(id);
								         	                                     
								         	                                     if(flagg==true) 
								         	                                     {	
								         	                                    	 flagSimular=true;
								         	                                    	 usuario.descontarDinero(cantidad);
								         	                                    	 System.out.println(usuario.verApuesta());
								         	                                    	 programa.agregarUsuario(usuario);
							         	                                    		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
							         	                                    		 flag=false;
								         	                                     }
								         	                                     else
								         	                                     {
								         	                                    	 System.out.println("\nID mal ingresado, vuelve a realizar apuesta");
								         	                                    	 break;
								         	                                     }
						         	                                    		
							         	                                     }
							         	                                     
							         	                                     
					         	                                    	}else
					        	         	                       		{
					        	         	                       		     System.out.println("\n\nNo posee suficiente dinero en la cuenta");
					        	         	                       		     break;
					        	         	                       		}
					         	                                    	
					         	                                     }
		         	                                	    	 }
		         	                                	    	 else
		         	                                	    	 {
		         	                                	    		 System.out.println("\nYa hecho otro tipo de apuesta o ya ha apostado");
		         	                                	    		 break;
		         	                                	    	 }
		         	                                	     }
		         	                                	     break;
		         	                                	     case "3"://simular fecha
		         	                                	     {
		         	                                	    	 if((flagSimular==true) || (flagSimular2==true))
		         	                                	    	 {
		         	                                	    		 if(flagSimular==true)//equipos
		         	                                	    		 {
		         	                                	    			 torneo.simularFecha2();
		         	                                	    		
				         	                                	    	 System.out.println(torneo.verFechaConResultados());
				         	                                	    	 boolean flagGanancia = usuario.gananciaDeApuestaPorCantidadDeUsuarios(usuario.getIdEquipo(), idE2, programa);
				         	                                    		 if (flagGanancia == true) {
				         	                                    			 
				         	                                    			 System.out.println("\nHa ganado en su apuesta :");
				         	                                    			 System.out.println("\n\nSaldo en su cuenta :"+usuario.getCuenta());
				         	                                    			 programa.agregarUsuario(usuario);
					         	                                    		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
				         	                                    		 }
				         	                                    		 else
				         	                                    		 {
				         	                                    			System.out.println("\nLamentablemente no ha salido positiva tu apuesta");
				         	                                    		 }
				         	                                    		 flagSimular=false;
				         	                                    		 flagSimular2=false;
				         	                                    		 cantidad=0;
					         	                                    	 numCamiseta=0;
					         	                                   	     goles=0;
					         	                                   	     id=0;
					         	                                   	     idE2=0;
					         	                                   	     flag2=true;
					         	                                   	     flag=true;
		         	                                	    		 }
		         	                                	    		 else if(flagSimular2==true)//jugadores
		         	                                	    		 {
		         	                                	    			 
		         	                                	    			 boolean flagApuesta = usuario.gananciaDeApuestasPorGolDeJugadores(numCamiseta, usuario.getIdEquipo(), goles, programa);
					         	                                                                 	 
					         	                                    	 if (flagApuesta == false) {
					         	                                    		 
					         	                                    		 System.out.println("\nCONVERSIONES :"+"\n"+programa.convertioGolesPorId(id));
					         	                                    		 
					         	                                    		 System.out.println(usuario.verApuesta());					         	   
					         	                                    		 usuario.descontarDinero(cantidad);
					         	                                    	 	 System.out.println("\nNo ha ganado la apuesta");
					         	                                    	 	 
					         	                                    	 }
					         	                                    	 else
					         	                                    	 {
					         	                                    		 System.out.println("\nCONVERSIONES :"+"\n"+programa.convertioGolesPorId(id));
					         	                                    		 System.out.println("\nApuesta ganada! , resultados :");
					         	                                    		 System.out.println(usuario.verApuesta());
					         	                                    		 programa.agregarUsuario(usuario);
					         	                                    		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
					         	                                    		 
					         	                                    	 }
					         	                                    	 flagSimular2=false;
					         	                                    	 flagSimular=false;
					         	                                    	 cantidad=0;
					         	                                    	 numCamiseta=0;
					         	                                   	     goles=0;
					         	                                   	     id=0;  
					         	                                   	     flag=true;
					         	                                   	     flag2=true;
					         	                                    	 
		         	                                	    		 } 
		         	                                	    	 }
		         	                                	    	 else
		         	                                	    	 {
		         	                                	    		System.out.println("\nAún no se han hecho apuestas");
		         	                                	    	 }
		         	                                	    	 
		         	                                	     }
		         	                                	     break;
		         	                                	     case "0":
		         	   	         	                         break;
		         	   	         	                         default:
		         	   	         	                             Menu.opcionIncorrecta();
		         	                                	 
		         	                                   }//switch	         	                                 
		         	                              }while(!opcion3.equals("0"));//DO	         	                            	  
		        	         	             }//if
		         	                         else
		        	         	            {
		        	         	                System.out.println("\nNo pose suficiente dinero en la cuenta");
		        	         	            }
		         	                                    	
		         	                    }//case
	         	 	            	    break;
	         	 	            	    case "2"://agregar dinero         
	         	                        {
	         	                         	float dinero = 0;
	         	                         	System.out.println("\nIngrese el dinero que desea agregar a la cuenta: ");
	         	                         	dinero = scanner.nextFloat();
	         	                         	boolean flagAgregarDinero = usuario.agregarDinero(dinero);
	         	                         	if(flagAgregarDinero==true)
	         	                         	{
	         	                         		System.out.println("\nDinero agregado, saldo actual: "+usuario.getCuenta());
	         	                         		programa.agregarUsuario(usuario);
	         	                         		Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
	         	                         	}
	         	                         	else
	         	                         	{
	         	                         		System.out.println("\nHa ocurrido un error al agregar dinero");
	         	                         	}
	         	                         	
	         	                        }
	         	                        break;
	         	                        case "3"://extraer dinero
	         	                        {
	         	                        	float dinero1 = 0;
	         	                         	System.out.println("\nIngrese el dinero que desea extraer de la cuenta: ");
	         	                         	dinero1 = scanner.nextFloat();
	         	                         	usuario.descontarDinero(dinero1);
	         	                            System.out.println("\nDinero extraido, saldo actual: "+usuario.getCuenta());
	         	                            programa.agregarUsuario(usuario);
        	                         		Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
	         	                        
	         	                        }
	         	                        break;
	         	                        case "0":
	         	                        break;
	         	                        default:
	         	                             Menu.opcionIncorrecta();
	         	                 }//switch
	     	 	              }while (!opcion2.equals("0"));//do
	 	            		}//if
         	             }else {
         	            	 
         	            	 System.out.println("\nUsuario inexistente.");
         	             }
            	     break;
            	     case "2"://registrarse
            	    
            	    	 programa.agregarUsuario(registrarse());
            	 		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);;
            	     
            	     break;
                     case "0":
                     break;
                     default:
                          Menu.opcionIncorrecta();
          }
	    }while (!opcion.equals("0"));//do
	}//inicio
	
	/**
	 * 
	 * Metodo para registrar un usuario al programa
	 * @return
	 * @throws Exception
	 */
	public Usuario registrarse()throws Exception
	{
		boolean rta=false;
		boolean rta2=false;
		String contraseña=null;
		String usuario=null;
		String nombreYapellido=null;
		String nacionalidad=null;
		String fechaNacimiento=null;
		
		while(!rta)
		{
			System.out.println("Ingresar Usuario: ");
            usuario = scanner.nextLine();

            if (programa.existeUsuario(usuario)) 
            {
                throw new Exception("\nEl Nombre de Usuario ya existe\n");
            }
			System.out.println("\nIngresar Nombre y Apellido:");
			nombreYapellido = scanner.nextLine();
			System.out.println("\nIngresar Nacionalidad:");
			nacionalidad = scanner.nextLine();
			System.out.println("\nIngresar fecha de nacimiento(../../....):");
			fechaNacimiento = scanner.nextLine();
			while (!rta2) 
			{
                System.out.println("\nIngrese contraseña alfanumerica(5-20 digitos): ");
                contraseña = scanner.nextLine();

                if (Password.hasLongitudCorrecta(contraseña)) {
                    rta2 = true;
                } 
                else {
                    System.out.println("\nLa contraseña ingresada no cumple todos los requisitos: ");
                }
            }
            System.out.println("\nUSUARIO REGISTRADO");
			rta=true;
		}
		Usuario n = new Usuario(usuario, nombreYapellido, nacionalidad, fechaNacimiento, new Password(contraseña));
		return n;
	}//registrarse
	 
}

