package hw13;

import hw13.controller.FamilyController;
import hw13.entity.Family;
import hw13.entity.human.Human;
import hw13.entity.human.Man;
import hw13.entity.human.Woman;
import hw13.entity.pet.Dog;
import hw13.entity.pet.DomesticCat;
import hw13.entity.pet.Pet;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleApp {
  private FamilyController familyController = new FamilyController();
  private static final Scanner SCANNER = new Scanner(System.in);
  private static final String REG_MENU = "(([0-9])+)|11";
  private static final String REG_SUBMENU = "[1-3]";
  private static final String REG_NUM = "([0-9])+";
  private static final String REG_STR = "\\D+";
  private static final String REG_YEAR = "([0-9]){4}";
  private static final String REG_MONS = "(1[0-2]|0[1-9])";
  private static final String REG_DAY = "((1)[0-9]|(2)[0-9]|0[1-9]|3[0-1])";
  private static final String REG_IQ = "([0-9]|[1-8][0-9]|9[0-9]|1[0-5][0-9]|160)";
  private static final String REG_GEN = "[(m|w),(W|M)]";
  private List<Family> families;


  public void genTestData() throws ParseException {
    Pet dog = new Dog("Bafl");
    dog.setAge(5);
    dog.setTrickLevel(25);
    Set<String> habitsOfDog = new HashSet<>();
    habitsOfDog.add("sleep");
    habitsOfDog.add("eat");
    habitsOfDog.add("play");
    dog.setHabits(habitsOfDog);

    Pet cat = new DomesticCat("Tom");
    cat.setAge(3);
    cat.setTrickLevel(80);
    Set<String> habitsOfCat = new HashSet<>();
    habitsOfCat.add("run");
    habitsOfCat.add("play");
    habitsOfCat.add("sleep");
    cat.setHabits(habitsOfCat);

    //Families

    Man fatherBlack = new Man("Alex", "Black", "1971/09/07");
    fatherBlack.setIq(75);
    Woman motherBlack = new Woman("June", "Black", "1975/07/22");
    motherBlack.setIq(75);
    Family familyBlack = new Family(fatherBlack, motherBlack);


    Man fatherAliyev = new Man("Ali", "Aliyev", "1985/01/10");
    fatherAliyev.setIq(95);
    Woman motherAliyev = new Woman("Nur", "Aliyev", "1988/01/01");
    motherAliyev.setIq(80);
    Family familyAliyev = new Family(fatherAliyev, motherAliyev);


    Man fatherWhite = new Man("Piter", "White", "1960/05/10");
    fatherWhite.setIq(100);
    Woman motherWhite = new Woman("Yana", "White", "1966/06/06");
    motherWhite.setIq(95);
    Family familyWhite = new Family(fatherWhite, motherWhite);
  }

  public void printCommandList() {
    System.out.print("LIST OF COMMANDS\n\n- 1. TO FILL TEST DATA\n- 2. SHOW THE ALL LIST OF FAMILIES:\n- 3. SHOW THE ALL LIST OF FAMILIES, WHERE AMOUNT OF PEOPLE BIGGER THEN GIVEN NUMBER:\n- 4. SHOW THE ALL LIST OF FAMILIES, WHERE AMOUNT OF PEOPLE SMALLER THEN GIVEN NUMBER:\n- 5. COUNT AMOUNT OF FAMILIES, WHERE MEMBERS OF FAMILY EQUAL GIVEN NUMBER:\n- 6. CREATE A NEW FAMILY:\n- 7. DELETED FAMILY BY FAMILY INDEX IN THE GENERAL LIST:\n- 8. EDIT FAMILY BY FAMILY INDEX IN THE GENERAL LIST:\n- 9. DELETED THE ALL CHILDREN OLDER THEN AGE:\n- 10. SAVE TO FIle:\n- 11. DOWNLOAD FROM FILE:\n IF YOU WONT BREAK THE PROGRAM, PLEASE ENTER THE: EXIT\n\nEND LIST OF COMMANDS");
  }

  public void printCase8() {
    System.out.print("- 8. EDIT FAMILY BY FAMILY INDEX IN THE GENERAL LIST:\n   - 1. Born a child:\n   - 2. Adopt a child:\n   - 3. Return to main menu.\n");
  }


  private String validInput(String regex, String printInfo) {
    String string = SCANNER.nextLine();
    while (!string.matches(regex) && !string.equalsIgnoreCase("exit")) {
      System.out.print("\nWRONG DATA -> " + string);
      System.out.print(printInfo);
      string = SCANNER.nextLine();
    }
    return string.toUpperCase();
  }


  public void runApplication() throws ParseException {
    System.out.println("\t\tConsole App\t\t");
    String command = null;
    do {
      printCommandList();
      System.out.print("Please enter number of command: ");
      try {
        label:
        switch (command = validInput(REG_MENU, "Enter a command [1-9]: ")) {
          case "1":
            System.out.println("Test data generated.");
            genTestData();
            break;
          case "2":
            System.out.println("List of all the families:");
            familyController.displayAllFamilies();
            break;
          case "3":
            System.out.println("Display a list of families where the number of people is greater than the specified number");
            int numBig = Integer.parseInt(validInput(REG_NUM, "Enter a number: "));
            familyController.getFamiliesBiggerThan(numBig);
            if (familyController.getAllFamilies().size() == 0) {
              families = familyController.downloadData();
            } else {
              familyController.getFamiliesBiggerThan(numBig);
            }
            break;
          case "4":
            System.out.println("Display a list of families where the number of people is less than the specified number");
            int numSmall = Integer.parseInt(validInput(REG_NUM, "Enter a number: "));
            familyController.getFamiliesLessThan(numSmall);
            break;
          case "5":
            System.out.println("Calculate the number of families where the number of members is");
            int numEqual = Integer.parseInt(validInput(REG_NUM, "Enter a number: "));
            familyController.countFamiliesWithMemberNumber(numEqual);
            break;
          case "6":
            System.out.println("Create a new family");
            System.out.print("- 6. CREATE A NEW FAMILY:\n");

            String nameF = validInput(REG_STR, "\n\t- Enter father's name: ");
            String surnameF = validInput(REG_STR, "\n\t- Enter father's surname: \"");
            String yearF = validInput(REG_YEAR, "\n\t- Enter father's birth year: ");
            String monthF = validInput(REG_MONS, "\n\t- Enter father's birth month: ");
            String dayF = validInput(REG_DAY, "\n\t- Enter father's birth day: ");
            int iqF = Integer.parseInt(validInput(REG_IQ, "\n\t- Enter father's iq: "));
            String birthDateF = String.format("%d/%d/%d", Integer.parseInt(dayF), Integer.parseInt(monthF), Integer.parseInt(yearF));
            Human father = new Man(nameF, surnameF, birthDateF, iqF);

            String nameM = validInput(REG_STR, "\n\t- enter mother's name: ");
            String surnameM = validInput(REG_STR, "\n\t- enter mother's surname: ");
            String yearM = validInput(REG_YEAR, "\n\t- enter mother's birth year: ");
            String monthM = validInput(REG_MONS, "\n\t- enter mother's birth month: ");
            String dayM = validInput(REG_DAY, "\n\t- enter mother's birth day: ");
            int iqM = Integer.parseInt(validInput(REG_IQ, "\n\t- enter mother's iq: "));
            String birthDateM = String.format("%d/%d/%d", Integer.parseInt(dayM), Integer.parseInt(monthM), Integer.parseInt(yearM));
            Human mother = new Woman(nameM, surnameM, birthDateM, iqM);

            familyController.createNewFamily(mother, father);
            System.out.println("New family created.");
            break;
          case "7":
            System.out.println("Delete a family by its index in the general list");
            int familyId = Integer.parseInt(validInput(REG_NUM, "Enter family id"));
            familyController.deleteFamilyByIndex(familyId);
            break;
          case "8":
            printCase8();
            String subCommand = validInput(REG_SUBMENU, "Enter sub-menu number:");
            switch (subCommand) {
              case "1": {
                System.out.print("1. Born a child\n");
                int fId = Integer.parseInt(validInput(REG_NUM, "Enter family index(ID): "));
                try {
                  Family family = familyController.getAllFamilies().get(fId - 1);
                  System.out.print("Enter the necessary data (boy's name, girl's name)\n");
                  String boyName = validInput(REG_STR, "Name of son: ");
                  String girlName = validInput(REG_STR, "\n\t- name of daughter: ");
                  familyController.bornChild(family, boyName, girlName);
                } catch (IndexOutOfBoundsException e) {
                  System.out.printf("Family by %d not presented\n", fId);
                }
                break;
              }
              case "2": {
                System.out.print("1. Adopt a child\n");
                int fId = Integer.parseInt(validInput(REG_NUM, "Enter family index(ID): "));
                try {
                  Family family = familyController.getAllFamilies().get(fId - 1);
                  System.out.print("\t- enter the gender of child (M - man, W - woman): ");
                  String gender = validInput(REG_GEN, "\n\t- enter the gender of child (M - man, W - woman): ");

                  boolean gen = gender.toLowerCase().trim().equalsIgnoreCase("m");

                  String name = validInput(REG_STR, "\n\t- enter the name of child: ");
                  String year = validInput(REG_YEAR, "\n\t- enter the year of birthday child: ");
                  String month = validInput(REG_MONS, "\n\t- enter the month of birthday child: ");
                  String day = validInput(REG_DAY, "\n\t- enter the day of birthday child: ");
                  int iq = Integer.parseInt(validInput(REG_IQ, "\n\t- enter the IQ of child: "));

                  if (gen) {
                    Human m = new Man(name, family.getFather().getSurname(), String.format("%s/%s/%s", day, month, year), iq);
                    familyController.adoptChild(family, m);
                  } else {
                    Human w = new Woman(name, family.getFather().getSurname(), String.format("%s/%s/%s", day, month, year), iq);
                    familyController.adoptChild(family, w);
                  }
                } catch (IndexOutOfBoundsException e) {
                  System.out.printf("Family by %d not found!\n", fId);
                }
                break;
              }
              case "3":
                System.out.println("returning to main menu");
                break label;
            }
          case "9":
            System.out.println("Remove all children over the age of majority (all families remove children over the age of majority - let us assume they have grown up)");
            int age = Integer.parseInt(validInput(REG_NUM, "\n\t- enter the age: "));
            familyController.deleteAllChildrenOlderThen(age);
            System.out.println("All children is deleted");
            break;
          case "10":
            System.out.print("- 10. SAVE TO FILE\n");
            familyController.loadData(families);
          break;
          case "11":
            System.out.print("- 11. DOWNLOAD FROM FILE\n");
            familyController.updateFamily(families);
            families = familyController.downloadData();
            break;
        }
      } catch (NumberFormatException ex) {
        System.out.println("NumberFormatException throwed");
      }
    } while (!command.equalsIgnoreCase("EXIT"));
    System.out.println("End of operations!");
  }
}
