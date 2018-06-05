
public class SistemaOperacional {
	
	private MemoriaVirtual memVirtual;
	private MemoriaPrincipal ram;
	private Clock clock = new Clock();
	
	public SistemaOperacional() {
		this.memVirtual = new MemoriaVirtual(10);
		this.ram = new MemoriaPrincipal(5);
		this.clock.start();
	}
	
	
	
	public void tratamento(String entrada) {
		if (entrada.charAt(2) == 'R') {
			try{
				ler(Integer.parseInt(entrada.substring(0, 1)));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}else if(entrada.charAt(2) == 'W') {
			escreva(Integer.parseInt(entrada.substring(0, 1)), Integer.parseInt(entrada.substring(4)));
		}else {
			System.out.println("Invalido");
		}
	}
	
	public void escreva(int endereco, int valor) {
		int enderecoFisico = 0;
		if (this.memVirtual.isReal(endereco)) {
			if (this.memVirtual.getEstadoPagina(endereco)) {
				enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			}else {
				//PEGA DO HD
			}
		}else {
			enderecoFisico = this.ram.getPosicaoLivre();
			//ENQUANTO NAO TIVER LIVRE PROCURE
			this.memVirtual.criarPagina(endereco, enderecoFisico, this.clock.getTempo());
		}
		this.ram.setValor(enderecoFisico, valor);
	}
	
	/*
	 * LE NA VIRTUAL, PEGA ENDERECO, SE TIVER
	 * LE/ALTERA/ESCREVE NA RAM NO ENDERECO, CASO O ENDERECO -1, A RAM PROCURA LUGAR LIVRE
	 * IF GETEREW == -1{ RAM.GETLIVRE
	 * CRIAR PAGINA(ENDERECO VIRTUAL, ENDERECO FISICO
	 * 
	 * */
	
	public void ler(int endereco) throws Exception{
		if (!this.memVirtual.isReal(endereco)) {
			throw new Exception("Acesso Inválido");
		}
		if (this.memVirtual.getEstadoPagina(endereco)) {
			int enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			int valor = this.ram.getValor(enderecoFisico);
			System.out.println(valor);
		}else {
			//PEGA NO HD
		}
	}
	
}
