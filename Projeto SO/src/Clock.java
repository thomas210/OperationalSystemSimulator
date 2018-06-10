
public class Clock extends Thread {

	private long tempoVirtual;
	private ListenerClock listener;
	
	
	public Clock(ListenerClock listener) {
		this.tempoVirtual = 0l;
		this.listener = listener;
	}
	
	public void run() {
	
		while(true) {
			
			try {
				
				sleep(10);
				this.tempoVirtual++;
				listener.notificar(tempoVirtual);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long getTempo() {
		return this.tempoVirtual;
	}
	
}
