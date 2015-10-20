package com.turner;

import java.io.*;


/**
 * Utility class that prints the directory structure to standard output
 * showing the composition of nested files and subdirectories.
 */
public class PrintDirectoryStructure
{
    private static int nLevel = 0;
    /**
     * Prints the structure for the file whose path name is given in arg[0].
     */
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            printUsage();
            System.exit(-1);
        }

        String pathName = args[0];
        File file = new File(pathName);

        if (file.exists())
            printTree(file);
        else
            System.out.println("*** File " + pathName + " does not exist. ***");
    }


    public static void printTree(File file)
    {
        if(!file.isDirectory()){
            //nLevel++;
            printFile(file, nLevel);
        }
        else{ // File is a directory
            //nLevel++;
            printDirectory(file, nLevel);
            File[] fileList = file.listFiles();
            nLevel++;
            for(File f : fileList) {
                //nLevel--;
                printTree(f);
            }
            nLevel--;
        }
    }


    private static void printDirectory(File dir, int nestingLevel)
    {
        System.out.println(getIndentString(nestingLevel)+"+ "+dir.toString());
    }


    private static void printFile(File file, int nestingLevel)
    {
        System.out.println(getIndentString(nestingLevel)+"- "+file.toString());
    }


    private static String getIndentString(int nestingLevel)
    {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < nestingLevel; i++)
            s.append("   ");

        return s.toString();
    }


    private static void printUsage()
    {
        System.out.println("Usage: (<path>)");
        System.out.println("       where <path> is the path of a file or directory");
        System.out.println();
    }
}