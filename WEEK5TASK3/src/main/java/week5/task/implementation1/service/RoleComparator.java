package week5.task.implementation1.service;

import week5.task.implementation1.model.Person;

import java.util.Comparator;

public class RoleComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p2.getRole().ordinal(),p1.getRole().ordinal());
    }
}
