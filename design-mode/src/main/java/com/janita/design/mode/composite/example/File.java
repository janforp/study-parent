package com.janita.design.mode.composite.example;

/**
 * 类说明：File
 *
 * @author zhucj
 * @since 20200423
 */
public class File extends FileComponent {

    private final String name;

    private final String content;

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print() {
        System.out.println(this.getName());
    }

    @Override
    public String getContent() {
        return this.content;
    }
}
