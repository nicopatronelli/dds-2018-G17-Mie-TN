package usuarios;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import hibernate.PersistEntity;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario extends PersistEntity<Usuario> {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE) @Column(name = "id")
	protected Long id;
	
	protected String nombre;
	
	protected String apellido;
	
	protected String usuario;
	
	protected String password;
	
	protected Usuario() {
		// Constructor vacío para Hibernate
	}
	
	protected Usuario(String nombre, String apellido, String usuario, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.password = password;
	}

	/* GETTERS Y SETTERS */ 
	
	public Long id() {
		return id;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getNombreApellido() {
		return nombre + " " + apellido;
	}
	
}
