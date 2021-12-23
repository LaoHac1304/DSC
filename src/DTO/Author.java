package DTO;

public class Author {

    private String authorID;
    private String name;

    public Author() {
        authorID = null;
        name = null;
    }

    public Author(String authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" + "authorID=" + authorID + ", name=" + name + '}';
    }

}
