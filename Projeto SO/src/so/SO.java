package so;

import Processo.Processo;

public class SO {
	
	public static MMU mmu = new MMU();
	
	public static void main(String[] args) {
				
		String s1 = new String("3-W-6,0-W-0,1-W-1,2-W-2,8-R,3-W-3,4-W-4,5-W-5,6-W-6,7-W-7,8-W-8,9-W-9,5-W-10,5-R,3-R,8-R");
		String s2 = new String("1-W-12,8-R,8-W-0,7-W-1,5-W-2,8-R,3-W-3,0-W-4,5-W-5,6-W-6,7-W-7,8-W-8,9-W-9,5-W-10,5-R,3-R,8-R");
		
		Processo processo1 = new Processo(mmu,s1);
		Processo processo2 = new Processo(mmu,s2);
		processo1.start();
		processo2.start();
		
	}
	
}
