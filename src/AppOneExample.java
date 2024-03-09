

class Runner1 {
    public void execute() {
        for (int i =0; i<10; i++) {
            System.out.println("runner1: " + i);
        }
    }
}

class Runner2 {
    public void execute() {
        for (int i =0; i<10; i++) {
            System.out.println("runner2: " + i);
        }
    }
}

public class AppOneExample {

    public static void main(String[] args) {
        //this is sequential processing
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        runner1.execute();
        runner2.execute();
    }
}
