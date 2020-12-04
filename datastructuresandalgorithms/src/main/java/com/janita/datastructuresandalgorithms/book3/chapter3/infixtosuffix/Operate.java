package com.janita.datastructuresandalgorithms.book3.chapter3.infixtosuffix;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

/**
 * Operate
 *
 * @author zhucj
 * @since 20201224
 */
@AllArgsConstructor
@Getter
public enum Operate {

    _PLUS('+', 3) {
        @Override
        public int operate(int left, int right) {
            return left + right;
        }
    },

    _MINUS('-', 3) {
        @Override
        public int operate(int left, int right) {
            return left - right;
        }
    },

    _X('*', 2) {
        @Override
        public int operate(int left, int right) {
            return left * right;
        }
    },

    _DIVIDE('/', 2) {
        @Override
        public int operate(int left, int right) {
            return left / right;
        }
    },

    _LEFT_BRACKET('(', 1) {
        @Override
        public int operate(int left, int right) {
            throw new UnsupportedOperationException();
        }
    },

    _RIGHT_BRACKET(')', 1) {
        @Override
        public int operate(int left, int right) {
            throw new UnsupportedOperationException();
        }
    },
    ;

    private char code;

    private int priority;

    public abstract int operate(int left, int right);

    public static final Set<Operate> TOTAL = Sets.newHashSet(EnumSet.allOf(Operate.class));

    public static Operate getByCode(char code) {
        for (Operate value : Operate.values()) {
            if (value.getCode() == (code)) {
                return value;
            }
        }
        return null;
    }

    public static boolean isOperate(char item) {
        Operate operate = getByCode(item);
        return operate != null;
    }

    public static boolean isDigital(char item) {
        return !isOperate(item);
    }
}