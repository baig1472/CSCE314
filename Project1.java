
public class Project1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Instantiate Primes Class
		Primes p = new Primes();
		
		// Generate Primes and test.
		p.generatePrimes(79);
		//
		
		p.printPrimes(); 
		
		// Generate and test Twin Primes
		Primes testtwo = new Primes();
		testtwo.generatePrimes(100);
		 
		testtwo.generateTwinPrimes();
		testtwo.printPrimes();
		testtwo.printTwins();
		
		// Generate and test Hexagonal crosses
		Primes testThree = new Primes();
		//
		
		
		testThree.generatePrimes(14000);
		testThree.generateTwinPrimes();
		testThree.printTwins();
		testThree.generateHexPrimes();
		testThree.printHexes();
	}

}
