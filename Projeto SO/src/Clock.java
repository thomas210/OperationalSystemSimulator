
public class Clock extends Thread {

	long tempoVirtual;
	
	public Clock() {
		this.tempoVirtual = 1000l;
	}
	
	public void run() {
	
		while(true) {
			
			try {
				
				sleep(1000);
				if(this.tempoVirtual <= 9999l) {
					this.tempoVirtual++;
				}else {
					this.tempoVirtual = 1000l;
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public long getTempo() {
		return this.tempoVirtual;
	}
	
}
