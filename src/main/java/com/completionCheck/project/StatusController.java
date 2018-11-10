package com.completionCheck.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StatusController {

    //TODO: import file? Currently test file just part of directory
    //https://github.com/spring-guides/gs-uploading-files

    //TODO: add functionality, i.e. does it run? code coverage? linters? todo/printstatments?
    //TODO: format output - use source of issue and number of occurences in visualization

    @RequestMapping("/check")
    public String checkStatus(Model model){
        List<Integer> issues = new ArrayList<>();

        try {
            issues.add(StatusCheck.checkCheckStyle());
            issues.add(StatusCheck.checkTODOsAndPrintStatements("test.java"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("issues", issues);
        return "check.html";
//        return "There are " + Integer.toString(issues.get(0))
//                + " checkstyle issues and " + Integer.toString(issues.get(1)) + " pending TODOS";
    }
}
