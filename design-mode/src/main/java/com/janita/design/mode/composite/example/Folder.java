package com.janita.design.mode.composite.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明：Folder
 *
 * @author zhucj
 * @since 20200423
 */
public class Folder extends FileComponent {

    private final String name;

    private final List<FileComponent> componentList = new ArrayList<>();

    public Integer level;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add(FileComponent component) {
        this.componentList.add(component);
    }

    @Override
    public void remove(FileComponent component) {
        this.componentList.remove(component);
    }

    @Override
    public void print() {
        System.out.println(this.getName());
        if (this.level == null) {
            this.level = 1;
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < this.level; i++) {
            prefix.append("\t- ");
        }
        for (FileComponent component : this.componentList) {
            if (component instanceof Folder) {
                ((Folder) component).level = this.level + 1;
            }
            System.out.print(prefix);
            component.print();
        }
        this.level = null;
    }
}
