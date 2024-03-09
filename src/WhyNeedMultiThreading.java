public class WhyNeedMultiThreading {

    public static void main(String[] args) {
        actionOne();
        actionTwo();
        //if actionTwo() takes very long, it will freeze the application
        actionThree();

    }

    private static void actionThree() {
        System.out.println("doing third action");
    }

    private static void actionTwo() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("doing second action");
    }

    private static void actionOne() {
        System.out.println("doing first action");
    }
}
