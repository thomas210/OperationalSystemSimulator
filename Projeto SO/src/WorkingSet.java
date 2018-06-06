

public class WorkingSet{
	long limite;
	
	public WorkingSet(long limite) {
		this.limite = limite;
	}
	
	public int exec(MemoriaVirtual memVirtual, long tClock) {
		int endereco = 0;
		long menorIdade = 0;
		long idade = 0;
		for (int i = 0; i < memVirtual.getTamanho(); i++) {
			
			idade = calcIdade(tClock, memVirtual.getUltimaReferencia(i));
			
			if (memVirtual.getEstadoPagina(i)) {
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
		
		return endereco;
	}
	
	public long calcIdade(long tClock, long tPagina) {
		return (tClock - tPagina);
	}
	
}
