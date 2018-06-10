import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		SistemaOperacional so = new SistemaOperacional();
		//new Clock().start();
		//so.tratamento("3-W-6");
		so.tratamento("5-R");
		so.tratamento("5-W-10");
		for(long i = 10000000000l; i > 0; --i) {}
		so.tratamento("5-R");
		so.tratamento("5-W-15");
		so.tratamento("5-R");
		
		
	}
}
