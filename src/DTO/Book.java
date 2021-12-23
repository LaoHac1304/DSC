package DTO;

public class Book {

    private String isbn;
    private String title;
    private String price;
    private String authorId;

    public Book() {
        isbn = null;
        title = null;
        price = null;
        authorId = null;
    }

    public Book(final String isbn, final String title, final String price, final String authorId) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(final String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", price=" + price + ", authorId=" + authorId + '}';
    }
}
