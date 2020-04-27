package com.janita.design.mode.visit;

/**
 * 类说明：Element
 *
 * @author zhucj
 * @since 20200423
 */
public abstract class BaseElement {

    /**
     * 双重指派/双重分配
     * 被访问者基类
     *
     * @param visitor 访问者
     */
    abstract void accept(Visitor visitor);
}
