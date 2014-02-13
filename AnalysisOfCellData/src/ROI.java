import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class ROI {
	public int ROINumber;
	public ArrayList<Float> times = new ArrayList<Float>();
	public ArrayList<Float> intensities = new ArrayList<Float>();
	
	public ROI(){};
	
	public ROI(int num){
		this.ROINumber = num;
	}
	
	public String toString(){
		String returnStr = "[" + this.ROINumber + " : ";
		for (int i = 0; i < times.size(); i++){
			returnStr = returnStr + "(" + times.get(i).toString() + ", " + intensities.get(i).toString() + "), ";
		}
		return (returnStr + "]");
	}

	public void addIntensity(float meanIntensity) {
		intensities.add(meanIntensity);
	}

	public void addTime(float time) {
		times.add(time);
	}
	
}
