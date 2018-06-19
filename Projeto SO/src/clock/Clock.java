package clock;

public class Clock extends Thread {
	
	private static final int TIMESLEEP = 1000;
	private long tempoVirtual;
	private ListenerClock listener;
	
	
	public Clock(ListenerClock listener) {
		this.tempoVirtual = 0l;
		this.listener = listener;
	}
	
	public void run() {
	
		while(true) {
			
			try {
				
				sleep(TIMESLEEP);
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
