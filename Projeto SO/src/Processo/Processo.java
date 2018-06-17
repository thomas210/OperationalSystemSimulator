package Processo;

import so.MMU;

public class Processo extends Thread{
	
	private static MMU mmu;
	private String instrucoes;
	
	public Processo(MMU mmu, String instrucoes) {
		this.mmu = mmu;
		this.instrucoes = instrucoes;
	}
	
	
	public void run(){
		
		for(String var : this.instrucoes.split(",")) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			sendMMU(var);
			
		}
	}
	
	public static void sendMMU(String entrada) {
		if (entrada.charAt(2) == 'R') {
			try{
				mmu.ler(Integer.parseInt(entrada.substring(0, 1)));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}else if(entrada.charAt(2) == 'W') {
			mmu.escreva(Integer.parseInt(entrada.substring(0, 1)), Integer.parseInt(entrada.substring(4)));
		}else {
			System.out.println("Invalido");
		}
	}
}
