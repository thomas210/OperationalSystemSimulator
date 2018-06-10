import java.util.ArrayList;

public class SistemaOperacional {
	
	private DiscoRigido hd;
	private MemoriaVirtual memVirtual;
	private MemoriaPrincipal memRam;
	private ListenerClock listener;
	private WorkingSet ws;
	
	public SistemaOperacional() {
		this.hd = new DiscoRigido(10);
		this.memVirtual = new MemoriaVirtual(10);
		this.memRam = new MemoriaPrincipal(5);
		this.listener = new ListenerClock(memVirtual);
		new Clock(listener).start();
		this.ws = new WorkingSet(5);
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
			}
		}else {
			enderecoFisico = this.memRam.getPosicaoLivre(); //ENQUANTO NAO TIVER LIVRE PROCURE
			this.memVirtual.criarPagina(endereco, enderecoFisico);
		}
		this.memRam.setValor(enderecoFisico, valor);
	}
	
	/*
	 * LE NA VIRTUAL, PEGA ENDERECO, SE TIVER
	 * LE/ALTERA/ESCREVE NA memRam NO ENDERECO, CASO O ENDERECO -1, A memRam PROCURA LUGAR LIVRE
	 * IF GETEREW == -1{ memRam.GETLIVRE
	 * CRIAR PAGINA(ENDERECO VIRTUAL, ENDERECO FISICO
	 * 
	 * */
	
	public void ler(int endereco) throws Exception{
		if (!this.memVirtual.isReal(endereco)) {
			throw new Exception("Acesso Inválido");
		}
		if (this.memVirtual.getEstadoPagina(endereco)) {
			int enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			int valor = this.memRam.getValor(enderecoFisico);
			System.out.println(valor);
		}else {
			this.PegarDoHD(endereco);
			this.ler(endereco);
		}
	}
	
	public void vaiSafadao() {
		
		int endereco = ws.exec(memVirtual);
		int valor = this.memRam.getValor(memVirtual.getEnderecoFisico(endereco));
		this.hd.setDiscoRigido(endereco, valor);
		this.memVirtual.setEstadoPagina(endereco,false);
	}
	
	public void PegarDoHD(int endereco) {
		int valorDoHD = this.hd.getDiscoRigido(endereco);
		int enderecoFisico = this.memRam.getPosicaoLivre();
		this.memVirtual.criarPagina(endereco, enderecoFisico);
	}

	
	
}
