
public class Exposure {
	public int cellNum;
	public float time;
	public float meanIntensity;
	public float px;
	public float py;
	
	public Exposure(){};
	
	public Exposure(int cellNum, float time, float meanIntensity, float px, float py){
		this.cellNum = cellNum;
		this.time = time;
		this.meanIntensity = meanIntensity;
		this.px = px;
		this.py = py;
	}
	
	public String toString(){
		return("Cell Number: " + cellNum + "\nTime: " + time + "\nMeanIntensity: " + meanIntensity + "\n");
	}
	
}
