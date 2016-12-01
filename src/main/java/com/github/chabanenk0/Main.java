package com.github.chabanenk0;

import com.github.chabanenk0.Entity.FileInformation;
import com.github.chabanenk0.Exception.WrongPathException;
import com.github.chabanenk0.Service.ArchiveBuilderService;
import com.github.chabanenk0.Service.ExtensionFileFilter;
import com.github.chabanenk0.Service.FileFinderService;

import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by dmitry on 24.11.16.
 */
public class Main
{
    /**
     * Prepare the filter for audiofiled
     * @return ExtensionFileFilter
     */
    private static ExtensionFileFilter prepareFilterForAudioFiles()
    {
        ExtensionFileFilter fileFilter = new ExtensionFileFilter();
        fileFilter.addAllowedExtension("mp3");
        fileFilter.addAllowedExtension("wav");
        fileFilter.addAllowedExtension("wma");
        fileFilter.addAllowedExtension("java");

        return fileFilter;
    }

    private static void findAndArchiveFilesByFilter(String archiveFileName, String lookupDirectory, FileFilter fileFilter)
    {
        int maxRecursion = 100;
        FileFinderService fileFinderService = new FileFinderService(maxRecursion, fileFilter);
        ArchiveBuilderService archiveBuilderService = new ArchiveBuilderService();
        Collection<FileInformation> filesCollection = null;
        try {
            filesCollection = fileFinderService.findFilesByType(lookupDirectory);
            archiveBuilderService.addFiles(filesCollection);
            archiveBuilderService.pack(archiveFileName);
        } catch (WrongPathException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        String lookupDirectory = ".";
        String audioArchiveFileName = "audio.zip";
        FileFilter audioFileFilter = prepareFilterForAudioFiles();
        findAndArchiveFilesByFilter(audioArchiveFileName, lookupDirectory, audioFileFilter);
    }
}
