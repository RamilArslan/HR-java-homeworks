package hw08;

import java.util.Set;

public class Dog extends Pet {
  Dog() {
    super();
  }

  Dog(String nickname) {
    super(nickname);
    species = Species.DOG;
  }

  Dog(String nickname, int age, int trickLevel, Set<String> habits) {
    super(nickname, age, trickLevel, habits);
    species = Species.DOG;
  }

  @Override
  public void respond() {
    System.out.printf("AVVVVVVVVV, i am %s\n",getNickname());
  }

  @Override
  public void foul() {
    System.out.println(".........");
  }
}
