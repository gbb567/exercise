package com.gbb.content.recursive;

import com.gbb.content.exceptions.ExceptionConstantEnum;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 递归工具
 * @author gaobinbin
 * @date 2020/06/25
 */
public class RecursiveInstance<Parameter,R> {
    private boolean useResultCache;
    private boolean useFrameCache;
    private Map<Parameter,R> resultCache = new HashMap<>();
    private Map<Parameter,RecursiveFrame<Parameter,R>> frameMap = new HashMap<>();
    private RecursiveFrame<Parameter,R> frame;
    public RecursiveInstance(RecursiveFrame<Parameter,R> frame){this(frame,false,true);}
    public RecursiveInstance(RecursiveFrame<Parameter,R> frame,boolean useFrameCache){this(frame,false,useFrameCache);}
    public RecursiveInstance(RecursiveFrame<Parameter,R> frame,boolean useResultCache,boolean useFrameCache){
        this.frame = frame;
        this.useResultCache = useResultCache;
        this.useFrameCache = useFrameCache;
        this.frame.setRecursiveInstance(this);
    }
    public R getResult(){return frame.getResult();}
    public void run(){
        if(null == frame){
            throw ExceptionConstantEnum.RECURSIVE_FRAME_NULL();
        }
        LinkedList<RecursiveFrame<Parameter,R>> frameStack = new LinkedList<>();
        frameStack.push(frame);
        RecursiveFrame<Parameter,R> currentFrame;
        while(!frameStack.isEmpty()){
            currentFrame = frameStack.peek();
            if(useResultCache && resultCache.containsKey(currentFrame.getParameter())){
                currentFrame.setResult(resultCache.get(currentFrame.getParameter()));
                putFrameCache(frameStack.pop());
            }else if(currentFrame.run()){
                putResultCache(currentFrame);
                putFrameCache(frameStack.pop());
            } else {
                currentFrame.getChildFrame().forEach(frameStack::push);
            }
        }
    }
    private void putResultCache(RecursiveFrame<Parameter, R> currentFrame){
        if(useResultCache){
            resultCache.put(currentFrame.getParameter(),currentFrame.getResult());
        }
    }
    private void putFrameCache(RecursiveFrame<Parameter, R> frame){
        if(useFrameCache){
            frameMap.put(frame.getParameter(),frame);
        }
    }
    RecursiveFrame<Parameter,R> getFrame(Parameter parameter) {
        return useFrameCache?frameMap.get(parameter):null;
    }
}
