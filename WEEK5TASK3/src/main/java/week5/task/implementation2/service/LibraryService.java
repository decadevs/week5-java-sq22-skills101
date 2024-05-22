package week5.task.implementation2.service;

import week5.task.implementation2.model.Book;
import week5.task.implementation2.model.Person;

public interface LibraryService {
    void addBook(Book book);
    void addPersonToQueue(Person person);
    void borrowBooks();
    boolean returnBook(Book book);
}
