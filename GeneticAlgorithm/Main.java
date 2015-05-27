package GeneticsPackage;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Random random = new Random();
		int [][] array;
		int number;
		int defaultPopulationNumber = 20;
		int counter = 1;
		int desiredResult = 8;
		Scanner scanner = new Scanner(System.in);
		Individual individual = new Individual();
		Population population;
		
		System.out.println("Insert the number of activities to generate:");
		while (!scanner.hasNextInt()){
			System.out.println("Use a number:");
			scanner.next();
		}
		number = scanner.nextInt();
		array = new int[number][2];
		
		for(int i = 0; i < array.length; i++){
			array[i][0] = random.nextInt(23 - 1) + 1;
			array[i][1] = array[i][0] + random.nextInt(23 - array[i][0])+1;
		}
			
	
		population = new Population(array);
		population.generatePopulation(defaultPopulationNumber, number, true);

		if(population.validatePopulation()){
			individual = population.returnBestCandidate();
			System.out.println("The desired result was found in generation " + counter
					+ ",the number of activities which can be assisted to is " + individual.getFitness());
			System.out.println("The best individual was " + individual.getChromosome());
		}
		
		
		if(population.returnBestCandidate().getFitness() == desiredResult){
			individual = population.returnBestCandidate();
			System.out.println("The desired result was found in generation " + counter
					+ ",the number of activities which can be assisted to is " + individual.getFitness());
			System.out.println("The best individual was " + individual.getChromosome());
		} else {
			while(population.returnBestCandidate().getFitness() != desiredResult && counter < 20){
				population.generatePopulation(defaultPopulationNumber, number, false);
				individual = population.returnBestCandidate();
				System.out.println(counter);
				counter++;
			}
			System.out.println("The desired result was found in generation " + counter
					+ ",the number of activities which can be assisted to is " + individual.getFitness());
			System.out.println("The best individual was " + individual.getChromosome());
		}
		
	
	
	}

}
