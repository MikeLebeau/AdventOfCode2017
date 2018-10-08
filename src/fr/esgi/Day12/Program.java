package fr.esgi.Day12;

import java.util.ArrayList;
import java.util.List;

public class Program {

    List<Program> connectedPrograms;
    String id;

    public Program(String id) {
        this.id = id;
        connectedPrograms = new ArrayList<>();
    }

    public Program(List<Program> connectedPrograms, String id) {
        this.connectedPrograms = connectedPrograms;
        this.id = id;
    }

    void addConnectedProgram(Program program){
        connectedPrograms.add(program);
    }

    private boolean isCompletlyChecked(List<String> checkedList){
        int res = 0;
        for (Program connectedProgram : connectedPrograms) {
            if(connectedProgram.id.equals(id)){
                res++;
            }
        }

        return (res == connectedPrograms.size());
    }

    public boolean isDirectlyConnected(String id){
        if(this.id.equals(id)){
            return true;
        }
        for (Program connectedProgram : connectedPrograms) {
            if(id.equals(connectedProgram.id)){
                return true;
            }
        }
        return false;
    }

    public boolean isConnectedToZero(){
        return isConnectedTo("0");
    }

    public boolean isConnectedTo(String targetId){
        return isConnectedToBis(targetId, new ArrayList<>());
    }

    private boolean isConnectedToBis(String targetId, List<String> checkedList){
        checkedList.add(id);
        System.out.println("Sommet " + id);
        if(id.equals(targetId)){
            return true;
        }
        for (Program connectedProgram : connectedPrograms) {
            if(!checkedList.contains(connectedProgram.id)){
                return connectedProgram.isConnectedToBis(targetId, checkedList);
            }
        }
        return false;
    }

    private boolean isConnectedTo(String targetId, List<String> checkedList){
        if(isDirectlyConnected(targetId)){
            return true;
        }
        checkedList.add(id);

        System.out.println("isConnectedTo -> " + id);
        System.out.println("\t" + connectedPrograms);

        for (Program connectedProgram : connectedPrograms) {
            if((checkedList.contains(connectedProgram.id))
                    || (connectedProgram.connectedPrograms.size() == 1
                    && connectedProgram.connectedPrograms.get(0).id.equals(this.id))){
                continue;
            }

            return connectedProgram.isConnectedTo(targetId, checkedList);
        }

        return false;
    }

    public String toString(){
        String result = "" + id;
        result += " <->";
        for (Program connectedProgram : connectedPrograms) {
            result += " " + connectedProgram.id;
        }

        return result;
    }

}
