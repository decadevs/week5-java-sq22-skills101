package week5.task.implementation1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week5.task.enums.Role;
import week5.task.implementation1.model.Book;
import week5.task.implementation1.model.Person;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.PriorityQueue;
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
        assertEquals(1,books.size());
        assertEquals(book, books.peek());
    }

    @Test
    void addPersonToQueue() {
        Book book = new Book("Intro to Testing with Junit5", "Olutosin Olalaye");
        Person person1 = new Person("Henry Ezea", Role.SENIOR_STUDENT, book);
        Person person2 = new Person("JaneFrances Ibeh", Role.JUNIOR_STUDENT, book);

        libraryService.addPersonToQueue(person1);

        HashMap<String, PriorityQueue<Person>> bookRequests = ((LibraryServiceImpl) libraryService).getBookRequests();
        assertEquals(1, bookRequests.get(book.getTitle()).size());
        assertEquals(person1, bookRequests.get(book.getTitle()).peek());
        assertNotEquals(person2, bookRequests.get(book.getTitle()).peek());
    }

    @Test
    void borrowBooks() {
        Book book1 = new Book("Intro to Java", "James Gosling");
        Book book2 = new Book("Intro to Testing with JUnit5", "Olutosin Olalaye");
        libraryService.addBook(book1);
        libraryService.addBook(book2);

        Person teacher = new Person("Segun Osiki", Role.TEACHER, book1);
        Person seniorStudent = new Person("Henry Ezea", Role.SENIOR_STUDENT, book2);


        Person juniorStudent = new Person("JaneFrances", Role.JUNIOR_STUDENT, book2);
        Person seniorStudent2 = new Person ("Uche Okor0", Role.SENIOR_STUDENT,book2);


        libraryService.addPersonToQueue(seniorStudent);
        libraryService.addPersonToQueue(teacher);


        libraryService.addPersonToQueue(seniorStudent2);
        libraryService.addPersonToQueue(juniorStudent);

        libraryService.borrowBooks();
        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book1));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(seniorStudent, book1));


        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(juniorStudent, book2));
        assertFalse(((LibraryServiceImpl) libraryService).isBookBorrowedBy(seniorStudent2, book2));
    }

    @Test
    void returnBook() {
        Book book = new Book("Data Science", "Decagon");
        libraryService.addBook(book);

        Person teacher = new Person("Segun Osiki", Role.TEACHER, book);
        libraryService.addPersonToQueue(teacher);
        libraryService.borrowBooks();

        assertTrue(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher, book));

        assertTrue(libraryService.returnBook(book));
        assertFalse(((LibraryServiceImpl)libraryService).isBookBorrowedBy(teacher,book));

        Book nonBorrowedBook = new Book ("Intro to Information Technology", "Damilola");
        assertFalse(libraryService.returnBook(nonBorrowedBook));






    }
}