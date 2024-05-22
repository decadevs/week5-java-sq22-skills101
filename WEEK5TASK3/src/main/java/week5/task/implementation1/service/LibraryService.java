package week5.task.implementation1.service;

import week5.task.implementation1.model.Book;
import week5.task.implementation1.model.Person;

public interface LibraryService {

     void addBook(Book book);
     void addPersonToQueue(Person person);
     void borrowBooks();
     boolean returnBook(Book book);
}
