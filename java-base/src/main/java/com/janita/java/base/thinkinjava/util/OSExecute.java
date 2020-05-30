package com.janita.java.base.thinkinjava.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 类说明：OSExecute
 *
 * @author zhucj
 * @since 20200528
 */
public class OSExecute {

    public static void command(String command) {
        boolean err = false;
        try {
            String[] strings = command.split(" ");
            ProcessBuilder processBuilder = new ProcessBuilder(strings);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader results = new BufferedReader(new InputStreamReader(inputStream));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }

            InputStream errorStream = process.getErrorStream();
            BufferedReader errors = new BufferedReader(new InputStreamReader(errorStream));
            // Report errors and return nonzero value
            // to calling process if there are problems:
            while ((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch (Exception e) {
            // Compensate for Windows 2000, which throws an
            // exception for the default command line:
            if (!command.startsWith("CMD /C")) {
                command("CMD /C " + command);
            } else {
                throw new RuntimeException(e);
            }
        }
        if (err) {
            throw new OSExecuteException("Errors executing " + command);
        }
    }
}
