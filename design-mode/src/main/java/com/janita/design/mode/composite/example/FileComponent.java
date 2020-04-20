package com.janita.design.mode.composite.example;

/**
 * 类说明：FileComponent
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class FileComponent {

    public String getName() {
        throw new UnsupportedOperationException("不支持获取名称操作");
    }

    public void add(FileComponent component) {
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void remove(FileComponent component) {
        throw new UnsupportedOperationException("不支持删除操作");
    }

    public void print() {
        throw new UnsupportedOperationException("不支持打印操作");
    }

    public String getContent() {
        throw new UnsupportedOperationException("不支持获取内容操作");
    }
}
