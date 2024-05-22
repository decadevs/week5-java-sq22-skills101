package week5.task.implementation2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week5.task.enums.Role;
import week5.task.implementation2.model.Book;
import week5.task.implementation2.model.Person;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class LibraryServiceImplTest {
    private LibraryService libraryService;

    @BeforeEach
    void setUp(){
        libraryService = new LibraryServiceImpl();
    }

    @Test
    void addBook() {
        Book book = new Book("Intro to Java", "James Gosling");
        libraryService.addBook(book);

        Queue<Book> books = ((LibraryServiceImpl) libraryService).getBooks();
        assertEquals(1, books.size());
        assertEquals(book, books.peek());
    }

    @Test
    void addPersonToQueue() {
        Book book = new Book ("Intro to Java", "James Gosling");
        Person person = new Person("Henry Ezea", 30, Role.SENIOR_STUDENT, book);
        libraryService.addPersonToQueue(person);

        Queue<Person> bookRequests = ((LibraryServiceImpl) libraryService).getBookRequests();
        assertEquals(person, bookRequests.peek());

    }

    @Test
    void borrowBooks() {
        Book book1 = new Book("Intro to Java", "James Gosling");
        Book book2 = new Book("Intro to Testing with JUnits5", "Olutosin Olalaye");

        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person student1 = new Person ("JaneFrances Ibeh", 28, Role.JUNIOR_STUDENT, book1);
        Person teacher = new Person("Segun Osike", 36,Role.TEACHER, book1);
        Person student2 = new Person("Henry Ezea", 30, Role.SENIOR_STUDENT, book2);
        Person student3 = new Person ("Uche Okoro" ,25, Role.SENIOR_STUDENT, book2) ;


        libraryService.addPersonToQueue(student1);
        libraryService.addPersonToQueue(teacher);
        libraryService.addPersonToQueue(student2);
        libraryService.addPersonToQueue(student3);

        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student1,book1));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher,book1));
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(student2,book2));
    }

    @Test
    void returnBook() {
        Book book = new Book("Data Science", "Decagon");
        libraryService.addBook(book);
        Person teacher = new Person("Segun Osiki", 35,Role.TEACHER,book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher,book));
        assertTrue(libraryService.returnBook(book));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book));

        Book nonBorrowedBook = new Book("Intro to Information Technology", "Gadiba Daro");
        assertFalse(libraryService.returnBook(nonBorrowedBook));
    }
}