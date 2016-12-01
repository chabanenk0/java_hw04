package com.github.chabanenk0.Service;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.LinkedList;

/**
 * FileFilter for filtering by allowed extensions list
 * If file has any of the extensions specified - it is allowed
 */
public class ExtensionFileFilter implements FileFilter
{
    private Collection<String> allowedExtensions;

    public ExtensionFileFilter()
    {
        allowedExtensions = new LinkedList<String>();
    }

    public void addAllowedExtension(String extension)
    {
        allowedExtensions.add(extension);
    }

    public boolean accept(File pathname) {
        String fileName = pathname.getName();
        for(String extension: this.allowedExtensions) {
            if (this.checkFileExtension(fileName, extension)) {
                return true;
            }
        }

        return false;
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
