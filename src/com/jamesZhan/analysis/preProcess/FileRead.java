package com.jamesZhan.analysis.preProcess;

import com.jamesZhan.entity.FwloganalysisEntity;
import com.jamesZhan.entity.RawfwlogEntity;
import org.junit.Test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static com.jamesZhan.analysis.preProcess.ParseLogRegex.ParseLog;
import static com.jamesZhan.analysis.preProcess.ParseRawLogRegex.ParseRawLog;

/**
 * Created by 16255 on 2017/7/29.
 */
public class FileRead {
    public static List<FwloganalysisEntity> fwlogs = new LinkedList<>();
    public static List<RawfwlogEntity> rawFwlogs = new LinkedList<>();

    @Test
    public static void readFile(File file){


        BufferedReader reader = null;
        try{
            //to read the chinese
            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"gbk");

            reader = new BufferedReader(read);//读文件
            String tempString = null;
            //has next line
            while((tempString = reader.readLine())!=null) {

//                System.out.println(ParseRawLog(tempString));
                fwlogs.add(ParseLog(tempString));
                rawFwlogs.add(ParseRawLog(tempString));
//
            }
            //end of read the file
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(reader!=null)
                try{
                    reader.close();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
        }
    }
    public static void main(String[] args){
//        readfile(new File("E:\\log\\log1.txt"));
    }
}
