package Data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
// This file gives access to the underlying datafile and stores the data in the Workout class.

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import Data.Primes.IterableCrosses;
import Data.Primes.IterablePrimes;

public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) {
	  
	  boolean successful = true;
	  
	  // clear the prime list first:
	  primes.clearPrimes();
	  // now add each of the primes to the list 
	  
	  // reading the content of file
	  try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
	  {
		  	int i = 0;
		  	String line;
		    while((line = bufferedReader.readLine()) != null) {
		        // converting to integer
		        i = Integer.parseInt(line);
		        primes.addPrime(BigInteger.valueOf(i));
		        System.out.println(i);
		       
		    }
		    MainWindow.primeCount = i;
		    // remove last element from primeList as it is total count of primes only if it is not a prime, 
		    // otherwise it won't 
		    if(primes.addCondition(BigInteger.valueOf(i))) 
		    	primes.removeLast();
	  } 
	  catch (FileNotFoundException e) 
	  {
		  MainWindow.status = "File Not Found Exception";
		  successful = false;
	  } catch (IOException e) {
		  MainWindow.status = "Input Output Exception";
		  successful = false;
	  }
	  
		return successful;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  boolean successful = true;
		  
		  // clear the crosses list first:
		  primes.clearCrosses();
		  // now add each of the primes to the list 
		  
		  // reading the content of file
		  try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename)))
		  {
			  	int i = 0;
			  	String line;
			  	int left = 0, right = 0;
			  	BigInteger bi = BigInteger.valueOf(0);
			  	
			    while((line = bufferedReader.readLine()) != null) {
			    	// converting to integer
			    	String[] values = line.split(",");
			    	if(values.length==2)
			    	{
			    		Pair<BigInteger> p = new Pair<BigInteger>(bi,BigInteger.valueOf(0));
			    		left = Integer.parseInt(values[0]);
				    	right = Integer.parseInt(values[1]);
				    	p.setLeft(BigInteger.valueOf(left));
				    	p.setRight(BigInteger.valueOf(right));
				    	primes.addCross(p);
				    	//p.print();
				    	System.out.println(left + ", " + right);
			    	}
			    	else
			    		i = Integer.parseInt(values[0]);
			    }
			    MainWindow.crossCount = i;
			    
		  } 
		  catch (FileNotFoundException e) 
		  {
			  MainWindow.status = "File Not Found Exception";
			  successful = false;
		  } catch (IOException e) {
			  MainWindow.status = "Input Output Exception";
			  successful = false;
		  }
		  
			return successful;
  }
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	  boolean successful = true;
	  // write the content in file 
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
			 
			IterablePrimes ip = primes.iteratePrimes();
			for(BigInteger bi: primes.iteratePrimes()) {
				System.out.println(bi);
				String fileContent = "" + bi + "\n";
				 bufferedWriter.write(fileContent);
			}
			String fileContent = "" + primes.primeCount();
			bufferedWriter.write(fileContent);
			
		} catch (IOException e) {
			MainWindow.status = "Input Output Exception";
			successful = false;
		}
		
	  return successful;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
	  boolean successful = true;
	  // write the content in file 
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
			 
			IterableCrosses ip = primes.iterateCrosses();
			for(Pair<BigInteger> bi: primes.iterateCrosses()) {
				System.out.println(bi.left() + ", " + bi.right());
				String fileContent = "" + bi.left() + "," + bi.right() + "\n";
				 bufferedWriter.write(fileContent);
			}
			String fileContent = "" + primes.crossesCount();
			bufferedWriter.write(fileContent);
			
		} catch (IOException e) {
			MainWindow.status = "Input Output Exception";
			successful = false;
		}
		
	  return successful;
  }
}