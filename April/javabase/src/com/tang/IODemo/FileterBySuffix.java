package com.tang.IODemo;

import java.io.FilenameFilter;
import java.io.*;

/**
 * Created by tang on 2017/4/5.
 */
public class FileterBySuffix implements FilenameFilter {
    private String suffix;
    public FileterBySuffix(String suffix)
    {
        super();
        this.suffix=suffix;
    }
    @Override
    public boolean accept(File dir,String name)
    {
        return name.endsWith(name);
    }
}
