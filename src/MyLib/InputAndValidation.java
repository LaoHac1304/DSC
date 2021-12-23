package MyLib;

import DTO.AuthorList;
import DTO.Book;
import DTO.BookList;
import java.util.Scanner;

public class InputAndValidation {

    private static Scanner sc = new Scanner(System.in);

    public static int inputNumber(String message, int min, int max) {
        int number;
        do {
            System.out.println(message);
            String stringNumber = sc.nextLine().trim();

            try {
                number = Integer.parseInt(stringNumber);
                if (number > max || number < min) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println("Error !");
            }
        } while (true);
    }

    public static String inputString(String message, String pattern) throws Exception {
        String s = "";
        System.out.println(message);
        s = sc.nextLine();

        if (!s.matches(pattern) || s == null || s.isEmpty() || s.trim().isEmpty()) {
            throw new Exception();// khớp , đúng format
        }
        return s;
    }

    public static String inputAndCheckString(String messageFromKeyBoard, String messageCatchError, String pattern) {
        String str = null;
        do {
            try {
                str = inputString(messageFromKeyBoard, pattern);
                return str;

            } catch (Exception e) {
                System.out.println(messageCatchError);
            }
        } while (true);

    }

    public static String inputAndCheckISBNNumber(String messageFromKeyBoard, String messageCatchError, BookList bookList) {

        String isbn = null;
        do {
            try {
                isbn = inputString(messageFromKeyBoard, "^[0-9]+$");
                if (isbn.length() != 10) {
                    throw new Exception();
                }
                int sum = 0;
                for (int i = 0; i < isbn.length(); i++) {
                    int digit = isbn.charAt(i) - 48;
                    sum = sum + digit * (10 - i);
                }
                if ( (sum % 11 != 0) || (bookList.searchBookByIsbn(isbn) != null) ) {
                    throw new Exception();
                }
                
                return isbn;

            } catch (Exception e) {
                System.out.println(messageCatchError);
            }
        } while (true);
    }

    public static String inputAndCheckAuthorId(String messageFromKeyBoard, String messageCatchError, AuthorList authorList) {

        String authorId = null;
        do {
            try {
                authorId = inputString(messageFromKeyBoard, "^[0-9]+$");
                if (authorList.getNameByAuthorId(Integer.parseInt(authorId)) == null) {
                    throw new Exception();
                }
                return authorId;

            } catch (Exception e) {
                System.out.println(messageCatchError);
            }
        } while (true);
    }
    
    

    public static Book inputBook(BookList bookList, AuthorList authorList) {

        Book book = new Book();
        book.setIsbn(inputAndCheckISBNNumber("Input ISBN : ", "Error!", bookList));
        book.setAuthorId(inputAndCheckAuthorId("Input Author ID : ", "Error!", authorList));
        book.setPrice(inputAndCheckString("Input Price : ", "Error!", "^[0-9]+$"));
        book.setTitle(inputAndCheckString("Input Title : ", "Error!", "^(.)+$"));
        return book;
    }

}
