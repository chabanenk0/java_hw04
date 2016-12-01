package com.github.chabanenk0;

import com.github.chabanenk0.Entity.FileInformation;
import com.github.chabanenk0.Exception.WrongPathException;
import com.github.chabanenk0.Service.ArchiveBuilderService;
import com.github.chabanenk0.Service.FileFinderService;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by dmitry on 24.11.16.
 */
public class Main
{
    public static void main(String[] args) {

        FileFinderService fileFinderService = new FileFinderService();
        ArchiveBuilderService archiveBuilderService = new ArchiveBuilderService();
        Collection<FileInformation> filesCollection = null;
        try {
            filesCollection = fileFinderService.findFilesByType(".", "java");
            archiveBuilderService.addFiles(filesCollection);
            archiveBuilderService.pack("output.zip");
        } catch (WrongPathException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
