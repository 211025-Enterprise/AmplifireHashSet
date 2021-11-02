public class Driver {

    public static void main(String[] args) {

        HashSetAmplifier<User> test = new HashSetAmplifier<User>();
        System.out.println("Initialization");

        User user = new User();
        user.age = 10;
        System.out.println(user.hashCode());

        User user2 = new User();
        user2.age = 12;
        System.out.println(user2.hashCode());

        user.age = 5;
        System.out.println(user.hashCode());

        User user3 = new User();
        User user4 = new User();
        User user5 = new User();
        User user6 = new User();
        User user7 = new User();
        User user8 = new User();
        User user9 = new User();
        User user10 = new User();


        test.add(user);
        test.add(user2);
        test.add(user);
        test.add(user3);
        test.add(user4);
        test.add(user5);
        test.add(user6);
        test.add(user7);
        test.add(user8);
        test.add(user9);
        test.add(user10);



        test.get(user9);
        System.out.println(test.getSize());

    }

    public static class User {
        int age;
        public User() {}
    }

    public void dothing() {

    }
}
