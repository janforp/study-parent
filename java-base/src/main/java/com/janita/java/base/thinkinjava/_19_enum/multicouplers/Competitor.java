package com.janita.java.base.thinkinjava._19_enum.multicouplers;

/**
 * Competitor
 *
 * @author zhucj
 * @since 20200528
 */
public interface Competitor<T extends Competitor<T>> {

    Outcome compete(T competitor);
}