package Archivo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Archivo {
	/**
	 * pasando un objeto por parametro y el archivo
	 * Escribe el archivo
	 * @param <T>
	 * @param t
	 * @param file
	 */
	public static <T extends Serializable> void  escribirObjeto(T t, String file)
	{
		try(ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream(file)))
		{
			out.writeObject(t);
			//System.out.println("Se ha modificado y guardado informacion de "+file+" exitosamente.");
		}
		catch (IOException e)
		{
			System.out.println("No se a podido escribir el objeto "+t.getClass().getSimpleName()+" en el archivo.  ");
			e.printStackTrace();
		}	
	}
	@SuppressWarnings("unchecked")
	/**
	 * Lee un objeto en un archivo pasado por parametro
	 * @param <T>
	 * @param file
	 * @return
	 */
	public static <T extends Serializable> T leerObjeto(String file)
	{
		T t = null;
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file)))
		{
			
			t = (T) in.readObject();
			
		}
		catch(IOException | ClassNotFoundException e)
		{
			//e.printStackTrace();
		}
		return t;
	}
	
	

}