package Data;

import java.math.BigInteger;

public class FinalProduct {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Primes p = new Primes();
		
		//p.generatePrimes(BigInteger.valueOf(11), 15);
	
		
		//FileAccess.loadPrimes(p,"primes.txt");
		//p.printPrimes();
		//FileAccess.savePrimes(p,"primes.txt");
		
		//FileAccess.loadCrosses(p,"crosses.txt");
		//p.printPrimes();
		//FileAccess.saveCrosses(p,"crosses.txt");
		
		MainWindow mw = new MainWindow(Config.APPLICATIONNAME, p);
	}
	
}
