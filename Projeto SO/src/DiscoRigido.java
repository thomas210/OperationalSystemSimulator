import java.util.ArrayList;

public class DiscoRigido {

	ArrayList<Integer> discoRigido;

	public DiscoRigido(int tamanho) {
		this.discoRigido = new ArrayList<Integer>();
		for(int i = 0; i < tamanho; ++i) {
			discoRigido.add(null);
		}
	}


	public int getDiscoRigido(int index) {
		return discoRigido.get(index);
	}

	public void setDiscoRigido( int index,int valor) {
		this.discoRigido.set(index, valor);
	}
	
	
}
