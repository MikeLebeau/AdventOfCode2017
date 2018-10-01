package fr.esgi.Day12;

import java.util.ArrayList;
import java.util.List;

public class Program {

    List<Program> connectedPrograms;
    String id;
    boolean checked;

    public Program(String id) {
        this.id = id;
        connectedPrograms = new ArrayList<>();
        checked = false;
    }

    public Program(List<Program> connectedPrograms, String id) {
        this.connectedPrograms = connectedPrograms;
        this.id = id;
        checked = false;
    }

    void addConnectedProgram(Program program){
        connectedPrograms.add(program);
    }

    public boolean isDirectlyConnected(String id){
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
        if(targetId.equals(this.id) || isDirectlyConnected(targetId)){
            return true;
        }

        for (Program connectedProgram : connectedPrograms) {
            if(checked){
                return false;
            }
            checked = true;
            return connectedProgram.isConnectedTo(targetId);
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
