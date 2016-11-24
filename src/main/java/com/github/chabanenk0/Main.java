package com.github.chabanenk0;

import com.github.chabanenk0.Entity.FileInformation;
import com.github.chabanenk0.Exception.WrongPathException;
import com.github.chabanenk0.Service.FileFinderService;

import java.util.Collection;

/**
 * Created by dmitry on 24.11.16.
 */
public class Main
{
    public static void main(String[] args) {

        FileFinderService fileFinderService = new FileFinderService();
        Collection<FileInformation> res = null;
        try {
            res = fileFinderService.findFilesByType(".", "java");
        } catch (WrongPathException e) {
            e.printStackTrace();
        }
        System.out.println(res);
    }
}
