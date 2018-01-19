package com.jamesZhan.exception;

/**
 * Created by 16255 on 2017/7/10.
 * @author jamesZhan
 */
public class fileTooLarge extends Exception{
    public fileTooLarge(){
        super("the log file is so large");
    }
}
