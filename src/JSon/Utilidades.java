package JSon;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;

public class Utilidades {
	/**
	 * Toma un archivo y un JSONArray, ambos pasados por parametro
	 * Esto carga el JSONArray en un Archivo.json preferiblemente
	 * @param array
	 * @param archivo
	 */
	public static void agregar(JSONArray array, String archivo)
	{
		try {
			FileWriter file = new FileWriter(archivo);
			file.write(array.toString());
			file.flush();
			file.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * Lee cualquier tipo de archivo
	 * Funcion hecha para leer json
	 * @param archivo
	 * @return
	 */
	public static String leer(String archivo)
	{
		String contenido = "";
		try {
			contenido = new String(Files.readAllBytes(Paths.get(archivo)));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return contenido;
	}

}
