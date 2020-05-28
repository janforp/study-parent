package com.janita.java.base.thinkinjava._18_io.file;

import java.io.File;
import java.io.IOException;

/**
 * 类说明：ProcessFiles
 *
 * @author zhucj
 * @since 20200528
 */
public class ProcessFiles {

    /**
     * 内部接口：提供了更多的上下文信息
     */
    public interface Strategy {

        void process(File file);
    }

    private Strategy strategy;

    private String ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0) {
                File file = new File(".");
                System.out.println("new File(\".\"); 的绝对路径：" + file.getAbsolutePath());
                processDirectoryTree(file);
            } else {
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory()) {
                        processDirectoryTree(fileArg);
                    } else {
                        // Allow user to leave off extension:
                        if (!arg.endsWith("." + ext)) {
                            arg += "." + ext;
                        }
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        //文件夹绝对路径
        String absolutePath = root.getAbsolutePath();
        //该文件夹下面的 .java或者其他传进来的后缀 的文件
        Directory.TreeInfo treeInfo = Directory.walk(absolutePath, ".*\\." + ext);
        for (File file : treeInfo) {
            File canonicalFile = file.getCanonicalFile();
            System.out.println(canonicalFile.getAbsolutePath() + " " + canonicalFile.isHidden());
            strategy.process(canonicalFile);
        }
    }

    // Demonstration of how to use it:
    public static void main(String[] args) {
        ProcessFiles processFiles = new ProcessFiles(System.out::println, "java");
        processFiles.start(args);
    }
}
