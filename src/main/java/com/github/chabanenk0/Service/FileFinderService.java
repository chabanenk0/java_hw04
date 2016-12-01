package com.github.chabanenk0.Service;

import com.github.chabanenk0.Entity.FileInformation;
import com.github.chabanenk0.Exception.WrongPathException;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by dmitry on 24.11.16.
 */
public class FileFinderService
{
    private int maxRecursion;

    private FileFilter fileFilter;

    public FileFinderService()
    {
        this.maxRecursion = 10;
        this.fileFilter = new ExtensionFileFilter();
    }

    public FileFinderService(int maxRecursion)
    {
        this.maxRecursion = maxRecursion;
        this.fileFilter = new ExtensionFileFilter();
    }

    public FileFinderService(int maxRecursion, FileFilter fileFilter)
    {
        this.maxRecursion = maxRecursion;
        this.fileFilter = fileFilter;
    }

    public int getMaxRecursion() {
        return maxRecursion;
    }

    public void setMaxRecursion(int maxRecursion) {
        this.maxRecursion = maxRecursion;
    }

    public Collection<FileInformation> findFilesByType(String directory) throws WrongPathException {
        Collection <FileInformation> results = new LinkedList<FileInformation>();

        File directoryObject = new File(directory);
        if (directoryObject == null || !directoryObject.isDirectory()) {
            throw new WrongPathException("Wrong path! " + directory + " should be a directory.");
        }

        this.recursivelyFindFiles(directoryObject, results, 0);

        return results;
    }

    private void recursivelyFindFiles(File directoryObject, Collection<FileInformation> results, int recursionNumber)
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

            FileInformation fileInformation = new FileInformation(fullFileName);
            File fileObject = new File(fullFileName);
            if (null != fileObject && fileObject.isDirectory()) {
                recursivelyFindFiles(fileObject, results, recursionNumber + 1);
            } else {
                if (!fileObject.isDirectory() && this.fileFilter.accept(fileObject)) {
                    results.add(fileInformation);
                }
            }
        }
    }
}
