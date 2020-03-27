package hw10.entity.human;

import hw10.entity.DayOfWeek;
import hw10.entity.Family;

import java.text.ParseException;
import java.util.Map;

public final class Woman extends Human {
  public Woman() {
    super();
  }

  public Woman(String name, String surname) {
    super(name, surname);
  }

  public Woman(String name, String surname, String year) throws ParseException {
    super(name, surname, year);
  }

  public Woman(String name, String surname, String year, Map<DayOfWeek, String> schedule) throws ParseException {
    super(name, surname, year, schedule);
  }

  public Woman(String name, String surname, String year, int iq, Family family, Map<DayOfWeek, String> schedule) throws ParseException {
    super(name, surname, year, iq, schedule, family);
  }

  public void makeUp() {
    System.out.println("Woman using makeup");
  }

  @Override
  public void greetPet() {
    System.out.println("GreetPet method inside Woman class");
  }
}