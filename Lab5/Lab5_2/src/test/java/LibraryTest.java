package test.java;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;

import main.java.org.example.*;

import java.sql.Date;
import java.util.List;

public class LibraryTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private Library library;

    List<Book> books;

    @Given("I am on the library search page")
    public void setup() {
        library = new Library();
    }

    @ParameterType("Date")
    public Date date(String date) {
        return Date.valueOf(date);
    }

    @Given("I have a book with the title {string}, author {string} and published date {int}-{int}-{int}")
    public void addBook(String title, String author, int year, int month, int day) {
        Date published = Date.valueOf(year + "-" + month + "-" + day);
        log.debug("Adding book with title {}, author {} and published date {}", title, author, published);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("I search for books published between {int}-{int}-{int} and {int}-{int}-{int}")
    public void findBooks(int Year, int Month, int Day, int Year2, int Month2, int Day2) {
        Date start = Date.valueOf(Year + "-" + Month + "-" + Day);
        Date end = Date.valueOf(Year2 + "-" + Month2 + "-" + Day2);
        log.debug("Searching for books published between {} and {}", start, end);
        books = library.findBooks(start, end);
    }

    @Then("I should see {string} by {string} published in {int}-{int}-{int}")
    public void verifyBook(String title, String author, int year, int month, int day) {
        Date published = Date.valueOf(year + "-" + month + "-" + day);
        log.debug("Verifying book with title {}, author {} and published date {}", title, author, published);
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getPublished() == published) {
                return;
            }
        }    
    }

    @Then("I should not see {string} by {string} published in {int}-{int}-{int}")
    public void verifyBookNotPresent(String title, String author, int year, int month, int day) {
        Date published = Date.valueOf(year + "-" + month + "-" + day);
        log.debug("Verifying book with title {}, author {} and published date {}", title, author, published);
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getPublished() == published) {
                throw new RuntimeException("Book found");
            }
        }
    }




    



}