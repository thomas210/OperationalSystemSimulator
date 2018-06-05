import java.util.ArrayList;

public class MemoriaVirtual {
	
	ArrayList<Pagina> memoriaVirtual;
	
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
	
	
	public void criarPagina (int enderecoVirtual, int enderecoFisico, long tempo) {
		Pagina dados = new Pagina(true, enderecoFisico, tempo);
		this.memoriaVirtual.add(enderecoVirtual, dados);
	}
	
	public boolean getEstadoPagina(int endereco) {
		return this.memoriaVirtual.get(endereco).isEstado();
	}
	
	public boolean isReal(int endereco) {
		boolean res = false;
		if (this.memoriaVirtual.get(endereco) != null) {
			res = true;
		}
		return res;
	}
	
	

}
