package DTO;

import DAO.FileDAO;
import java.util.HashMap;
import java.util.Map;

public class AuthorList {

    // add các author từ file .dat vào đây
    private HashMap<Integer, Author> list;
    private final String filename = "author.txt";

    public AuthorList() {
        try {
            list = FileDAO.readData(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AuthorList(HashMap<Integer, Author> list) {
        this.list = list;
    }

    public HashMap<Integer, Author> getList() {
        return list;
    }

    public void setList(HashMap<Integer, Author> list) {
        this.list = list;
    }

    public void displayAuthorList() {
        for (Map.Entry<Integer, Author> entry : list.entrySet()) {

            Author val = entry.getValue();
            System.out.println("-----------------");
            System.out.println(val);
        }
    }

    public String getNameByAuthorId(int authorID) {
        if (list.get(authorID) == null) return null;
        String nameAuthor = list.get(authorID).getName();
        return nameAuthor;
    }

    public Author removeAuthorByAuthorId(int authorID) {
        return list.remove(authorID);
    }

}
