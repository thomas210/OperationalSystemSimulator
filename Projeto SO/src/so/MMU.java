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
	
	public MMU(int _disco, int _memoriaVirtual, int _memoriaPrincipal, int _limiteWorkingSet) {
		this.hd = new DiscoRigido(_disco);
		this.memVirtual = new MemoriaVirtual(_memoriaVirtual);
		this.memRam = new MemoriaPrincipal(_memoriaPrincipal);
		this.ws = new WorkingSet(_limiteWorkingSet);
		this.listener = new ListenerClock(memVirtual);
		new Clock(listener).start();

	}
	
	
	
	public synchronized void escreva(int endereco, int valor) {
		int enderecoFisico = 0;
		if (this.memVirtual.isReal(endereco)) {
			if (this.memVirtual.getEstadoPagina(endereco)) {
				enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
				this.memVirtual.EnderecoReferenciado(endereco);
			}else {
				enderecoFisico = this.PegarDoHD(endereco);
				//this.escreva(endereco, valor);
			}
		}else {
			enderecoFisico = this.getEnderecoFisico(); 
			this.memVirtual.criarPagina(endereco, enderecoFisico);
		}
		this.memRam.setValor(enderecoFisico, valor);
		System.out.println("Write " + endereco + " = " + valor);
	}
	

	
	public void ler(int endereco) throws Exception{
		int enderecoFisico = -1;
		int valor = -1;
		
		if (!this.memVirtual.isReal(endereco)) {
			throw new Exception("Acesso Inválido de leitura");
		}
		if (this.memVirtual.getEstadoPagina(endereco)) {
			enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			this.memVirtual.EnderecoReferenciado(endereco);
			valor = this.memRam.getValor(enderecoFisico);
		}else {
			enderecoFisico = this.PegarDoHD(endereco);
			this.memVirtual.EnderecoReferenciado(endereco);
			valor = this.memRam.getValor(enderecoFisico);
			//this.ler(endereco);
		}
		
		System.out.println("Read " + endereco + " = " + valor);
	}
	
	
	public synchronized int getEnderecoFisico() {
		int endereco = -1;
			while(true) {
				endereco = this.memRam.getPosicaoLivre();
				if(endereco == -1) {
					this.vaiSafadao();
				}else {
					break;
				}
			}
		System.out.println("GET NEW ADDRESS RAM " + endereco);
		return endereco;
	}
	
	
	public void vaiSafadao() {
		
		int endereco = ws.exec(memVirtual);
		int enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
		int valor = this.memRam.getValor(enderecoFisico);
		this.hd.setDiscoRigido(endereco, valor);
		this.memVirtual.setEstadoPagina(endereco,false);
		this.memRam.setNull(enderecoFisico);
	}
	
	
	public int PegarDoHD(int endereco) {
		int valorDoHD = this.hd.getDiscoRigido(endereco);
		int enderecoFisico = this.getEnderecoFisico();
		this.memVirtual.criarPagina(endereco, enderecoFisico);
		this.memRam.setValor(enderecoFisico, valorDoHD);
		return enderecoFisico;
	}

	
	
}
