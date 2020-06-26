package com.gbb.content.recursive;

import java.util.LinkedList;
import java.util.List;

/**
 * 递归帧
 * @author gaobinbin
 * @date 2020/06/25
 */
public abstract class RecursiveFrame<Parameter,R>{
    private List<RecursiveFrame<Parameter,R>> childFrame;
    private Parameter parameter;
    private R result;
    private boolean flag = true;
    private RecursiveInstance<Parameter,R> recursiveInstance;
    public RecursiveFrame(Parameter parameter) {this.parameter=parameter;}
    public Parameter getParameter(){return this.parameter;}
    public void setResult(R result){this.result = result;}
    public R getResult(){return this.result;};
    protected List<RecursiveFrame<Parameter,R>> getChildFrame(){return childFrame;}
    protected void clear(){
        this.childFrame = null;
        result = null;
        flag = true;
        doClear();
    }
    void setRecursiveInstance(RecursiveInstance<Parameter, R> recursiveInstance) {this.recursiveInstance = recursiveInstance;}
    protected void addChildFrame(Parameter parameter){
        RecursiveFrame<Parameter,R> frame = recursiveInstance.getFrame();
        if (null == frame) {
            frame = newFrame(parameter);
            frame.setRecursiveInstance(this.recursiveInstance);
        } else {
            frame.clear();
            frame.parameter = parameter;
        }
        this.childFrame.add(frame);
    }
    boolean run(){
        if(flag){
            if(runDirect()){
                return true;
            }
            childFrame = new LinkedList<>();
            split();
            flag = false;
            return false;
        }
        runStack();
        return true;
    }
    public void doClear(){
        parameter = null;
    }
    public abstract boolean runDirect();
    public abstract void runStack();
    public abstract void split();
    public abstract RecursiveFrame<Parameter,R> newFrame(Parameter p);
}
