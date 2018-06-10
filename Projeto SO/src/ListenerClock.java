
public class ListenerClock implements InterfaceClock {
	
	MemoriaVirtual memVirtual;
	
	public ListenerClock(MemoriaVirtual memoriaVirtual) {
		this.memVirtual = memoriaVirtual;
	}

	public void notificar(long tempoDeClock) {
		memVirtual.setTempoAtualDeClock(tempoDeClock);
		memVirtual.zerarReferencia();
	}

}
