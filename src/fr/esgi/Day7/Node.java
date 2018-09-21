package fr.esgi.Day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

    private Node father;
    private List<Node> children;
    private int value;
    private String name;

    public Node(String name, int value) {
        this.name = name;
        this.value = value;
		children = new ArrayList<>();
        father = null;
    }

    public Node(String name, int value, List<Node> children) {
        this.name = name;
        this.children = children;
        this.value = value;
        father = null;
    }

    public Node(String name, int value, List<Node> children, Node father) {
        this.name = name;
        this.father = father;
        this.children = children;
        this.value = value;
    }

    public void addChild(Node child){
    	children.add(child);
	}

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight(){
    	int weight = value;

		for (Node child : children) {
			weight += child.getWeight();
		}

    	return weight;
	}

	public int getDepth(){
    	int depth = 1;
		boolean ok = false;

    	while( ! ok && ! children.isEmpty()){
			depth += children.get(0).getDepth();
			ok = true;
		}

		return depth;
	}

    /**
     * Globaly :
     * -> I check if the node has 2 children, in this case I return first node because we don't care
     * -> Otherwise, I put in map : node value as key and node in list as value
     *
     * @return I return the node which has only one as size of map value
     */
    public Node getDeviantChild(){
        Map<Integer, List<Node>> map = new HashMap<>();

        // If the node has just 2 children
        if(children.size() > 0 && children.size() <= 2){
            return children.get(0);
        }

        for (Node child : children) {
            if(!map.containsKey(child.getWeight())){
                List<Node> nodeList = new ArrayList<>();
                map.put(child.getWeight(), nodeList);
            }

            map.get(child.getWeight()).add(child);
        }

        for (Integer integer : map.keySet()) {
            if(map.get(integer).size() == 1){
                return map.get(integer).get(0);
            }
        }

        return null;
    }

    /**
     * Return a random normal child
     * @return null if normal child not found
     */
    public Node getNormalChild(){
        for (Node child : children) {
            if( ! child.getName().equals(getDeviantChild().getName())){
                return child;
            }
        }
        return null;
    }
}
