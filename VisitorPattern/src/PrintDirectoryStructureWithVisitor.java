//package com.turner

import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import static java.nio.file.FileVisitResult.*;

public class PrintDirectoryStructureWithVisitor extends SimpleFileVisitor<Path>
{
    /* Used for keeping track of nesting level for prints */
    private static int nestingLevel = 0;
    /**
      * Prints the structure for a given directory or file
     */
    public static void main(String[] args) throws IOException
    {
        if (args.length != 1)
        {
            printUsage();
            System.exit(-1);
        }

        String pathName = args[0];
        Path startingDir = Paths.get(pathName);

        PrintDirectoryStructureWithVisitor pdsv = new PrintDirectoryStructureWithVisitor();
        Files.walkFileTree(startingDir, pdsv);
    }

    /* Handles recursion logic and prints files, not directories */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes bfAttrs)
    {
        File f = new File(file.toString());
        if (f.isFile()){
            printFile(f);
        }
        else{
            nestingLevel++;
            for(File recF : f.listFiles()){
                Path nextFile = Paths.get(recF.toString());
                visitFile(nextFile, bfAttrs);
            }
        }
        return CONTINUE;
    }

    /* Before visiting a directory, print it out and
        update the nesting level for subfiles/subdirectories
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes bfAttrs)
    {
        printDirectory(dir.toFile());
        nestingLevel++;
        return CONTINUE;
    }

    /* We're done with this directory, so let's back up with our nesting level */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException ex){
        nestingLevel--;
        return CONTINUE;
    }

    /* Error handling */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException ex)
    {
        System.err.println(ex);
        return CONTINUE;
    }

    /* Usage for command line */
    private static void printUsage()
    {
        System.out.println("Usage: java com.turner.PrintDirectoryStructureWithVisitor(<path>)");
        System.out.println("       where <path> is the path of a file or directory");
        System.out.println();
    }

    private static String getIndentString(int nestingLevel)
    {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < nestingLevel; i++)
            s.append("   ");

        return s.toString();
    }

    /* Abstracted printer */
    private static String clean_printer(File dirOrFile){
        String[] sList = dirOrFile.toString().split("/");
        return sList[sList.length-1];
    }

    private static void printDirectory(File dir)
    {
        System.out.println(getIndentString(nestingLevel)+"+ "+clean_printer(dir));
    }


    private static void printFile(File file)
    {
        System.out.println(getIndentString(nestingLevel)+"- "+clean_printer(file));
    }
}
