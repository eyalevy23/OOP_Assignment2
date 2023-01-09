



import java.util.concurrent.*;

public class Test {
         @org.junit.Test
        public void partialTest(){
            CustomExecutor customExecutor = new CustomExecutor();
            var task = Task.create(()->{
                int sum = 0;
                for (int i = 1; i <= 10; i++) {
                    sum += i;
                }
                return sum;
            }, TaskType.COMPUTATIONAL);
            var sumTask = customExecutor.submit(task);
            final int sum;
            try {
                sum = sumTask.get(1, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Sum of 1 through 10 = " + sum);
            // logger.info(()-> "Sum of 1 through 10 = " + sum);
            Callable<Double> callable1 = ()-> {
                return 1000 * Math.pow(1.02, 5);
            };
            Callable<String> callable2 = ()-> {
                StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                return sb.reverse().toString();
            };
            // var is used to infer the declared type automatically
            var priceTask = customExecutor.submit(()-> {
                return 1000 * Math.pow(1.02, 5);
            }, TaskType.COMPUTATIONAL);
            var reverseTask = customExecutor.submit(callable2, TaskType.IO);
            final Double totalPrice;
            final String reversed;
            try {
                totalPrice = priceTask.get();
                reversed = reverseTask.get();
            } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
            }
            System.out.println( "Reversed String = " + reversed);
            System.out.println(String.valueOf("Total Price = " + totalPrice));
            System.out.println("Current maximum priority = " + customExecutor.getMaxPriority());
            // logger.info(()-> "Reversed String = " + reversed);
            // logger.info(()->String.valueOf("Total Price = " + totalPrice));
            // logger.info(()-> "Current maximum priority = " + customExecutor.getCurrentMax());
            // customExecutor.gracefullyTerminate();
        }
}