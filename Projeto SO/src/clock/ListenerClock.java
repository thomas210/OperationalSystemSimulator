package clock;
import memoria.MemoriaVirtual;

public class ListenerClock implements InterfaceClock {
	
	MemoriaVirtual memVirtual;
	
	public ListenerClock(MemoriaVirtual memoriaVirtual) {
		this.memVirtual = memoriaVirtual;
	}

	public void notificar(long tempoDeClock) {
		System.out.println("\n");
		memVirtual.setTempoAtualDeClock(tempoDeClock);
		memVirtual.zerarReferencia();
	}

}
