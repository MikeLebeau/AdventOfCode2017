package fr.esgi.Day7;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Day7.Node father;
    private List<Day7.Node> child;
    private int value;
    private String name;

    public Node(String name, int value) {
        this.name = name;
        this.value = value;
        child = new ArrayList<>();
        father = null;
    }

    public Node(String name, int value, List<Day7.Node> child) {
        this.name = name;
        this.child = child;
        this.value = value;
        father = null;
    }

    public Node(String name, int value, List<Day7.Node> child, Day7.Node father) {
        this.name = name;
        this.father = father;
        this.child = child;
        this.value = value;
    }

    public List<Day7.Node> getChild() {
        return child;
    }

    public void setChild(List<Day7.Node> child) {
        this.child = child;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Day7.Node getFather() {
        return father;
    }

    public void setFather(Day7.Node father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
