package modelo;

public class Cliente {
	
	private int codigoCliente;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String dni;
	
	
	public Cliente(int codigoCliente, String nombres, String apellidos, String direccion, String telefono, String dni) {
		this.codigoCliente = codigoCliente;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
		
	}
	
	public Cliente(String nombres, String apellidos, String direccion, String telefono, String dni) {
		this();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public Cliente() {
		this.codigoCliente = generarCodigo();
	}
	
	public int getCodigoCliente() {
		return this.codigoCliente;
	}
	
	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public int generarCodigo() {
		if(DocCliente.obtenerCodUltCliente()==-1) {
			return 1000;
		}
		return DocCliente.obtenerCodUltCliente()+1;
	}

}
