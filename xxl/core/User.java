package xxl.core;
import xxl.core.Spreadsheet;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String _name;
    private ArrayList<Spreadsheet> _spreadsheets;

    public User(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public ArrayList<Spreadsheet> getSpreadsheets(){
        return _spreadsheets;
    }

    public boolean equals(Object obj){
        if(obj instanceof User){
            User u = (User) obj;
            return _name.equals(u.getName());
        }
        return false;
    }

    public int hashCode(){
        return _name.hashCode();
    }

    void add(Spreadsheet sheet){
        _spreadsheets.add(sheet);
    }
    
}
