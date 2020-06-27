package com.gbb.test.recursive;

import com.gbb.content.recursive.RecursiveFrame;
import com.gbb.content.recursive.RecursiveInstance;

import java.util.function.Consumer;

/**
 * @author gaobinbin
 * @date 2020/06/25
 */
public class FibonacciTest {

    public static void testFib(){
        Consumer<Integer> consumer = new Consumer<Integer>() {
            public int fib(int a){
                if(a < 1){
                    return 0;
                } else if(a == 1){
                    return 1;
                }
                return fib(a-1) + fib(a-2);
            }
            @Override
            public void accept(Integer integer) {
                long start = System.currentTimeMillis();
                System.out.println("fib: " + fib(integer));
                System.out.println("cost: " + (System.currentTimeMillis()-start));
            }
        };
        consumer.accept(50);
    }


    public static class FibRecursiveFrame extends RecursiveFrame<Integer,Integer> {
        public FibRecursiveFrame(int parameter) {
            super(parameter);
        }
        @Override
        public boolean runDirect() {
            Integer parameter = getParameter();
            if(parameter > 1){
                return false;
            }
            setResult(parameter == 1?1:0);
            return true;
        }
        @Override
        public void runAfterSplit() {
            int total = 0;
            for(RecursiveFrame<Integer,Integer> frame:getChildFrame()){
                total += frame.getResult();
            }
            setResult(total);
        }
        @Override
        public void split() {
            addChildFrame(getParameter()-2);
            addChildFrame(getParameter()-1);
        }

        @Override
        public RecursiveFrame<Integer, Integer> newFrame(Integer p) {
            return new FibRecursiveFrame(p);
        }
    }
    public static void testRecursive(){
        long start = System.currentTimeMillis();
        RecursiveInstance instance = new RecursiveInstance(new FibRecursiveFrame(50),true);
        instance.run();
        System.out.println(instance.getResult());
        System.out.println("cost :" + (System.currentTimeMillis()-start));
    }
}
