public class Driver {

    public static void main(String[] args) {

        HashSetAmplifier<User> test = new HashSetAmplifier<User>();
        System.out.println("Initialization");

        // tests:

        int userAdded = 250;
        for (int i = 0; i < userAdded; i++) {
            User user1 = new User();
            test.add(user1);

        }
        if (test.size() == userAdded) {
            System.out.println("Add() ran successfully!");
        } else {
            System.out.println("Error in add() function!");
        }

        test.clear();

        if (test.size() == 0) {
            System.out.println("clear() ran succesfully");
        } else {
            System.out.println("Error in clear() function!");
        }

        User user = new User();

        test.add(user);

        if (test.contains(user)) {
            System.out.println("contains() ran succesfully");
        } else {
            System.out.println("Error in contains() function!");
        }

        test.remove(user);

        if (!test.contains(user)) {
            System.out.println("remove() ran succesfully");
        } else {
            System.out.println("Error in remove() function!");
        }

    }

    public static class User {
        int age;

        public User() {
        }
    }
}
