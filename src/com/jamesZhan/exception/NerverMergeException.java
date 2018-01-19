package com.jamesZhan.exception;

/**
 * Created by 16255 on 2017/8/3.
 */
public class NerverMergeException extends Exception{
    public NerverMergeException(){
        super("need to merge!");
    }
    public NerverMergeException(String firstTime, String secondTime){
        super("from" + firstTime + "to" + secondTime + "need to merge");
    }
}
