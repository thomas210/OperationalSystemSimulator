package memoria;

public class Pagina {

	private boolean estado;  	  //IF TRUE -> MEMORIA RAM  | ELSE -> HD
	private int enderecoFisico;   //ENDEREÇO DA MEMORIA RAM
	private boolean referencia;   //SE FOI UTILIZADA
	private long ultimaReferencia;//TEMPO DO CLOCK PARA CALCULAR A IDADE

	
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
