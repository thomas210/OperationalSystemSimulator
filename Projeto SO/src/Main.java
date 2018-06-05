import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		SistemaOperacional so = new SistemaOperacional();
		
		//new Clock().start();
		//so.tratamento("3-W-6");
		so.tratamento("5-R");
		so.tratamento("5-W-10");
		so.tratamento("8-W-15");
		so.tratamento("5-R");
		so.tratamento("8-R");
	}
}
