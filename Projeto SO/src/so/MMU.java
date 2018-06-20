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
		if (this.memVirtual.isReal(endereco)) { //EXISTE UMA PAGINA?
			if (this.memVirtual.getEstadoPagina(endereco)) { //TÁ NA MEMORIA RAM?
				enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
				this.memVirtual.EnderecoReferenciado(endereco);
			}else {											//TÁ NO HD!
				enderecoFisico = this.transHDtoMEM(endereco);
			}
		}else {									//SE NÃO EXISTE, CRIE!
			enderecoFisico = this.getEnderecoFisico(); 
			this.memVirtual.criarPagina(endereco, enderecoFisico);
		}
		this.memRam.setValor(enderecoFisico, valor);
		System.out.println("Write " + endereco + " = " + valor);
	}
	

	
	public void ler(int endereco) throws Exception{
		int enderecoFisico = -1;
		int valor = -1;
		
		if (!this.memVirtual.isReal(endereco)) { //EXISTE ESSA PAGINA?
			throw new Exception("Acesso Inválido de leitura");
		}
		if (this.memVirtual.getEstadoPagina(endereco)) { //OBTENHO A PAGINA SE ELA TÁ NA MEMORIA RAM
			enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
			this.memVirtual.EnderecoReferenciado(endereco);
			valor = this.memRam.getValor(enderecoFisico);
		}else {											//TÁ NO HD
			enderecoFisico = this.transHDtoMEM(endereco);
			this.memVirtual.EnderecoReferenciado(endereco);
			valor = this.memRam.getValor(enderecoFisico);
		}
		
		System.out.println("Read " + endereco + " = " + valor);
	}
	
	
	public synchronized int getEnderecoFisico() {
		int endereco = -1;
			while(true) {
				endereco = this.memRam.getPosicaoLivre(); //SE NÃO TIVER ESPAÇO -> RETURN -1
				if(endereco == -1) {
					this.algoritmoWS(); //WORKING SET
				}else {
					break;
				}
			}
		System.out.println("GET NEW ADDRESS RAM " + endereco);
		return endereco;
	}
	
	
	public void algoritmoWS() {
		
		int endereco = ws.exec(memVirtual);					 //CHAMA O ALGORITMO
		int enderecoFisico = this.memVirtual.getEnderecoFisico(endereco);
		int valor = this.memRam.getValor(enderecoFisico);
		this.hd.setDiscoRigido(endereco, valor); 			//GRAVA EM DISCO
		this.memVirtual.setEstadoPagina(endereco,false);
		this.memRam.setNull(enderecoFisico);				//LIBERA MEMORIA RAM
	}
	
	
	public int transHDtoMEM(int endereco) { //TRANSFERE DO HD PARA AS MEMORIAS
		System.out.println("HD TO MEMORY TRANSFER");
		int valorDoHD = this.hd.getDiscoRigido(endereco);
		int enderecoFisico = this.getEnderecoFisico();
		this.memVirtual.criarPagina(endereco, enderecoFisico);
		this.memRam.setValor(enderecoFisico, valorDoHD);
		return enderecoFisico;
	}

	
	
}
