package palindromes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {

	int decPal = 0;
	int binPal = 0;
	int both = 0;
	int i = 0;
	long average = 0;
	int j = 0;

	public Test() {

		try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {

			StringBuilder sb = new StringBuilder();
			sb.append("Algorithm");
			sb.append(',');
			sb.append("Time: 1");
			sb.append(',');
			sb.append("Count: 1");
			sb.append(',');
			sb.append("Time: 10");
			sb.append(',');
			sb.append("Count: 10");
			sb.append(',');
			sb.append("Time: 100");
			sb.append(',');
			sb.append("Count: 100");
			sb.append(',');
			sb.append("Time: 1000");
			sb.append(',');
			sb.append("Count: 1000");
			sb.append(',');
			sb.append("Time: 10000");
			sb.append(',');
			sb.append("Count: 10000");
			sb.append(',');
			sb.append("Time: 100000");
			sb.append(',');
			sb.append("Count: 100000");
			sb.append(',');
			sb.append("Time: 1000000");
			sb.append(',');
			sb.append("Count: 1000000");
			sb.append(',');
			sb.append("Decimal Palidromes");
			sb.append(',');
			sb.append("Binary Palidromes");
			sb.append(',');
			sb.append("Both Bin and Dec Palidromes");
			sb.append('\n');
			
			sb.append("Compare Check");
			sb.append(',');
			
			for (j=1; j<1000001; j=(i*10)) {

				// System.out.println(j);

				long start = 0;
				long finish = 0;
				long total = 0;
				average = 0;
				decPal = 0;
				binPal = 0;
				both = 0;
				Palidrome.compareCheckCount=0;
				

				for (i = 0; i < j; i++) {
					boolean palidromeDec = false;
					boolean palidromeBin = false;
					String a = Integer.toString(i);
					String b = Palidrome.decToBinary(Palidrome.myAtoi(a));
					
					start = System.nanoTime();
					
					palidromeDec = Palidrome.compareCheck(a);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					//System.out.println("\n"+Palidrome.compareCheckCount);
					
					start = System.nanoTime();
					
					palidromeBin = Palidrome.compareCheck(b);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					
					if (palidromeDec) {
						decPal++;
					}
					if (palidromeBin) {
						binPal++;
					}
					if ((palidromeDec) && (palidromeBin)) {
						both++;
					}
				}
				
				sb.append(average);
				sb.append(',');
				sb.append(Palidrome.compareCheckCount);
				sb.append(',');
				System.out.println(" j = "+ j + "    Average : " + average);
			}
			
			sb.append(decPal);
			sb.append(',');
			sb.append(binPal);
			sb.append(',');
			sb.append(both);
			sb.append('\n');

			System.out.println("/********** Compare Check *************/");
			System.out.println("Total Decimal Palidromes: " + decPal);
			System.out.println("Total Binary Palidromes: " + binPal);
			System.out.println("Total Both Palidromes: " + both);
			System.out.println("Average time taken CompareCheck: " + (average / (j*2)) + "nanoseconds");
			
			sb.append("Stack Check");
			sb.append(',');
			
			for (j=1; j<1000001; j=(i*10)) {

				// System.out.println(j);

				long start = 0;
				long finish = 0;
				long total = 0;
				average = 0;
				decPal = 0;
				binPal = 0;
				both = 0;
				Palidrome.stackCheckCount=0;
				

				for (i = 0; i < j; i++) {
					boolean palidromeDec = false;
					boolean palidromeBin = false;
					String a = Integer.toString(i);
					String b = Palidrome.decToBinary(Palidrome.myAtoi(a));
					
					start = System.nanoTime();
					
					palidromeDec = Palidrome.stackCheck(a);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					//System.out.println("\n"+Palidrome.stackCheckCount);
					
					start = System.nanoTime();
					
					palidromeBin = Palidrome.stackCheck(b);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					
					if (palidromeDec) {
						decPal++;
					}
					if (palidromeBin) {
						binPal++;
					}
					if ((palidromeDec) && (palidromeBin)) {
						both++;
					}
				}
				
				sb.append(average);
				sb.append(',');
				sb.append(Palidrome.stackCheckCount);
				sb.append(',');
				System.out.println(" j = "+ j + "    Average : " + average);
			}
			
			sb.append(decPal);
			sb.append(',');
			sb.append(binPal);
			sb.append(',');
			sb.append(both);
			sb.append('\n');

			System.out.println("/********** Stack Check *************/");
			System.out.println("Total Decimal Palidromes: " + decPal);
			System.out.println("Total Binary Palidromes: " + binPal);
			System.out.println("Total Both Palidromes: " + both);
			System.out.println("Average time taken stackCheck: " + (average / (j*2)) + "nanoseconds");
			
			sb.append("Reverse Check");
			sb.append(',');
			
			for (j=1; j<1000001; j=(i*10)) {

				// System.out.println(j);

				long start = 0;
				long finish = 0;
				long total = 0;
				average = 0;
				decPal = 0;
				binPal = 0;
				both = 0;
				Palidrome.reverseCheckCount=0;
				

				for (i = 0; i < j; i++) {
					boolean palidromeDec = false;
					boolean palidromeBin = false;
					String a = Integer.toString(i);
					String b = Palidrome.decToBinary(Palidrome.myAtoi(a));
					
					start = System.nanoTime();
					
					palidromeDec = Palidrome.reverseCheck(a);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					//System.out.println("\n"+Palidrome.reverseCheckCount);
					
					start = System.nanoTime();
					
					palidromeBin = Palidrome.reverseCheck(b);
					
					finish = System.nanoTime();
					total = finish - start;
					average += total;
					
					if (palidromeDec) {
						decPal++;
					}
					if (palidromeBin) {
						binPal++;
					}
					if ((palidromeDec) && (palidromeBin)) {
						both++;
					}
				}
				
				sb.append(average);
				sb.append(',');
				sb.append(Palidrome.reverseCheckCount);
				sb.append(',');
				System.out.println(" j = "+ j + "    Average : " + average);
			}
			
			sb.append(decPal);
			sb.append(',');
			sb.append(binPal);
			sb.append(',');
			sb.append(both);
			sb.append('\n');

			System.out.println("/********** Reverse Check *************/");
			System.out.println("Total Decimal Palidromes: " + decPal);
			System.out.println("Total Binary Palidromes: " + binPal);
			System.out.println("Total Both Palidromes: " + both);
			System.out.println("Average time taken reverseCheck: " + (average / (j*2)) + "nanoseconds");
			
			writer.write(sb.toString());
			System.out.println("DONE");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}// end constructor

}
