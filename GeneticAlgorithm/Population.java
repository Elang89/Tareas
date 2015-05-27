package GeneticsPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Population{
	private ArrayList<Individual> listOfIndividuals;
	private int [][] candidateSet;
	private int crossoverValue;
	private double totalFitness;
	private double mutationProbability;
	private int chromosome;
	
	public Population(int[][] candidateSet){
		listOfIndividuals = new ArrayList<Individual>();
		this.candidateSet = sortArray(candidateSet);
		crossoverValue = 3;
		mutationProbability = 0.05;
	}
	
	public int[][] sortArray(int [][] array){
		Arrays.sort(array, new Comparator<int[]>(){
			public int compare(int[] a, int [] b){
				return Integer.compare(a[0],b[0]);
			}
		});
		return array;
	}
	
	public void generatePopulation(int populationSize,int chromosomeSize, boolean option){
		chromosome = chromosomeSize;
		if (option){
			for (int i = 0; i < populationSize; i++){
				Individual individual = new Individual();
				individual.generateChromosome(chromosomeSize,true);
				individual.calculateFitness(candidateSet);
				listOfIndividuals.add(individual);	
			}
		} else {
			ArrayList<Individual> newListOfIndividuals = new ArrayList<Individual>();
			Individual individual1;
			Individual individual2;
			Individual finalIndividual;
			for(int i = 0; i < populationSize; i++){
				individual1 = selectionFunction();
				individual2 = selectionFunction();
				finalIndividual = crossIndividuals(individual1.getChromosome(), individual2.getChromosome());
				finalIndividual.calculateFitness(candidateSet);
				newListOfIndividuals.add(finalIndividual);
			}
			listOfIndividuals = newListOfIndividuals;
		}
		calculateTotalFitness();
	}
	
	public boolean validatePopulation(){
		if(listOfIndividuals.size() == 1){
			return true;
		}
		return false;
	}
	
	public Individual crossIndividuals(ArrayList<Integer> firstIndividual, ArrayList<Integer> secondIndividual){
		Random random = new Random();
		int gene;
		int number = random.nextInt(firstIndividual.size()-1);
		ArrayList<Integer> newChromosome = new ArrayList<Integer>();
		Individual individual = new Individual();
		for(int i = 0; i < crossoverValue; i++){
			newChromosome.add(firstIndividual.get(i));
		}
		
		for(int i = crossoverValue; i < chromosome; i++){
			newChromosome.add(secondIndividual.get(i));
		}
		
		
		
		
		individual.setChromosome(newChromosome);
		return individual;
		
	}
	
	public Individual selectionFunction(){
		ArrayList<Individual> selection = new ArrayList<Individual>();
		Random random = new Random();
		Individual individual;
		int number;
		int size = 5;
		
		for(int i = 0; i < size; i++){
			number = random.nextInt(listOfIndividuals.size());
			selection.add(listOfIndividuals.get(number));
		}
		individual = selectBestFit(selection);
		return individual;
	}
	
	public Individual returnBestCandidate(){
		Individual individual = listOfIndividuals.get(0);
		for(int i = 0; i < listOfIndividuals.size(); i++){
			if(listOfIndividuals.get(i).getFitness() > individual.getFitness()){
				individual = listOfIndividuals.get(i);
			}
		}
		return individual;
		
	}
	
	public Individual selectBestFit(ArrayList<Individual> selection){
		Individual individual;
		individual = selection.get(0);
		for(int i = 0; i < selection.size(); i++){
			if(selection.get(i).getFitness() > individual.getFitness()){
				individual = selection.get(i);
			}
		}
		return individual;
		
		
	}
	
	
	public void calculateTotalFitness(){
		for (int i = 0; i < listOfIndividuals.size(); i++){
			totalFitness += listOfIndividuals.get(i).getFitness();
		}
	}
	
	public ArrayList<Individual> getListOfIndividuals() {
		return listOfIndividuals;
	}

	public int getCrossoverValue() {
		return crossoverValue;
	}

	public double getMutationProbability() {
		return mutationProbability;
	}
	
	public int[][] getCandidateSet(){
		return candidateSet;
	}
	
	public double getTotalFitness(){
		return totalFitness;
	}
	
}
