package memoria;

public class Pagina {

	private boolean estado;
	private int enderecoFisico;
	private boolean referencia;
	private long ultimaReferencia;

	
	public Pagina(boolean estado,int enderecoFisico, long ultimaReferencia) {
		
		this.estado = estado;
		this.enderecoFisico = enderecoFisico;
		this.referencia = true;
		this.ultimaReferencia = ultimaReferencia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getEnderecoFisico() {
		return enderecoFisico;
	}

	public void setEnderecoFisico(int enderecoFisico) {
		this.enderecoFisico = enderecoFisico;
	}

	public boolean getReferencia() {
		return referencia;
	}

	public void isReferencia(boolean referencia) {
		this.referencia = referencia;
	}

	public long getUltimaReferencia() {
		return ultimaReferencia;
	}

	public void setUltimaReferencia(long ultimaReferencia) {
		this.ultimaReferencia = ultimaReferencia;
	}
	
	
}
