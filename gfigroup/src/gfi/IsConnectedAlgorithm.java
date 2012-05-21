package gfi;

import java.util.List;
import java.util.Set;

public interface IsConnectedAlgorithm {
	void setDirectlyConnectedCities(List<ConnectedCityPair> connectedCities);
	boolean isConnected(String cityA, String cityB);
}
