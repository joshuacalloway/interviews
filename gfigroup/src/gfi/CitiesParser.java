package gfi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CitiesParser {
	List<ConnectedCityPair> directlyConnectedCities;
	Set<String> uniqueCities;

	void parseFile(String filename) throws Exception {
		directlyConnectedCities = new ArrayList<ConnectedCityPair>();
		uniqueCities = new HashSet<String>();
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			String[] tokens = currentLine.split(",");
			String cityA = tokens[0].trim();
			String cityB = tokens[1].trim();
			uniqueCities.add(cityA);
			uniqueCities.add(cityB);
			directlyConnectedCities.add(new ConnectedCityPair(cityA, cityB));
		}
		br.close();
		fr.close();
	}
	Set<String> getUniqueCities() {
		return uniqueCities;
	}
	List<ConnectedCityPair> getDirectlyConnectedCities() {
		return directlyConnectedCities;
	}

}
