package com.janita.design.mode.visit;

/**
 * 类说明：CompensationVisitor
 * 不同的处理逻辑有不同的具体访问逻辑
 *
 * 此处为补偿金处理逻辑
 *
 * @author zhucj
 * @since 20200423
 */
public class CompensationVisitor implements Visitor {

    @Override
    public void visit(BaseElement element) {
        Employee employee = (Employee) element;
        System.out.println(employee.getName() + "'的补偿金是： " +
                (employee.getDegree() * employee.getVacationDays() * 10));
    }
}
