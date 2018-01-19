package com.jamesZhan.analysis.preProcess;



import com.jamesZhan.entity.FwloganalysisEntity;
import com.jamesZhan.exception.fileTooLarge;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jamesZhan.analysis.preProcess.ParseLogRegex.ParseLog;

/**
 * Created by 16255 on 2017/7/10.
 * @author jamesZhan
 */
public class ReadLogFile {
    private File logFile = null;
    private static long newFileLogSize = 0;//the new file's size
    private static long lastFileLogSize = 0;//the last log file's size

    public static List<FwloganalysisEntity> fwlogs = new LinkedList<>();

    public ReadLogFile(File logFile){
        this.logFile = logFile;
        this.lastFileLogSize = logFile.length();
    }

    public static void monitorLogFileIncrease(File file){
        lastFileLogSize = file.length();
        //create a new thread
        new Thread(()->{

            while(true){
                //the time of the thread's sleep
                try{
                    //the thread sleep 1 second
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                newFileLogSize = file.length();
                //compare the logfile's size
                if(newFileLogSize >= lastFileLogSize){

                    RandomAccessFile randomFile = null;
                    try{
                        randomFile = new RandomAccessFile(file,"r");
                        randomFile.seek(lastFileLogSize);
                        while(true){
                            String nextLine = randomFile.readLine();
                            //the nextLine add to the linkList
                            if(nextLine != null && !nextLine.equals("")){
                                //utf-8 的编码格式
                                nextLine = new String(nextLine.getBytes("ISO-8859-1"),"UTF-8");
                                FwloganalysisEntity newFlog = ParseLog(nextLine);
                                fwlogs.add(newFlog);

                                //prevent the fwlogs is so large
                                if(fwlogs.size() > 100 && fwlogs.size() < 1000){
                                    try{
                                        Thread.sleep(100);
                                    }catch(InterruptedException e1){
                                        e1.printStackTrace();
                                }
                            }else if(fwlogs.size() >= 1000 && fwlogs.size() < 10000){
                                    try{
                                        Thread.sleep(1000);
                                    }catch(InterruptedException e1){
                                        e1.printStackTrace();
                                    }
                                }else{
                                throw new fileTooLarge();
                                }
                            }
                        }
                    } catch(IOException e){
                        e.printStackTrace();
                    }catch (fileTooLarge e){
                        e.printStackTrace();
                    }finally{
                        try{
                            if(randomFile != null)
                                randomFile.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void main(String[] args){
        File file = new File("E:\\log\\log1.txt");
        ReadLogFile.monitorLogFileIncrease(file);
    }
}
