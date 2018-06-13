package so;
import memoria.MemoriaVirtual;

public class WorkingSet{
	long limite;
	
	public WorkingSet(long limite) {
		this.limite = limite;
	}
	
	public int exec(MemoriaVirtual memVirtual) {
		System.out.println("WORKING SET");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long tClock = memVirtual.getTempoAtualDeClock();
		int endereco = -1;
		long menorIdade = 0;
		long idade = 0;
		for (int i = 0; i < memVirtual.getTamanho(); i++) {
			
			if(memVirtual.isReal(i)) { //As paginas que existem
			
				if (memVirtual.getEstadoPagina(i)) { //Os que estão na Memoria Ram
					
					idade = calcIdade(tClock, memVirtual.getUltimaReferencia(i));
					
					if (memVirtual.getReferencia(i) == true) {
						memVirtual.setUltimaReferencia(i,tClock);
						
					}else if((memVirtual.getReferencia(i) == false) && (idade > this.limite )) {
						endereco = i;
						break;
					}else if((memVirtual.getReferencia(i) == false) && (idade <= this.limite )) {
						if(idade > menorIdade) {
							endereco = i;
							menorIdade = idade;
						}
					}
				}
			}
		}
		
		return endereco;
	}
	
	public long calcIdade(long tClock, long tPagina) {
		return (tClock - tPagina);
	}
	
}
