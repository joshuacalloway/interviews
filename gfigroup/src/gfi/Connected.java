package gfi;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.ArrayList;

public class Connected {
	
	public static void main(String[] args) {
		String filename = args[0];
		String cityA = args[1];
		String cityB = args[2];
		try {
			CitiesParser parser = new CitiesParser();
			parser.parseFile(filename);
			List connectedCities = parser.getDirectlyConnectedCities();
			
			IsConnectedAlgorithm isConnected = new DepthFirstSearchAlgorithm();
			isConnected.setDirectlyConnectedCities(connectedCities);		
			boolean result = isConnected.isConnected(cityA, cityB);
			System.out.println(result? "yes" : "no");
		} catch (Exception any) {
			System.out.println("Failed to parse or execute Connected due to " + any.getMessage());
			any.printStackTrace();
		}
	}
}