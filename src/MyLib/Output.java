
package MyLib;

import DTO.AuthorList;
import DTO.Book;
import java.util.ArrayList;

public class Output {
    
    public static void outBook(Book book, int position, AuthorList authorList){
        
        System.out.println("----------------------------");
        System.out.println("Book " + position + ": ");
        System.out.println("ISBN : " + book.getIsbn());
        System.out.println("Title : " + book.getTitle());
        System.out.println("Author id : " + book.getAuthorId());
        String authorName = authorList.getNameByAuthorId(Integer.parseInt(book.getAuthorId()));
        System.out.println("Author name : " + authorName);
        System.out.println("Price : " + book.getPrice());
        
    }
    
    public static void outputBookList(ArrayList<Book> bookList, AuthorList authorList){
        
        int position = 0;
        for (Book book : bookList) {
            position++;
            outBook(book, position, authorList);
        }
    }
    
}
