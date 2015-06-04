package GeneticsPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Individual {
	private ArrayList<Integer> chromosome;
	private int fitness;

	public Individual(){
		this.chromosome = new ArrayList<Integer>();
	}
	
	/* Este metodo genera un arreglo de unos y ceros random, cada individuo
	 * tiene un cromosoma complatamente random cuando. Cada cromosoma es generada
	 * con respecto al tamaño del conjunto insertado. Un 1 en el arreglo representa una 
	 * actividad, la cual es representada como un par ordenado en el arreglo de actividades. Es decir 
	 * una activdad es asi: [7,9], el 7 representa su hora de comienzo mientras que el 9 representa 
	 * su hora de finalizacion. 
	 */
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
	
	/*Con este metodo se mide el fitness de cada individuo particular. 
	 * Este metodo toma el arreglo de posibles actividades, que esta ordenado antes de
	 * ser llamado por el metodo, es decir los pares ordenados dentro de array fueron previamente
	 * ordenados por un metodo llamado sortArray que esta en la clase Population. Una vez que eso ocurre
	 * se itera sobre array, a la misma vez iterando sobre el arraylist de cromosomas. Si se verifica, que 
	 * en el cromosoma hay un 1 entonces se sabe que este representa una actividad. Es decir si cromosoma[0] == 1 entonces
	 * representa array[0][0] la primera actividad, si hay un 0 en esa posicion del cromosoma es porque ese cromosoma
	 * en particular no tiene esa actividad en su representacion.
	 * 
	 * Finalmente, despues de que se verifique que la actividad es validad, entonces se chequea en el arreglo de actividades 
	 * para ver si alguna actividad choca, si chocan, entonces el fitness es 0 sino se le suma al contador. Cada actividad que exista 
	 * que no choque con otra actividad se le suma como un 1 al contador, el cual finalmente se le asigna al fitness. 
	 */
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
	
	/* Esto se utiliza en el metodo de arriba para crear un rango, 
	 * usualmente un rango que consiste de la hora de comienzo y finalizacion, para 
	 * poder chequear si alguna actividad cae dentro de ese rango, si se encuentra
	 * que cae ahi entonces en el metodo anterior se toma como un conflicto entre actividades.
	 */
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
