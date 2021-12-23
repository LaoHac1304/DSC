package GUI;

import DTO.AuthorList;
import DTO.Book;
import DTO.BookList;
import MyLib.Menu;
import MyLib.InputAndValidation;
import MyLib.Output;

public class BookTest {

    public static void main(String[] args) {
        String[] options = {"Show the book list",
            "Add new book",
            "Update book",
            "Delete book",
            "Search book",
            "Store data to file",
            "Delete author",
            "Real time update processing",
            "Quit"};
        int choice = 0,intIndex = 0;
        Book book = new Book(), updateBook = new Book();
        String index = null;
        BookList bookList = new BookList();
        AuthorList authorList = new AuthorList();
        authorList.displayAuthorList();
        do {
            boolean flag = false;
            do {
                try {
                    Menu.showMenu(options);
                    choice = InputAndValidation.inputNumber("Choose your choice : ", 1, 9);
                    flag = true;
                } catch (Exception e) {
                    System.out.println("Error!");
                    flag = false;
                }
            } while (!flag);

            switch (choice) {
                ////////////////////////////////////////
                case 1:
                    bookList.showBookList(authorList);
                    break;
                ////////////////////////////////////////
                case 2:
                    System.out.println("****** Please input infomation about book ******");
                    book = InputAndValidation.inputBook(bookList, authorList);
                    boolean addBook = bookList.addNewBook(book);
                    if (addBook) {
                        System.out.println("** Add book is done!!! **");
                        bookList.storeDataToFile(authorList);
                    } else {
                        System.out.println("Something is wrong");
                    }
                    break;
                ////////////////////////////////////////
                case 3:
                    System.out.println("***** Update book ******");
                    bookList.showBookList(authorList);
                    do{
                        index = InputAndValidation.inputAndCheckString("Choose book's index which you want to update", "Error!", "^[0-9]+$");
                        intIndex = Integer.parseInt(index);
                        if (intIndex > 0 && intIndex <= bookList.getSize()) break;
                        System.out.println("Error !");
                    }while (true);
                    
                    updateBook = InputAndValidation.inputBook(bookList, authorList);
                    bookList.updateBook(updateBook, Integer.parseInt(index));
                    System.out.println("** Update book is done **");
                    bookList.showBookList(authorList);
                    bookList.storeDataToFile(authorList);
                    break;
                ////////////////////////////////////////
                case 4:
                    System.out.println("**** Choose book which you want to remove ****");
                    intIndex = 0;
                    bookList.showBookList(authorList);
                    do {
                        index = InputAndValidation.inputAndCheckString("Your choice", "Error", "^[0-9]+$");
                        intIndex = Integer.parseInt(index);
                        if (intIndex < 1 || intIndex > bookList.getSize()) {
                            System.out.println("Error!");
                        }
                    } while (intIndex < 1 || intIndex > bookList.getSize());

                    Book deletedBook = bookList.deleteBook(Integer.parseInt(index));
                    if (deletedBook != null) {
                        System.out.println("** Update book is done **");
                        bookList.showBookList(authorList);
                        bookList.storeDataToFile(authorList);
                    } else {
                        System.out.println("Something is wrong");
                    }
                    break;
                ////////////////////////////////////////
                case 5:
                    System.out.println("**** Search book *****");
                    System.out.println("1. Search by book name");
                    System.out.println("2. Search by author name");
                    do {
                        index = InputAndValidation.inputAndCheckString("Input your choice", "Error !", "^[1-2]+$");
                        if (index.length() != 1) {
                            System.out.println("Error !");
                        } else {
                            break;
                        }
                    } while (true);
                    BookList foundBookList = bookList.searchBook(Integer.parseInt(index), authorList);
                    if (foundBookList == null) System.out.println("Not found !!");
                    else
                        Output.outputBookList(foundBookList.getList(), authorList);
                    break;
                ////////////////////////////////////////
                case 6:
                    System.out.println("**** Store data to file ****");
                    boolean checkDataSaveInFile = bookList.storeDataToFile(authorList);
                    if (checkDataSaveInFile) {
                        System.out.println("Data is saved");
                    } else {
                        System.out.println("Something wrong !!!");
                    }
                    break;
                ////////////////////////////////////////
                case 7:
                    System.out.println("**** Delete author ****");
                    int authorId = InputAndValidation.inputNumber("Input author's id : ", 0, 10000000);
                    String nameAuthor = authorList.getNameByAuthorId(authorId);
                    if (nameAuthor == null || bookList.searchBookByAuthorId(authorId, authorList) != null) {
                        System.out.println("Error !");
                    } else {
                        bookList.deleteAuthor(authorId, authorList);
                        authorList.displayAuthorList();
                    }
                    break;
                ////////////////////////////////////////
                case 8:
                    System.out.println("Real time update processing (auto)");
                    break;
                case 9:
                    return;
            }

        } while (choice > 0 && choice <= 8);

    }

}
