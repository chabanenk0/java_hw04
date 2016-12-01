package com.github.chabanenk0.Entity;

/**
 * Created by dmitry on 24.11.16.
 */
public class FileInformation
{
    public String path;
    public String extension;

    public FileInformation(String path, String extension)
    {
        this.path = path;
        this.extension = extension;
    }

    public FileInformation(String path)
    {
        this.path = path;
        this.extension = ""; //@todo implement extension parsing
    }
}
