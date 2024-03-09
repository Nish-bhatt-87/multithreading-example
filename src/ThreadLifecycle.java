public class ThreadLifecycle {

    public static void main(String[] args) {
        System.out.println("new"); // thread object created
        System.out.println("active"); //.start() called -> runnable or running
        System.out.println("waiting"); // thread does not need cpu cycles  -> not runnable or running -> when we call the sleep method, for example
        System.out.println("terminated"); //  when the thread's task is completed
    }
}
