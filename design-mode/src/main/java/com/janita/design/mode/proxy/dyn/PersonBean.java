package com.janita.design.mode.proxy.dyn;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface PersonBean {

    String getName();

    String getGender();

    String getInterests();

    int getHotOrNotRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setHotOrNotRating(int rating);
}
