package com.gbb.content.sort;

import com.gbb.content.recursive.RecursiveFrame;
import com.gbb.content.recursive.RecursiveInstance;

import java.util.Comparator;
import java.util.List;

/**
 * @author gaobinbin
 * @date 2020/06/27
 */
public class QuickSort<O> extends Sorter<O> {
    public QuickSort(Comparator<O> comparator) {
        super(comparator);
    }

    class QuickSortParameter {
        private int start;
        private int end;
        private int base;

        public QuickSortParameter(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    class QuickSortRecursiveFrame extends RecursiveFrame<QuickSortParameter,List<O>>{

        public QuickSortRecursiveFrame(QuickSortParameter quickSortParameter) {
            super(quickSortParameter);
        }

        @Override
        public boolean runDirect() {
            QuickSortParameter parameter = getParameter();
            return skip(parameter.start,parameter.end);
        }

        @Override
        public void runBeforeSplit() {
            QuickSortParameter parameter = getParameter();
            parameter.base = quick(parameter.start,parameter.end);
        }

        @Override
        public void split() {
            QuickSortParameter parameter = getParameter();
            addChildFrame(new QuickSortParameter(parameter.start,parameter.base-1));
            addChildFrame(new QuickSortParameter(parameter.base+1,parameter.end));
        }

        @Override
        public RecursiveFrame<QuickSortParameter, List<O>> newFrame(QuickSortParameter p) {
            return new QuickSortRecursiveFrame(p);
        }
    }
    private boolean skip(int start,int end){
        int d = end - start;
        if(d < 1){
            return true;
        } else if(d == 1){
            if(compare(start,end)){
                swap(end,start);
            }
            return true;
        }
        return false;
    }
    private int quick(int start,int end){
        int left = start;
        int right = end;
        O o = get(start);
        for(;left < right;){
            for(;left < right;right--){
                if(compare(o,right)){
                    swap(left++,right);
                    break;
                }
            }
            for(;left < right;left++){
                if(compare(left,o)){
                    set(right--,get(left));
                    break;
                }
            }

        }
        set(left,o);
        return left;
    }
    private void sort(int start,int end){
        if(skip(start,end)){
            return;
        }
        int base = quick(start,end);
        sort(start,base-1);
        sort(base+1,end);
    }

    @Override
    void doRun(List<O> source) {
        setList(source);
        RecursiveInstance recursiveInstance = new RecursiveInstance(new QuickSortRecursiveFrame(new QuickSortParameter(0,source.size()-1)));
        recursiveInstance.run();
        //sort(0,source.size()-1);
    }
}
