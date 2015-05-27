package GeneticsPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Individual {
	private ArrayList<Integer> chromosome;
	private int fitness;
	
	/* Generates an individual based on the size of the solution
	 * set inserted through
	 */
	public Individual(){
		this.chromosome = new ArrayList<Integer>();
	}
	
	public void generateChromosome(int chromosomeSize, boolean option){
		int number;
		Random random = new Random();
		number = random.nextInt(5);
		if(option){
			for	(int i = 0; i < chromosomeSize/2; i++){
				int bit = 0;
				if(random.nextInt(5) == number){
					bit = 1;
				}
				chromosome.add(bit);
			}
			for	(int i = chromosomeSize/2; i < chromosomeSize; i++){
				int bit = 1;
				if(random.nextInt(5) == number){
					bit = 0;
				}
				chromosome.add(bit);
			}
			Collections.shuffle(chromosome);
		}
	}
	
	public void calculateFitness(int [][] array){
		ArrayList<Integer> range;
		int counter = 0;
		for(int i = 0; i < chromosome.size(); i++){
			if(chromosome.get(i) == 1){
				range = createRange(array[i][0],array[i][1]);
				if(!(i+1 >= chromosome.size()) && !range.contains(array[i+1][1])){
					counter++;
				} else {
					counter = 0;
				}
			}
		}
		this.fitness = counter;
	}
	

	private ArrayList<Integer> createRange(int start, int end){
		ArrayList<Integer> range = new ArrayList<Integer>();
		for (int i = 0; i < start; i++){
			range.add(i);
		}
		return range;
	}
	
	public void setChromosome(ArrayList<Integer> chromosome) {
		this.chromosome = chromosome;
	}

	public ArrayList<Integer> getChromosome() {
		return chromosome;
	}

	public int getFitness() {
		return fitness;
	}

}
