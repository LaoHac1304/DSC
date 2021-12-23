package DTO;

import DAO.FileDAO;
import MyLib.InputAndValidation;
import MyLib.Output;
import java.util.ArrayList;

public class BookList {

    private ArrayList<Book> list;
    public final String filename = "bookList.txt";

    public BookList() {
        list = new ArrayList<Book>();
    }

    public BookList(final ArrayList<Book> list) {
        this.list = list;
    }

    public ArrayList<Book> getList() {
        return list;
    }

    public void setList(final ArrayList<Book> list) {
        this.list = list;
    }

    public void showBookList(AuthorList authorList) {
        Output.outputBookList(list, authorList);
    }

    public boolean addNewBook(Book book) {
        return (list.add(book));
    }
    
    public int getSize(){
        return list.size();
    }

    public void updateBook(Book updateBook, int index) {
        Book book = list.get(index - 1);
        book.setAuthorId(updateBook.getAuthorId());
        book.setIsbn(updateBook.getIsbn());
        book.setPrice(updateBook.getPrice());
        book.setTitle(updateBook.getTitle());
    }

    public Book deleteBook(int choice) {
        return list.remove(choice - 1);
    }

    public BookList searchBook(int yourChoose, AuthorList authorList) {
        if (yourChoose == 1) {
            String bookName = InputAndValidation.inputAndCheckString("Input name of book : ", "Error !", "^(.)+$");
            return searchBookByName(bookName);
        }
        String authorName = InputAndValidation.inputAndCheckString("Input name of author : ", "Error !", "^(.)+$");
        return searchBookByAuthorName(authorName, authorList);

    }

    public BookList searchBookByName(String bookName) {
        BookList bookList = new BookList();
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            if (book.getTitle().equals(bookName)) {
                bookList.addNewBook(book);
            }
        }
        if (bookList.getSize() == 0) return null;
        return bookList;
    }

    public BookList searchBookByAuthorId(int authorID, AuthorList authorList) {
        BookList bookList = new BookList();
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);

            if ((authorList.getNameByAuthorId(authorID) != null) && (Integer.parseInt(book.getAuthorId()) == authorID)) {
                bookList.addNewBook(book);
            }
        }
        if (bookList.getSize() == 0) return null;
        return bookList;
    }
    
    public Book searchBookByIsbn(String Isbn){
        
        for (Book book : list) {
            if (book.getIsbn().equals(Isbn)) return book;
        }
        return null;
    }

    public BookList searchBookByAuthorName(String authorName, AuthorList authorList) {
        
        BookList bookList = new BookList();
        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            String authorNameFromAuthorList = authorList.getNameByAuthorId(Integer.parseInt(book.getAuthorId()));
            if (authorName.equals(authorNameFromAuthorList)) {

                bookList.addNewBook(book);
            }
        }
        if (bookList.getSize() == 0) return null;
        return bookList;
    }

    public boolean storeDataToFile(AuthorList authorList) {
        try {
            FileDAO.writeData(filename, list, authorList);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // if author in store then don't remove
    public Author deleteAuthor(int authorId, AuthorList authorList) {

        return authorList.removeAuthorByAuthorId(authorId);

    }
}
