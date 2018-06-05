import java.util.ArrayList;

public class MemoriaPrincipal {

	ArrayList<Integer> memoriaPrincipal;
	
	
	public MemoriaPrincipal(int tamanho) {
		this.memoriaPrincipal = new ArrayList<Integer>();
		for (int i = 0; i < tamanho; i++) {
			this.memoriaPrincipal.add(null);
		}
	}
	
	public int getValor (int endereco) {
		return this.memoriaPrincipal.get(endereco);
	}
	
	public void setValor (int endereco, int valor) {
		this.memoriaPrincipal.add(endereco, valor);
	}
	
	public int getPosicaoLivre() {
		for (int i = 0; i < this.memoriaPrincipal.size(); i++) {
			if (this.memoriaPrincipal.get(i) == null) {
				return i;
			}
		}
		return -1;
	}
}
