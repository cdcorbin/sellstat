package ai;

import java.util.Map;
import java.util.TreeMap;
import play.Logger;

public class OccupantModel {

	private int distanceTolerance = 100;
	private Bucket[] trainingData;
	private TreeMap<Integer, double[]> treeMap = new TreeMap<Integer, double[]>();
	private TreeMap<Integer, Double> probabilities = new TreeMap<Integer, Double>();
	
	public void setTrainingData (Bucket[] data) {
		this.trainingData = data;
	}
	
	public TreeMap<Integer, Double> getProbabilities() {
		return probabilities;
	}

	public void train () {
		
		int key;
		
		for (int i = 0; i< trainingData.length; i++) {
			
			Bucket bucket = trainingData[i];
			key = (bucket.getDay()-1)*1440+bucket.getMinute();
			if (treeMap.containsKey(key)) {
				double[] temp = treeMap.get(key);
				temp[0] += bucket.getDistance()<distanceTolerance ? 1 : 0; 
				temp[1] += 1; 
			} else {
				treeMap.put(key, new double[]{bucket.getDistance()<distanceTolerance ? 1 : 0, 1});
			}

		}
		
		for (Map.Entry<Integer, double[]> entry : treeMap.entrySet()) {
			  key = entry.getKey();
			  double[] value = entry.getValue();
			  double probability = value[1] > 0 ? value[0]/value[1] : 0.0;
			  probabilities.put(key, probability);
			  //Logger.debug("Minute: " + key + "\tProbability: " + probability);
		}
		
	}
	
}
