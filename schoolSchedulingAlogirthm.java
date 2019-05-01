import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class schoolSchedulingAlogirthm {
	static Scanner input = new Scanner(System.in);
	static int[] classId = new int[200];
	static Map<String, List<String>> individualClassId = new HashMap<String, List<String>>();
	static Map<String, String> classToIndividual = new HashMap<String, String>();
	static Map<String, String> startTime = new HashMap<String, String>();
	static Map<String, String> daysOfWeek = new HashMap<String, String>();
	static int numClasses;

	public static void main(String[] args) {
		File temp = new File("C:\\Users\\wwwpa\\Desktop\\AlgorithmsDataSet.txt");
		try {
			read(temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter number of classes: ");
		numClasses = input.nextInt();
		input.nextLine();
		String[] desiredClasses = getClassesArray(numClasses);
		String[] result = new String[numClasses];
		result = findPossibleSchedules(desiredClasses);
		if (result[0] == null) {
			System.out.println("No possible schedules with desired classes.");
		}
		else {
			System.out.println(Arrays.toString(result));
		}
	}

	
	public static void read(File file) throws IOException{
	    Scanner scanner = new Scanner(file);
	    int i = 0;
	    while(scanner.hasNext()){
	    	List<String> temp = new ArrayList<>();
	        String[] tokens = scanner.nextLine().split(",");
	        classId[i] = Integer.parseInt(tokens[1]);
	        individualClassId.put(tokens[1], temp);
	        individualClassId.get(tokens[1]).add(tokens[0]);
	        classToIndividual.put(tokens[0], tokens[1]);
	        startTime.put(tokens[0], tokens[2]);
	        daysOfWeek.put(tokens[0], tokens[3]);
	        i++;
	    }
	}
	
	public static String[] getClassesArray(int numClasses) {
		String[] result = new String[numClasses];
		System.out.println("Enter desired classIds separated by spaces: ");
		Scanner classReader = new Scanner(input.nextLine());
		for(int i = 0; i < numClasses; i++) {
			if (classReader.hasNextInt()) {
				result[i] = classReader.next();
			}
			else {
				System.out.println("Did not input enough classes.");
			}
		}
		return result;
	}
	
	public static String[] findPossibleSchedules(String[] desiredClasses) {
		int length = desiredClasses.length;
		String[] result = new String[numClasses];
		int k = 1;
		List<String> individualClasses = new ArrayList<>();
		for(int i = 0; i < length; i++) {
			int length1 = individualClassId.get(desiredClasses[i]).size();
			for(int j = 0; j < length1; j++) {
				individualClasses.add(individualClassId.get(desiredClasses[i]).get(j));	
			}
		}
		result[0] = individualClasses.get(0);
		for(int i = 1; i < individualClasses.size() - 1; i++) {
			if(startTime.get(result[i]) != startTime.get(result[k-1]) || daysOfWeek.get(result[i]) != daysOfWeek.get(result[k-1])) {
				result[k] = result[i];
				k++;
			}
		}
		return result;
	}
	
}


