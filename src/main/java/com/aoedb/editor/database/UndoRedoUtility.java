package com.aoedb.editor.database;

import java.util.Stack;

public class UndoRedoUtility {

    private static final Stack<FunctionValue> undoStack =  new Stack<>();
    private static final Stack<FunctionValue> redoStack =  new Stack<>();


    public static int undoSize(){
        return undoStack.size();
    }

    public static int redoSize(){
        return redoStack.size();
    }

    public static void pushUndo(CustomFunction function){
        FunctionValue undoItem = new FunctionValue(function);
        undoStack.push(undoItem);
        redoStack.clear();
    }

    private static  void pushUndo(FunctionValue functionValue){
        undoStack.push(functionValue);
    }

    private static void pushRedo(FunctionValue functionValue){
        redoStack.push(functionValue);
    }

    public static void performUndo(){
        if (!undoStack.isEmpty()) {
            FunctionValue p = undoStack.pop();
            p.callFunction();
            pushRedo(p);
        }
    }

    public static void performRedo(){
        if (!redoStack.isEmpty()) {
            FunctionValue p = redoStack.pop();
            p.callFunction();
            pushUndo(p);
        }
    }

    private static class FunctionValue{
        private final CustomFunction function;
        public FunctionValue(CustomFunction function){
            this.function = function;
        }

        public void callFunction(){
            function.function();
        }
    }

    public interface CustomFunction {
        void function();
    }

}
