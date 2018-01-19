package com.jamesZhan.analysis.preProcess;


import com.jamesZhan.entity.FwloganalysisEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 16255 on 2017/7/8.
 * @author jamesZhan
 */
public class ParseLogRegex {
    public static FwloganalysisEntity ParseLog(String log) {
        FwloganalysisEntity fwlog = new FwloganalysisEntity();
        //第一个时间戳的表达式
        String FirstTimeStampRegex = "\\p{Upper}\\p{Lower}{2} (\\d{2} | \\d )(\\d{2}:){2}\\d{2}";
        Pattern p = Pattern.compile(FirstTimeStampRegex);
        Matcher m = p.matcher(log);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMM dd HH:mm:ss", Locale.ENGLISH);
//        if (m.find()) {
//            try {
//                Calendar a = Calendar.getInstance();
//                fwlog.setAnotherTimestamp(sdf.parse(a.get(Calendar.YEAR) + m.group(0)));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        //regex正则表达式
        //内网IP
        String internalIPRegex = "(\\d{1,3}\\.){3}\\d{1,3}";
        p = Pattern.compile(internalIPRegex);
        m = p.matcher(log);
        if (m.find()) {
            fwlog.setInternalIp(m.group(0));
        }

        //时间戳正则表达式
        String timeStampRegex = "\\d{4}(-\\d{2}){2}: (\\d{2}:){2}\\d{2}";
        p = Pattern.compile(timeStampRegex);
        m = p.matcher(log);
        if (m.find()) {
            sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
            String timeStamp1 = m.group(0);
            try {
                fwlog.setTimestamp(sdf.parse(timeStamp1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //源IP和端口
        String sourceIpAndPortRegex = "(\\d{1,3}\\.){3}\\d{1,3}\\(\\d+\\)->";
        p = Pattern.compile(sourceIpAndPortRegex);
        m = p.matcher(log);
        if (m.find()) {
            String IpAndPort = m.group(0);
            fwlog.setOriginalSrcIp(IpAndPort.substring(0, IpAndPort.indexOf("(")));
            fwlog.setOriginalSrcPort(IpAndPort.substring(IpAndPort.indexOf("(") + 1, IpAndPort.length() - 3));
        }

        //目标IP和PORT
        String DestIpAndPortRegex = "->(\\d{1,3}\\.){3}\\d{1,3}\\(\\d+\\)";
        p = Pattern.compile(DestIpAndPortRegex);
        m = p.matcher(log);
        if (m.find()) {
            String IpAndPort1 = m.group(0);
            fwlog.setOriginalDestIp(IpAndPort1.substring(2, IpAndPort1.indexOf("(")));
            fwlog.setOriginalDestPort(IpAndPort1.substring(IpAndPort1.indexOf("(") + 1, IpAndPort1.length() - 1));
        }

        //协议 protocol
        String protocalRegex = "协议：\\d{1,3}，";
        p = Pattern.compile(protocalRegex);
        m = p.matcher(log);
        if (m.find()) {
//            String--->int
            fwlog.setProtocolNumber(Integer.parseInt(m.group(0).substring(3, m.group(0).length() - 1)));
        }

        //安全域 safety domainp
        String safetyDomainRegex = "安全域：.+，";
        p = Pattern.compile(safetyDomainRegex);
        m = p.matcher(log);
        if (m.find()) {
            fwlog.setSafetyDomain(m.group().substring(4, m.group().length() - 1));
        }

        //动作 action
        p = Pattern.compile("动作：\\D{2}。");
        m = p.matcher(log);
        while (m.find()) {
            if (m.group().substring(3, m.group().length() - 1).equals("允许")) {
                fwlog.setAction(1);
            } else {
                fwlog.setAction(0);
            }
        }
        return fwlog;
    }
    public static void main(String[] args){
        String log = "Aug  1 00:00:00 11.116.14.155 2016-07-31: 23:42:39 FW_internetDMZ_Main:root 03-011-069-0017 Notice Session N/A rep=1 | 匹配到访问策略FOR_SERVER， 原始地址：172.21.181.82(58073)->212.117.201.1(53)， 协议：17，  转换后地址：172.22.181.82(58173)->202.127.211.1(53)， 安全域：outside->server， 动作：允许。";
        System.out.println(ParseLog(log));
    }
}
