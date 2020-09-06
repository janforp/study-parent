package com.janita.java.ssy.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * _35_StreamSourceCode
 *
 * @author zhucj
 * @since 20200917
 */
public class _35_StreamSourceCode {

    public static void main(String[] args) {
        close1();
        System.out.println("-------------------");
        closeStream();
        System.out.println("-------------------");
        //closeStreamException();
        System.out.println("-------------------");
        //closeStreamSuppressedException();
        closeStreamNotSuppressedException();
    }

    private static void close1() {
        List<String> list = Arrays.asList("hello", "world");
        list.stream().onClose(() -> {
            System.out.println("aa");
        }).onClose(() -> {
            System.out.println("bb");
        }).forEach(System.out::println);
    }

    private static void closeStream() {
        List<String> list = Arrays.asList("hello", "world");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("close a");
            }).onClose(() -> {
                System.out.println("close b");
            }).forEach(System.out::println);
        }
    }

    private static void closeStreamException() {
        List<String> list = Arrays.asList("hello", "world");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("close a");
                throw new NullPointerException("first npe");
            }).onClose(() -> {
                System.out.println("close b");
            }).forEach(System.out::println);
        }
    }

    private static void closeStreamSuppressedException() {
        List<String> list = Arrays.asList("hello", "world");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("close a");
                throw new NullPointerException("first npe");
            }).onClose(() -> {
                System.out.println("close b");
                throw new NullPointerException("second npe");
            }).forEach(System.out::println);
        }
    }

    private static void closeStreamNotSuppressedException() {
        List<String> list = Arrays.asList("hello", "world");
        //同一个异常无法压制
        NullPointerException nullPointerException = new NullPointerException("npe");
        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("close a");
                throw nullPointerException;
            }).onClose(() -> {
                System.out.println("close b");
                throw nullPointerException;
            }).forEach(System.out::println);
        }
    }
}
