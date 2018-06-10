import java.util.ArrayList;

public class MemoriaVirtual {
	
	ArrayList<Pagina> memoriaVirtual;
	long TempoAtualDeClock = 0l;
	
	public MemoriaVirtual(int tamanho) {
		this.memoriaVirtual = new ArrayList<Pagina>();
		for (int i = 0; i < tamanho; i++) {
			this.memoriaVirtual.add(null);
		}
	}

	

	public int getEnderecoFisico (int endereco) {
		int res;
		if (this.memoriaVirtual.get(endereco) == null) {
			res = -1;
		}else {
			res = this.memoriaVirtual.get(endereco).getEnderecoFisico();
		}
		return res;
	}
	
	
	public void criarPagina (int enderecoVirtual, int enderecoFisico) {
		Pagina dados = new Pagina(true, enderecoFisico, TempoAtualDeClock);
		this.memoriaVirtual.add(enderecoVirtual, dados);
	}
	
	public boolean getEstadoPagina(int endereco) {
		return this.memoriaVirtual.get(endereco).isEstado();
	}
	
	public void setEstadoPagina(int endereco, boolean estado) {
		this.memoriaVirtual.get(endereco).setEstado(estado);
	}
	
	public boolean isReal(int endereco) {
		boolean res = false;
		if (this.memoriaVirtual.get(endereco) != null) {
			res = true;
		}
		return res;
	}
	
	
	/***********************************************************/
	public boolean getReferencia(int endereco) {
		return this.memoriaVirtual.get(endereco).getReferencia();
	}
	
	public long getUltimaReferencia (int endereco) {
		return this.memoriaVirtual.get(endereco).getUltimaReferencia();
	}
	
	public void setUltimaReferencia (int endereco, long valor) {
		this.memoriaVirtual.get(endereco).setUltimaReferencia(valor);
	}
	/*************************************************************/
	
	public int getTamanho() {
		return memoriaVirtual.size();
	}
	
	public void setTempoAtualDeClock(long clock) {
		this.TempoAtualDeClock = clock;
	}
	
	public long getTempoAtualDeClock() {
		return this.TempoAtualDeClock;
	}
	
	public void zerarReferencia() {
		for(int i = 0; i < this.getTamanho(); ++i) {
			if (isReal(i)) {
				this.memoriaVirtual.get(i).isReferencia(false);
			}
		}
	}
	
	
	

}
