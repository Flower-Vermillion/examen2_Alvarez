package co.edu.poli.examen2_Alvarez.modelo;

public class SeguroDeVehiculo extends Seguro {

	private String marca;

	public SeguroDeVehiculo(String numero, String fechaExp, boolean estado, Asegurado asegurado, String marca) {
		super(numero, fechaExp, estado, asegurado);
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Seguro del vehiculo [" + super.toString() + ", de marca-" + marca + "]";
	}
}
