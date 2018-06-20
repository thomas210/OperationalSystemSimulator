package memoria;
import java.util.ArrayList;

public class MemoriaVirtual {
	
	private ArrayList<Pagina> memoriaVirtual; //ARRAY DE PAGINAS
	private long TempoAtualDeClock = 0l;      //VARIAVEL PARA O TICK DO CLOCK
	
	public MemoriaVirtual(int tamanho) {
		this.memoriaVirtual = new ArrayList<Pagina>();
		for (int i = 0; i < tamanho; i++) {
			this.memoriaVirtual.add(null);
		}
	}

	
	/**METODOS DA PAGINA**/
	
	public void criarPagina (int enderecoVirtual, int enderecoFisico) {
		Pagina pagina = new Pagina(true, enderecoFisico, this.TempoAtualDeClock);
		this.memoriaVirtual.set(enderecoVirtual, pagina);
	}
	
	public int getEnderecoFisico (int endereco) {
		int res;
		if (this.memoriaVirtual.get(endereco) == null) { //TESTA DE EXISTE UMA PAGINA NO ENDEREÇO SOLICITADO
			res = -1;
		}else {
			res = this.memoriaVirtual.get(endereco).getEnderecoFisico(); //RETORNA O ENDEREÇO DA MEMORIA RAM
		}
		return res;
	}
	
	
	public boolean getEstadoPagina(int endereco) {
		return this.memoriaVirtual.get(endereco).isEstado();
	}
	
	public void setEstadoPagina(int endereco, boolean estado) {
		this.memoriaVirtual.get(endereco).setEstado(estado);
	}
		
	public void EnderecoReferenciado(int endereco) { //FOI UTILIZADO
		this.memoriaVirtual.get(endereco).setUltimaReferencia(this.TempoAtualDeClock);
		this.memoriaVirtual.get(endereco).isReferencia(true);
		
	}

	public boolean getReferencia(int endereco) {
		return this.memoriaVirtual.get(endereco).getReferencia();
	}
	
	public long getUltimaReferencia (int endereco) { //CLOCK
		return this.memoriaVirtual.get(endereco).getUltimaReferencia();
	}
	
	public void setUltimaReferencia (int endereco, long valor) { //CLOCK
		this.memoriaVirtual.get(endereco).setUltimaReferencia(valor);
	}
	
	public void zerarReferencia() { //ZERAR TODAS AS REFERENCIAS DAS PAGINAS
		for(int i = 0; i < this.getTamanho(); ++i) {
			if (isReal(i)) {
				this.memoriaVirtual.get(i).isReferencia(false);
			}
		}
	}
	
	
	/**METODOS DA MEMORIA VIRTUAL**/
	
	public boolean isReal(int endereco) { //TESTA A EXISTENCIA DE UMA PAGINA
		boolean res = false;
		if (this.memoriaVirtual.get(endereco) != null) {
			res = true;
		}
		return res;
	}
	
	public int getTamanho() {
		return memoriaVirtual.size();
	}
	
	public void setTempoAtualDeClock(long clock) {
		this.TempoAtualDeClock = clock;
	}
	
	public long getTempoAtualDeClock() {
		return this.TempoAtualDeClock;
	}
	
	
	
	

}
