package JSon;

import java.util.ArrayList;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Clases.Equipo;
import Clases.Jugador;
import Clases.Usuario;

public class JsonUtility {
	
	private JSONObject equipoJSON;
	private JSONObject jugadorJSON;
	private JSONObject usuarioJSON;
	/**
	 * Carga jugadorJSON con un Jugador pasado por parametro
	 * @param jug
	 * @return
	 * @throws JSONException
	 */
	public JSONObject jugadorAjson (Jugador jug) throws JSONException{
		
		jugadorJSON = new JSONObject();
		
		try {
			///posicion,lesion,expulsado,amonestado,anotacion,asistencia,numerocamiseta
			jugadorJSON.put("Nombre y Apellido", jug.getNombreyApellido());
			jugadorJSON.put("Nacionalidad", jug.getNacionalidad());
			jugadorJSON.put("Fecha de Nacimiento", jug.getFechaNacimiento());
			jugadorJSON.put("Posicion", jug.getPosicion());
			jugadorJSON.put("Numero de camiseta", jug.getNumeroCamiseta());
			jugadorJSON.put("Lesionado", jug.getAnotacion());
			jugadorJSON.put("Expulsado", jug.isExpulsado());
			jugadorJSON.put("Amonestado", jug.isAmonestado());
			jugadorJSON.put("Anotacion", jug.getAnotacion());
	
			
		}catch (JSONException e){
			
			e.printStackTrace();
		}
		
		return jugadorJSON;
	}
	/**
	 * Con un treemap de jugadores pasado por parametros, carga el treemap en un JSONArray
	 * Esto lo hace ejecutando la funcion jugadorAjson
	 * @param jugadores
	 * @return
	 * @throws JSONException
	 */
	public JSONArray arregloJugAjson (TreeMap<String, Jugador> jugadores) throws JSONException {
		
		JSONArray mapJsonJugadores = new JSONArray();
		JSONObject jObj = new JSONObject();
		
		try{
			
			for (Jugador jug : jugadores.values()) {
			
				jObj = jugadorAjson(jug);
				mapJsonJugadores.put(jObj);
			}
		}catch (JSONException e){
			
			e.printStackTrace();	
		}
		
		return mapJsonJugadores;
	}
	/**
	 * Carga equipoJSON con un Equipo pasado por parametro
	 * @param eqi
	 * @return
	 * @throws JSONException
	 */
     public JSONObject equipoAjson (Equipo eqi) throws JSONException{
		
		equipoJSON = new JSONObject();
		
		try {
			///id, nombreDelEquipo, nombredelequipo, cantidaddepuntos, cantidadapuestas, cantidad goles, codigo partido
			equipoJSON.put("Id", eqi.getId());
			equipoJSON.put("Nombre del Equipo", eqi.getNombreDelEquipo());
			equipoJSON.put("Cantidad de Puntos", eqi.getCantidadPuntos());
			equipoJSON.put("Cantidad de Apuestas", eqi.getCantidadApuestas());
			equipoJSON.put("Cantidad de Goles", eqi.getCantidadGoles());
			equipoJSON.put("Codigo Partido", eqi.getCodigoPartido());

			
		}catch (JSONException e){
			
			e.printStackTrace();
		}
		
		return equipoJSON;
	}
     /**
      * Con un arraylist de equipos pasado por parametros, carga el treemap en un JSONArray
	  * Esto lo hace ejecutando la funcion equipoAjson
      * @param equipos
      * @return
      * @throws JSONException
      */
     public JSONArray arregloEquiAjson (ArrayList<Equipo> equipos) throws JSONException {
 		
 		JSONArray arrayJsonEquipos = new JSONArray();
 		JSONObject jObj = new JSONObject();
 		
 		try{
 			
 			for (Equipo eqi : equipos) {
 			
 				jObj = equipoAjson(eqi);
 				arrayJsonEquipos.put(jObj);
 			}
 		}catch (JSONException e){
 			
 			e.printStackTrace();	
 		}
 		
 		return arrayJsonEquipos;
 	}
     /**
      * Carga usuarioJSON con un Usuario pasado por parametro
      * @param usu
      * @return
      * @throws JSONException
      */
     public JSONObject usuarioAjson (Usuario usu) throws JSONException{
 		
 		usuarioJSON = new JSONObject();
 		
 		try {
 			//usuario, nombreyapellido, nacionalidad, fechanacimiento, password, cuenta, cantidadapostado, numeroafiliado
 			usuarioJSON.put("Usuario", usu.getUsuario());
 			usuarioJSON.put("Nombre y Apellido", usu.getNombreyApellido());
			usuarioJSON.put("Nacionalidad", usu.getNacionalidad());
			usuarioJSON.put("Fecha de Nacimiento", usu.getFechaNacimiento());
			usuarioJSON.put("Password", usu.getPassword());
			usuarioJSON.put("Cuenta", usu.getCuenta());
			usuarioJSON.put("Cantidad Apostado", usu.getCantidadApostado());
			usuarioJSON.put("Numero Afiliado", usu.getNumAfiliado());
 			
 		}catch (JSONException e){
 			
 			e.printStackTrace();
 		}
 		
 		return usuarioJSON;
 	} 
     /**
      * Con un treemap de usuarios pasado por parametros, carga el treemap en un JSONArray
	  * Esto lo hace ejecutando la funcion usuarioAjson
      * @param usuarios
      * @return
      * @throws JSONException
      */
      public JSONArray arregloUsuaAjson (TreeMap<String, Usuario> usuarios) throws JSONException {
  		
  		JSONArray mapJsonUsuarios = new JSONArray();
  		JSONObject jObj = new JSONObject();
  		
  		try{
  			
  			for (Usuario usu : usuarios.values()) {
  			
  				jObj = usuarioAjson(usu);
  				mapJsonUsuarios.put(jObj);
  			}
  		}catch (JSONException e){
  			
  			e.printStackTrace();	
  		}
  		
  		return mapJsonUsuarios;
  	}
	
}