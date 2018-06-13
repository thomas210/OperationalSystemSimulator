package so;
import clock.Clock;
import clock.ListenerClock;
import memoria.DiscoRigido;
import memoria.MemoriaPrincipal;
import memoria.MemoriaVirtual;

public class MMU {
	
	private DiscoRigido hd;
	private MemoriaVirtual memVirtual;
	private MemoriaPrincipal memRam;
	private ListenerClock listener;
	private WorkingSet ws;
	
	public MMU() {
		this.hd = new DiscoRigido(10);
		this.memVirtual = new MemoriaVirtual(10);
		this.memRam = new MemoriaPrincipal(5);
		this.ws = new WorkingSet(2);
		this.listener = new ListenerClock(memVirtual);
		new Clock(listener).start();

	}
	
	
	
	public void escreva(int endereco, int valor) {
		int enderecoFisico = 0;
		if (this.memVirtual.isReal(endereco)) {
			if (this.memVirtual.getEstadoPagina(endereco)) {
				enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			}else {
				this.PegarDoHD(endereco);
				this.escreva(endereco, valor);
			}
		}else {
			enderecoFisico = this.getEnderecoFisico(); 
			this.memVirtual.criarPagina(endereco, enderecoFisico);
		}
		this.memRam.setValor(enderecoFisico, valor);
		System.out.println("ESCREVER" + endereco + " = " + valor);
	}
	

	
	public void ler(int endereco) throws Exception{
		if (!this.memVirtual.isReal(endereco)) {
			throw new Exception("Acesso Inválido");
		}
		if (this.memVirtual.getEstadoPagina(endereco)) {
			int enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			this.memVirtual.EnderecoReferenciado(endereco);
			int valor = this.memRam.getValor(enderecoFisico);
			System.out.println("LER " + endereco + " = " + valor);
		}else {
			this.PegarDoHD(endereco);
			this.ler(endereco);
		}
	}
	
	
	public int getEnderecoFisico() {
		int endereco = -1;
			while(true) {
				endereco = this.memRam.getPosicaoLivre();
				if(endereco == -1) {
					this.vaiSafadao();
				}else {
					break;
				}
			}
		System.out.println("GET END RAM " + endereco);
		return endereco;
	}
	
	
	public void vaiSafadao() {
		
		int endereco = ws.exec(memVirtual);
		int valor = this.memRam.getValor(memVirtual.getEnderecoFisico(endereco));
		this.hd.setDiscoRigido(endereco, valor);
		this.memVirtual.setEstadoPagina(endereco,false);
		this.memRam.setNull(endereco);
	}
	
	
	public void PegarDoHD(int endereco) {
		int valorDoHD = this.hd.getDiscoRigido(endereco);
		int enderecoFisico = this.getEnderecoFisico();
		this.memVirtual.criarPagina(endereco, enderecoFisico);
		this.memRam.setValor(enderecoFisico, valorDoHD);
	}

	
	
}
