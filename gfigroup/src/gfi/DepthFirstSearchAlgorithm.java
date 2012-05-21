package gfi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSearchAlgorithm implements IsConnectedAlgorithm {
	class Node<T> {
		final T value;
		boolean visited=false;

		Node(T valu) {
			this.value = valu;
		}

		T getValue() { return value; }
		boolean isVisited() { return visited; }
		void setVisited(boolean aVisited) { this.visited = aVisited; }

		@Override
		public boolean equals(Object other) {
			if (super.equals(other)) return true;
			if (other instanceof Node) {
				Node otherNode = (Node)other;
				if (otherNode.getValue().equals(getValue())) return true;
				return false;
			}
			return false;
		}
	}	
	
	
	Map<String, Set<String> > adjMatrix = new HashMap<String, Set<String> >();  // Adjacency Matrix
	Map<String, Node> nodes = new HashMap<String, Node>();

	private void addAdjMatrix(String cityA, String cityB) {
		if (!adjMatrix.containsKey(cityA)) {
			adjMatrix.put(cityA, new HashSet<String>());
		}
		Set<String> adjacentCities = adjMatrix.get(cityA);
		adjacentCities.add(cityB);
		nodes.put(cityA, new Node(cityA));
		nodes.put(cityB, new Node(cityB));
	}

	private boolean doDFSSearch(String cityA, String cityB) {
		Stack<String> stack = new Stack<String>();
		stack.push(cityA);
		while (!stack.isEmpty()) {
			String city = stack.pop();
			nodes.get(city).setVisited(true);
			Set<String> adjacentCities = adjMatrix.get(city);
			Iterator<String> i = adjacentCities.iterator();
			while (i.hasNext()) {
				String next = i.next();
				if (next.equalsIgnoreCase(cityB)) {
					return true;
				} else if (!nodes.get(next).isVisited()) {
					Node nextNode = nodes.get(next);
					stack.push(next);
				}										
			}	
		}
		return false;
	}

	@Override
	public void setDirectlyConnectedCities(List<ConnectedCityPair> connectedCities) {
		for (ConnectedCityPair next : connectedCities) {
			addAdjMatrix(next.getCityA(), next.getCityB());
			addAdjMatrix(next.getCityB(), next.getCityA());
		}
	}

	@Override
	public boolean isConnected(String cityA, String cityB) {
		boolean reachable = doDFSSearch(cityA, cityB);
		return reachable;
	}

}
