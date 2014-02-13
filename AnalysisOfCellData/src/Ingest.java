import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class Ingest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Read in each exposure, parse into ArrayList of Exposure objects
		ArrayList<Exposure> Exposures = new ArrayList<Exposure>();
		ArrayList<ROI> rois = new ArrayList<ROI>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/formatted_data.csv"));
			String line = null;
			ROI curROI = null;
			while ((line = reader.readLine()) != null){
				String[] s = line.split(",");
				
				if (curROI == null){
					curROI = new ROI(Integer.parseInt(s[0]));
				}
				
//				Exposures.add(new Exposure(
//						Integer.parseInt(s[0]),
//						Float.parseFloat(s[1]),
//						Float.parseFloat(s[2]),
//						Float.parseFloat(s[3]),
//						Float.parseFloat(s[4])));
				
				if (curROI.ROINumber == Integer.parseInt(s[0])){
					curROI.addIntensity(Float.parseFloat(s[2]));
					curROI.addTime(Float.parseFloat(s[1]));
				}else{
					rois.add(curROI);
					curROI = new ROI(Integer.parseInt(s[0]));
					curROI.addIntensity(Float.parseFloat(s[2]));
					curROI.addTime(Float.parseFloat(s[1]));
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		ArrayList<DescriptiveStatistics> roiStats = new ArrayList<DescriptiveStatistics>();
		for (ROI r: rois){
			DescriptiveStatistics ds = new DescriptiveStatistics();
			for(Float intensity: r.intensities){
				ds.addValue(intensity);
			}
			roiStats.add(ds);
		}
		
		
		
		ROI roiOne = rois.get(0);
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (int i = 0; i < roiOne.intensities.size(); i++){
			stats.addValue((double) roiOne.intensities.get(i));
		}
		System.out.println(stats);
		double stdev = stats.getStandardDeviation();
		double mean = stats.getMean();
		for (int i = 0; i < roiOne.intensities.size(); i++){
			if (roiOne.intensities.get(i) > mean + 1.5*stdev){
				System.out.println("Time: " + roiOne.times.get(i) + ", Intensity: " + roiOne.intensities.get(i));
			}
		}
	}
}
