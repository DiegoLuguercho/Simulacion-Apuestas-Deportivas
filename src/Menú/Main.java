package Menu;

import Clases.Programa;
import Controlador.Controlador;

public class Main {

	public static void main(String[] args) throws Exception {
		
		new Controlador (Programa.getInstancia()).inicio();
	}
}
