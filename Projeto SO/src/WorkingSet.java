

public class WorkingSet{
	long t;
	
	public WorkingSet(long tempo) {
		this.t = tempo;
	}
	
	public void ws(long tempoVirtual) {
		
		for(Pagina pag : memRam.getPaginas()) {
			
			boolean ref = pag.getReferencia();
			long idade = (tempoVirtual - pag.getUltimaReferencia());
			
			if(ref == true) {
				pag.setUltimaReferencia(tempoVirtual);
				
			}else if((ref == false) && (idade > t)) {
				//colocar no HD
				//Tirar da memoriaRam
			}else if((ref == false) && (idade <= t)) {
				//Lembrar da maior idade e remove-la no final
			}
		}
	}
	
	
}
