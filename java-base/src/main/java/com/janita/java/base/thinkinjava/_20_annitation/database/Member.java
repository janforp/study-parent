package com.janita.java.base.thinkinjava._20_annitation.database;

import lombok.Getter;

@Getter
@DBTable(name = "MEMBER")
public class Member {

    @SQLString(30)
    String firstName;

    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;
}
