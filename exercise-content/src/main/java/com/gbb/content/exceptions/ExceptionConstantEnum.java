package com.gbb.content.exceptions;

/**
 * 自定义异常枚举
 * @author gaobinbin
 * @date 2020/06/25
 */
public class ExceptionConstantEnum {
    private static class IntegerCounter{
        private int i;
        public IntegerCounter(int i) {this.i = i;}
        public int get() {return ++i;}
    }
    private final static IntegerCounter RECURSIVE_CODE_COUNTER = new IntegerCounter(3000);
    public static CustomRuntimeException RECURSIVE_FRAME_NULL(){
        return ExceptionConstantsEnumInstance.RECURSIVE_FRAME_NULL.exception;
    }
    public enum ExceptionConstantsEnumInstance{
        //RECURSIVE
        RECURSIVE_FRAME_NULL(RECURSIVE_CODE_COUNTER,"recursive frame is nul");
        private int code;
        private String message;
        private CustomRuntimeException exception;
        ExceptionConstantsEnumInstance(IntegerCounter counter, String message) {
            this.code = counter.get();
            this.message = message;
            this.exception = new CustomRuntimeException(this.code,message);
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public CustomRuntimeException getException() {
            return exception;
        }
    }
}
