package com.janita.java.base.thinkinjava._18_io.file;

import com.janita.java.base.thinkinjava.util.PPrint;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 类说明：Directory
 *
 * @author zhucj
 * @since 20200528
 */
public final class Directory {

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) { // Overloaded
        return local(new File(path), regex);
    }

    // A two-tuple for returning a pair of objects:
    public static class TreeInfo implements Iterable<File> {

        public List<File> files = new ArrayList<File>();

        public List<File> dirs = new ArrayList<File>();

        // The default iterable element is the file list:
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    public static TreeInfo walk(String start, String regex) { // Begin recursion
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) { // Overloaded
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(File start) { // Everything
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        File file = new File(start);
        return recurseDirs(file, ".*");
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        File[] listFiles = startDir.listFiles();
        if (listFiles == null) {
            return result;
        }
        for (File item : listFiles) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else // Regular file
                if (item.getName().matches(regex)) {
                    result.files.add(item);
                }
        }
        return result;
    }

    // Simple validation test:
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println(walk("/Users/janita/code/studyCode/sp/java-base/src/main/java/com/janita/java/base/thinkinjava/_19_io"));
        } else {
            for (String arg : args) {
                System.out.println(walk(arg));
            }
        }
    }
}