package com.github.chabanenk0.Service;

import com.github.chabanenk0.Entity.FileInformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dchabanenko on 01.12.16.
 */
public class ArchiveBuilderService
{
    private Collection<FileInformation> files;

    public ArchiveBuilderService()
    {
        this.files = new LinkedList<FileInformation>();
    }
    public void addFileItemToArchive(FileInformation file)
    {
        this.files.add(file);
    }

    public void addFiles(Collection<FileInformation> collection)
    {
        this.files.addAll(collection);
    }

    public void clearFiles()
    {
        this.files.clear();
    }

    public void pack(String archiveFileName) throws IOException {
        FileOutputStream archiveFileStream = new FileOutputStream(new File(archiveFileName));
        ZipOutputStream zipOutputStream = new ZipOutputStream(archiveFileStream);
        for(FileInformation fileInformation: this.files) {
            zipOutputStream.putNextEntry(new ZipEntry((fileInformation.path)));
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }
}
