package com.gbb.content.recursive;

import com.gbb.content.exceptions.ExceptionConstantEnum;
import com.gbb.content.utils.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 递归工具
 * @author gaobinbin
 * @date 2020/06/25
 */
public class RecursiveInstance<Parameter,R> {
    private boolean useCache;
    private Map<Parameter,R> resultCache = new HashMap<>();
    private LinkedList<RecursiveFrame<Parameter,R>> frameCache = new LinkedList<>();
    private RecursiveFrame<Parameter,R> frame;
    public RecursiveInstance(RecursiveFrame<Parameter,R> frame){
        this(frame,true);
    }
    public RecursiveInstance(RecursiveFrame<Parameter,R> frame,boolean useCache){
        this.frame = frame;
        this.frame.setRecursiveInstance(this);
        this.useCache = useCache;
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
            if(useCache && resultCache.containsKey(currentFrame.getParameter())){
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
        if(useCache){
            resultCache.put(currentFrame.getParameter(),currentFrame.getResult());
        }
    }
    private void putFrameCache(RecursiveFrame<Parameter, R> frame){
        if(CollectionUtils.isNotEmpty(frame.getChildFrame())){
            frame.getChildFrame().forEach(f->frameCache.push(f));
        }
    }
    RecursiveFrame<Parameter,R> getFrame() {
        return frameCache.isEmpty()?null:frameCache.poll();
    }
}
