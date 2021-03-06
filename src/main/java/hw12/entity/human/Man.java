package hw12.entity.human;

import hw12.entity.DayOfWeek;
import hw12.entity.Family;

import java.text.ParseException;
import java.util.Map;

public final class Man extends Human {
  public Man() {
    super();
  }

  public Man(String name, String surname) {
    super(name, surname);
  }

  public Man(String name, String surname, String year) throws ParseException {
    super(name, surname, year);
  }

  public Man(String name, String surname, String year, int iq) throws ParseException {
    super(name, surname, year, iq);
  }

  public Man(String name, String surname, String year, int iq, Family family, Map<DayOfWeek, String> schedule) throws ParseException {
    super(name, surname, year, iq, schedule, family);
  }

  public Man(String name, String surname, String year, Map<DayOfWeek, String> schedule) throws ParseException {
    super(name, surname, year, schedule);
  }

  public void repairCar() {
    System.out.println("Car was broken. I am repairing it");
  }

  @Override
  public void greetPet() {
    System.out.println("GreetPet method inside Man class");
  }
}