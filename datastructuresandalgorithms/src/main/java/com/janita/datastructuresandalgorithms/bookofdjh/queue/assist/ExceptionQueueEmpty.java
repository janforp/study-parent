package com.janita.datastructuresandalgorithms.bookofdjh.queue.assist;

/**
 * ExceptionQueueEmpty
 *
 * @author zhucj
 * @since 20201126
 */
public class ExceptionQueueEmpty extends RuntimeException {

    public ExceptionQueueEmpty(String err) {
        super(err);
    }
}
