package DAO;

import DTO.Author;
import DTO.AuthorList;
import DTO.Book;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


public class FileDAO {
    
    public static HashMap<Integer,Author> readData(String filename) throws Exception{
        HashMap<Integer, Author> list = new HashMap<>();
        //step 1 : open file
        FileReader f =  new FileReader(filename);
        BufferedReader bf = new BufferedReader(f);
        
        //step 2 : read
        while (bf.ready()){
            String s = bf.readLine();
            String [] tmp = s.split("-"); // cắt chuỗi
            if (tmp.length == 2){
                Author author = new Author(tmp[0].trim(), tmp[1]);
                list.put(Integer.parseInt(tmp[0].trim()), author);
            }
        }
        //step 3 : close
        if (f!=null) f.close();
        if (bf!=null) bf.close();
        return list;
    }
    
    public static void writeData(String filename, ArrayList<Book> list, AuthorList authorList) throws Exception{
        PrintWriter w = new PrintWriter(filename);
        int position = 0;
        for (Book book : list) {
            position++;
            w.println("----------------------------");
            w.println("Book " + position + ": ");
            w.println("ISBN : " + book.getIsbn());
            w.println("Title : " + book.getTitle());
            w.println("Author id : " + book.getAuthorId());
            String authorName = authorList.getNameByAuthorId(Integer.parseInt(book.getAuthorId()));
            w.println("Author name : " + authorName);
            w.println("Price : " + book.getPrice());
        }
        if (w!=null) w.close();
    }
    
}
