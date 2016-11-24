package com.github.chabanenk0.Service;

import com.github.chabanenk0.Entity.FileInformation;
import com.github.chabanenk0.Exception.WrongPathException;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by dmitry on 24.11.16.
 */
public class FileFinderService
{
    private int maxRecursion;

    public FileFinderService(int maxRecursion)
    {
        this.maxRecursion = maxRecursion;
    }

    public FileFinderService()
    {
        this.maxRecursion = 10;
    }


    public int getMaxRecursion() {
        return maxRecursion;
    }

    public void setMaxRecursion(int maxRecursion) {
        this.maxRecursion = maxRecursion;
    }

    public Collection<FileInformation> findFilesByType(String directory, String extension) throws WrongPathException {
        Collection <FileInformation> results = new LinkedList<FileInformation>();

        File directoryObject = new File(directory);
        if (directoryObject == null || !directoryObject.isDirectory()) {
            throw new WrongPathException("Wrong path! " + directory + " should be a directory.");
        }

        this.recursivelyFindFiles(directoryObject, extension, results, 0);

        return results;
    }

    private void recursivelyFindFiles(File directoryObject, String extension, Collection<FileInformation> results, int recursionNumber)
    {
        if (recursionNumber > this.maxRecursion) {
            return;
        }
        for(String fileName: directoryObject.list()) {
            // @todo check if necessary
            if ("." == fileName && ".." == fileName) {
                continue;
            }

            String fullFileName = directoryObject.getAbsoluteFile() + "/" +fileName;

            FileInformation fileInformation = new FileInformation(fullFileName, extension);
            File fileObject = new File(fullFileName);
            if (null != fileObject && fileObject.isDirectory()) {
                recursivelyFindFiles(fileObject, extension, results, recursionNumber + 1);
            } else {
                // fix bug if the directory has the appropriate "extension"
                if (this.checkFileExtension(fileName, extension)) {
                    results.add(fileInformation);
                }
            }
        }
    }

    private boolean checkFileExtension(String fileName, String properExtension)
    {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }

        return extension.equals(properExtension);
    }
}
