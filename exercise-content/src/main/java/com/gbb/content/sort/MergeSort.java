package com.gbb.content.sort;

import com.gbb.content.recursive.RecursiveFrame;
import com.gbb.content.recursive.RecursiveInstance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 归并排序
 * @author gaobinbin
 * @date 2020/06/25
 */
public class MergeSort<O> extends Sorter<O> {
    public MergeSort(Comparator<O> comparator) {super(comparator);}

    static class MergeSortParameter<O>{
        private List<O> source;
        private int start;
        private int mid;
        private int end;

        public MergeSortParameter(List<O> source) {
            this(source,0,source.size()-1);
        }

        public MergeSortParameter(List<O> source,int start,int end){
            this.source = source;
            this.start = start;
            this.end = end;
            this.mid = start+((end - start) >> 1);
        }
    }

    class MergeSortRecursiveFrame extends RecursiveFrame<MergeSortParameter<O>,List<O>>{

        public MergeSortRecursiveFrame(MergeSortParameter<O> oMergeSortParameter) {
            super(oMergeSortParameter);
        }

        @Override
        public boolean runDirect() {
            MergeSortParameter parameter = getParameter();
            int d = parameter.end - parameter.start;
            if(d == 1){
                if(compare(parameter.start,parameter.end)){
                    swap(parameter.end,parameter.start);
                }
                return true;
            } else return d == 0;
        }

        @Override
        public void runStack() {
            MergeSortParameter<O> parameter = getParameter();
            sort(parameter.start,parameter.mid,parameter.end);
        }

        @Override
        public void split() {
            MergeSortParameter parameter = getParameter();
            addChildFrame(new MergeSortParameter(parameter.source,parameter.start,parameter.mid-1));
            addChildFrame(new MergeSortParameter(parameter.source,parameter.mid,parameter.end));
        }

        @Override
        public RecursiveFrame<MergeSortParameter<O>, List<O>> newFrame(MergeSortParameter<O> p) {
            return new MergeSortRecursiveFrame(p);
        }
    }

    private void merge(int start,int end){
        int d = end - start;
        if(d == 1){
            if(compare(start,end)){
                swap(start,end);
            }
        } else if(d > 0){
            int mid = start+((end-start)>>1);
            merge(start,mid-1);
            merge(mid,end);
            sort(start,mid,end);
        }
    }

    private void sort(int start,int mid,int end){
        int left = start;
        int right = mid;
        List<O> l = new ArrayList<>(end-start+1);
        for(;;){
            if(compare(left,right)){
                l.add(get(right));
                right++;
            } else {
                l.add(get(left));
                left++;
            }
            if(left == mid){
                for(;right<=end;right++){
                    l.add(get(right));
                }
                break;
            } else if(right == end+1){
                for(;left<mid;left++){
                    l.add(get(left));
                }
                break;
            }
        }
        left = start;
        for(int i = 0;left<=end;left++){
            set(left,l.get(i++));
        }
    }
    @Override
    void doRun(List<O> source) {
        setList(source);
        //RecursiveInstance recursiveInstance = new RecursiveInstance(new MergeSortRecursiveFrame(new MergeSortParameter(source)));
        //recursiveInstance.run();
        merge(0,source.size()-1);
    }
}
