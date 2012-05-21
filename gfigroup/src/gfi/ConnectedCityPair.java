package gfi;


public class ConnectedCityPair {
	final String cityA;
	final String cityB;

	public ConnectedCityPair(String cityA, String cityB) {
		this.cityA = cityA.trim();
		this.cityB = cityB.trim();
	}

	public String getCityA() { return cityA; }
	public String getCityB() { return cityB; }

	@Override public boolean equals(Object other) {
		if (super.equals(other)) return true;
		if (other instanceof ConnectedCityPair) {
			ConnectedCityPair otherConnected = (ConnectedCityPair)other;
			if (otherConnected.getCityA().equalsIgnoreCase(getCityA()) && 
					otherConnected.getCityB().equalsIgnoreCase(getCityB())) return true;

			if (otherConnected.getCityB().equalsIgnoreCase(getCityA()) && 
					otherConnected.getCityA().equalsIgnoreCase(getCityB())) return true;
			return false;
		}
		return false;
	}

}
