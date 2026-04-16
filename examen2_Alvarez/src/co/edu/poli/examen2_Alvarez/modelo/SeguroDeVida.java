package co.edu.poli.examen2_Alvarez.modelo;

public class SeguroDeVida extends Seguro {

	private boolean beneficiario;

	public SeguroDeVida(String numero, String fechaExp, boolean estado, Asegurado asegurado, boolean beneficiario) {
		super(numero, fechaExp, estado, asegurado);
		this.beneficiario = beneficiario;
	}

	public boolean getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(boolean beneficiario) {
		this.beneficiario = beneficiario;
	}

	@Override
	public String toString() {
		return "Seguro de Vida [" + super.toString() + ", Beneficiario=" + beneficiario + "]";
	}
}
