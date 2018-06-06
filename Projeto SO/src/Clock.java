
public class Clock extends Thread {

	private long tempoVirtual;
	private SistemaOperacional listener;
	
	public Clock() {
		this.tempoVirtual = 0l;
	}
	
	public void run() {
	
		while(true) {
			
			try {
				
				sleep(1000);
				this.tempoVirtual++;
				listener.notificar();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long getTempo() {
		return this.tempoVirtual;
	}
	
}
