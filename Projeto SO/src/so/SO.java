package so;

import Processo.FabricaDeEntradas;
import Processo.Processo;

public class SO {
	
	public static MMU mmu = new MMU(10,10,5,2);
	
	public static void main(String[] args) {
				
		
		String s1 = new FabricaDeEntradas(10).getNewEntrada();
		String s2 = new FabricaDeEntradas(10).getNewEntrada();

		
		new Processo(mmu,s1).start();
		new Processo(mmu,s2).start();

		
	}
	
}
