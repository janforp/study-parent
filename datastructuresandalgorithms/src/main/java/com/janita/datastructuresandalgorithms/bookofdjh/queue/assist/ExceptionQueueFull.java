package com.janita.datastructuresandalgorithms.bookofdjh.queue.assist;

/**
 * ExceptionQueueEmpty
 *
 * @author zhucj
 * @since 20201126
 */
public class ExceptionQueueFull extends RuntimeException {

    public ExceptionQueueFull(String err) {
        super(err);
    }
}
