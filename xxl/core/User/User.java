package xxl.core.User;

import java.util.ArrayList;
import xxl.core.Spreadsheet;

public class User {
    private String _name;
    private ArrayList<Spreadsheet> _spreadsheets;

    public User(String name) {
        _name = name;
        _spreadsheets = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public ArrayList<Spreadsheet> getSpreadsheets() {
        return _spreadsheets;
    }

    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User u = (User) obj;
            return _name.equals(u.getName());
        }
        return false;
    }

    public int hashCode() {
        return _name.hashCode();
    }

    public void addSpreadsheet(Spreadsheet sheet) {
        _spreadsheets.add(sheet);
        //bidirectional relationship
        sheet.addUser(this);
    }
}

