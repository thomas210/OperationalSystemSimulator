package memoria;
import java.util.ArrayList;

public class MemoriaPrincipal {

	private ArrayList<Integer> memoriaPrincipal; //ARRAY DE INTEIROS
	
	
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
		this.memoriaPrincipal.set(endereco, valor);
	}
	
	
	
	public int getPosicaoLivre() {
		for (int i = 0; i < this.memoriaPrincipal.size(); i++) { //PERCORRER TODA MEMORIA
			if (this.memoriaPrincipal.get(i) == null) {          //TESTAR SE ESTÁ LIVRE
				return i;
			}
		}
		return -1;												//CASO CONTRÁRIO RETURN -1
	}
	
	public void setNull(int endereco) { //SET NULL
		this.memoriaPrincipal.set(endereco, null);
	}
}
