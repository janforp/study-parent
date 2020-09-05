package com.janita.java.ssy.jdk8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * _22_Student
 *
 * @author zhucj
 * @since 20200723
 */
@Data
@AllArgsConstructor
public class _22_Student {

    private String name;

    private int score;

    public static List<_22_Student> getList() {
        _22_Student zhangsan = new _22_Student("zhangsan", 80);
        _22_Student lisi = new _22_Student("lisi", 90);
        _22_Student wangwu = new _22_Student("wangwu", 100);
        _22_Student zhaoliu = new _22_Student("zhaoliu", 90);
        _22_Student zhaoliu2 = new _22_Student("zhaoliu", 90);

        return Arrays.asList(zhangsan, lisi, wangwu, zhaoliu, zhaoliu2);
    }
}
