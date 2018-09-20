package fr.esgi.Day7;

import java.util.ArrayList;
import java.util.List;

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

}
