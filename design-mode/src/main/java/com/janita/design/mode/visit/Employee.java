package com.janita.design.mode.visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类说明：Employee
 *
 * @author zhucj
 * @since 20200423
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends Element {

    private String name;

    private float income;

    private int vacationDays;

    private int degree;

    /**
     * 双重指派/双重分配
     *
     * @param visitor 访问者
     */
    @Override
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
