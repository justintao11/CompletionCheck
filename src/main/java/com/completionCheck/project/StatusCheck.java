package com.completionCheck.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StatusCheck {

    //https://alvinalexander.com/blog/post/java/how-open-read-file-java-string-array-list
    public static int checkTODOsAndPrintStatements(String filename){
        int issueCount = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                //TODO: Checks can be made more robust
                if (line.contains("//TODO") || line.contains("//todo") || line.contains("System.out.print")){
                    issueCount++;
                }
            } reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
        }
        return issueCount;
    }

    //https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
    //Modified to return output of command as String
    public static int checkCheckStyle() throws java.io.IOException {
        int issueCount = 0;
        Runtime rt = Runtime.getRuntime();
        String cmd = "java -jar checkstyle-8.14-all.jar -c /sun_checks.xml test.java";
        Process proc = rt.exec(cmd);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String s;
        while ((s = stdInput.readLine()) != null) {
            if(s.contains("ERROR")){
                issueCount++;
//                System.out.println(s);
            }
        }
        return issueCount;
    }

    public static List<Integer> checkFiles(List<String> files) {
        List<Integer> scores = new ArrayList<>();
        for(String s:files) {
            scores.add(checkTODOsAndPrintStatements(s));
        }
        return scores;
    }
}
