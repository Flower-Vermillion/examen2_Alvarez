package co.edu.poli.examen2_Alvarez.modelo;

public class Seguro {
	
	private String numero;

	private String fechaExp;

	private boolean estado;

	private Asegurado asegurado;

	public Seguro(String numero, String fechaExp, boolean estado, Asegurado asegurado) {
		this.numero = numero;
		this.fechaExp = fechaExp;
		this.estado = estado;
		this.asegurado = asegurado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Asegurado getAsegurado() {
		return asegurado;
	}

	public void setAsegurado(Asegurado asegurado) {
		this.asegurado = asegurado;
	}

	public String cancelar() {
		this.estado = false;
		return "El seguro con número: " + numero + " HA SIDO CANCELADO";
	}

	public String activar() {
		this.estado = true;
		return "El seguro con número: " + numero + " ACTIVADO.";
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", asegurado=" + asegurado;
	}
}