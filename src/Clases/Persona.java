package Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Persona  implements Serializable{
	
	private String nombreyApellido;
	private String fechaNacimiento; /// interface calcula edad con fecha de nacimiento.
	private String nacionalidad;
	
	//Constructor
	public Persona(String nombreyApellido, String nacionalidad, String fechaNacimiento) {
		this.nacionalidad = nacionalidad;
		this.nombreyApellido = nombreyApellido;
		this.fechaNacimiento =fechaNacimiento;
	}

	//Getters y Setters
	public String getNombreyApellido() {
		return nombreyApellido;
	}
	public void setNombreyApellido(String nombreyApellido) {
		this.nombreyApellido = nombreyApellido;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fecchaNacimiento) {
		this.fechaNacimiento = fecchaNacimiento;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "Persona [nombreyApellido=" + nombreyApellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", nacionalidad=" + nacionalidad + "]";
	}

	
	
}