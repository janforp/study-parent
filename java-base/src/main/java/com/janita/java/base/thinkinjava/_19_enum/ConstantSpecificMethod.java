package com.janita.java.base.thinkinjava._19_enum;

import java.text.DateFormat;
import java.util.Date;

/**
 * ConstantSpecificMethod
 *
 * @author zhucj
 * @since 20200528
 */
public enum ConstantSpecificMethod {
    DATE_TIME {
        String getInfo() {
            return DateFormat.getDateInstance().format(new Date());
        }

        String get() {
            return "";
        }
    },
    CLASSPATH {
        String getInfo() {
            return System.getenv("CLASSPATH");
        }

        String get() {
            return "";
        }
    },
    VERSION {
        String getInfo() {
            return System.getProperty("java.version");
        }

        String get() {
            return "";
        }
    };

    abstract String getInfo();

    abstract String get();

    public static void main(String[] args) {
        for (ConstantSpecificMethod csm : values()) {
            System.out.println(csm.getInfo());
            System.out.println(csm.get());
        }
    }
}