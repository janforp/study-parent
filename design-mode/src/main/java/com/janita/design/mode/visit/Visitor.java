package com.janita.design.mode.visit;

/**
 * 类说明：Visitor
 *
 * 不同的处理逻辑有不同的具体访问逻辑
 *
 * @author zhucj
 * @since 20200423
 */
public interface Visitor {

    /**
     * 访问基lei
     *
     * @param element 被访问者
     */
    void visit(Element element);
}
