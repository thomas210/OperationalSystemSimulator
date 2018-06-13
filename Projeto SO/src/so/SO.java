package so;

public class SO {
	
	public static MMU mmu = new MMU();
	
	public static void main(String[] args) {
				
		String s1 = new String("3-W-6,0-W-0,1-W-1,2-W-2,8-R,3-W-3,4-W-4,5-W-5,6-W-6,7-W-7,8-W-8,9-W-9,5-W-10,5-R,3-R,8-R");
		
		for(String var : s1.split(",")) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			SO.sendMMU(var);
			
		}
		
		
	}
	
	
	public static void sendMMU(String entrada) {
		if (entrada.charAt(2) == 'R') {
			try{
				SO.mmu.ler(Integer.parseInt(entrada.substring(0, 1)));
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}else if(entrada.charAt(2) == 'W') {
			SO.mmu.escreva(Integer.parseInt(entrada.substring(0, 1)), Integer.parseInt(entrada.substring(4)));
		}else {
			System.out.println("Invalido");
		}
	}
	
}
