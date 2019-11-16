//Name:Hassan Baig
//
//
//
//
//https://github.com/baig1405/csce314.git





import java.util.ArrayList; 
import java.math.BigInteger;


public class Primes {
	// Pair class implementation.
	
 
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
		private class Pair<T> {
			int size=10000000;	
		    // Big Integer array for Prime Numbers
		    BigInteger[] arr_prime=new BigInteger[size];
		    int count1=0;
		    
		    // Big Integer array for Twin Prime Numbers
		    BigInteger[] arr_twinprime=new BigInteger[size];
		    int count2=0;
		    
		    // // Big Integer array for Hex Prime Numbers
		    BigInteger[] arr_hexprime=new BigInteger[size];
		    BigInteger[] arr_storeprime=new BigInteger[size];
		    int count3=0;
		    
		    int hexcount=0;
		    
		    
		    //Hex pairs separated 
		    BigInteger[] sep1=new BigInteger[size];
		    BigInteger[] sep2=new BigInteger[size];
		    int sep1count=0;
		    int sep2count=0;
		    
		    
		    int length;
		    
		    //for numbers of twin prime
		    int counttwinprime;
		}
		
		
		
		// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
		public void addPrime(BigInteger x)
		{
			
			int y=0;
			
			for(int x=0; count1>y; y++)
		 
			{
				if (arr_prime[y]=x)
			   System.out.println("Already int he list");
				else
			     arr_prime[y]=x
			y++;
			}
			
		}
		
		
		
		
		// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
		public void printPrimes()
		{ 
			int y=0;
			System.out.println("Printing Prime Numbers Only");
			//Count1 is the size of the arr_prime
		   while(count1>y)
			{
			   System.out.println(arr_prime[y]);
			y++;
			}
		   System.out.print("Total Primes:");
		   System.out.println(y+1);
		}
			
		// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
		public void printTwins()
		{  
			String comma=",";
			int y=0;
			System.out.println("Printing Twin Prime Numbers");
			   while(count2>y)
				{
				   System.out.print(arr_twinprime[y]+comma);
				  System.out.print(arr_twinprime[y+1]);
				  System.out.println();
				  y=y+2;
				}
			   System.out.print("Total Twin Primes:");
			   counttwinprime=y/2;
			   System.out.println(y/2);
		}
			
		// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
		public void printHexes()
		{
			
			String comma=",";
			int y=0;
			System.out.println("Printing Hex Prime Numbers");
			int i=0;
		
			   while(hexcount>y)
				{
				  
				   System.out.print(arr_hexprime[y]+comma);
				  System.out.print(arr_hexprime[y+1]);
				  
				  System.out.print(" and ");
				  System.out.print(arr_hexprime[y+2]+comma);
				  System.out.print(arr_hexprime[y+3]);
				  
				  
				  System.out.print(" seperated by ");
				  System.out.print(sep1[i]+comma);
				  System.out.print(sep2[i]);
				i++;
				  System.out.println();
				  y=y+4;
				  
				}
			   
			   System.out.print("Total Hexes");  
			   System.out.println(hexcount/4);  
		}
			
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		// Generate and store a list of primes.
		public void generatePrimes(int count)
		{
			length=count;
			int i=0;
			int num=0;
			
			
			// Generating prime numbers between 1 to given count
			for(i=1; i<=count; i++)
			{
				int y=0;
                 
				for (num=i; num>=1; num--)
				{
					
					if(i%num==0)
					{
						y=y+1;
						
					}
					
				}
				
				if (y==2)
				{
					
					arr_prime[count1]=BigInteger.valueOf(i);
					 count1++;
					 
					
				}
			
			}
		
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		// Generate and store a list of twin primes.
		public void generateTwinPrimes()
		{
			for (int i = 2; i <length; i++) {
                         
				
				//Condition to check if the number and the +2 index number is prime or not
	            if (twin_Prime(i) && twin_Prime(i + 2)) {
	            	
	            	arr_twinprime[count2]=BigInteger.valueOf(i);
	            	//System.out.println(arr[x]);
	            	count2++;
	            	
	            arr_twinprime[count2]=BigInteger.valueOf(i+2);
	           // System.out.println(arr[x]);
	            	count2++;
	            	
	                //System.out.printf("(%d, %d)\n", i, i + 2);
	            }
	        }
	    
		}
		
		
		//Bollean function just to check if the number index i and i+2 is a prime
	    public static boolean twin_Prime(long n) {

	        if (n < 2) return false;

	        for (int i = 2; i <= n / 2; i++) {

	            if (n % i == 0) return false;
	        }
	        return true;
	    }
			
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

// Generating Hex Primes	    
	    
	    
		// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
		public void generateHexPrimes()
		{
			
			//sTEP1: Making twin prime numbers array
			for (int i = 2; i <length; i++) {
                
	            if (twin_Prime(i) && twin_Prime(i + 2)) {
	            	
	            	arr_storeprime[count3]=BigInteger.valueOf(i);
	            	
	            	count3++;
	            	
	            arr_storeprime[count3]=BigInteger.valueOf(i+2);
	           
	            	count3++;
	            	
	                
	            }
	        }
			
			
			//Step2: 
			//Loop1: First get 1sthex 
			for(int x=2; x<counttwinprime*2-2; x=x+2)
			{
				   BigInteger hex1=arr_storeprime[x];
				   
				   hex1=hex1.add(new BigInteger("1"));
				   sep1[sep1count]=hex1;
				 
				   
				   hex1=hex1.multiply(new BigInteger("2"));
				   
				   
				   for(int y=4; y<counttwinprime*2-4; y=y+2)
				   {
				   
                 BigInteger hex2=arr_storeprime[y];
				   
				   hex2=hex2.add(new BigInteger("1"));
				   
				   
				   int compare;
				   
				   compare=hex1.compareTo(hex2);
				   
				   if(compare==0)
				   {
					   
					   arr_hexprime[hexcount]=arr_storeprime[x];
					   hexcount++;
					   arr_hexprime[hexcount]=arr_storeprime[x+1];
					   hexcount++;
					   
					   arr_hexprime[hexcount]=arr_storeprime[y];
					   hexcount++;
					   
					   arr_hexprime[hexcount]=arr_storeprime[y+1];
					   hexcount++;
					   
					  
						 sep1count++;
						 
						 sep2[sep2count]=hex2;
						   sep2count++;
					   
					   //System.out.println(arr_twinprime[x]);
						//System.out.println(arr_twinprime[x+1]);
						 //System.out.println(arr_twinprime[y]);
							//System.out.println(arr_twinprime[y+1]);
					   
							//System.out.println(sep1);
							//System.out.println(sep2);
							
							
					   
				   }
			
			}
			
	//		
		
}
}
}		
	   
