package com.janita.design.mode.visit;

/**
 * 类说明：PrintNameVisitor
 * 通知参加会议
 *
 * @author zhucj
 * @since 20200423
 */
public class NoticeMeetingVisitor implements Visitor {

    @Override
    public void visit(Element element) {
        Employee employee = (Employee) element;
        System.out.println(employee.getName() + ", 开会啦");
    }
}
