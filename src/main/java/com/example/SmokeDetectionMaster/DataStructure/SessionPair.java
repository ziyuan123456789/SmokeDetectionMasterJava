package com.example.SmokeDetectionMaster.DataStructure;

/**
 * @author ziyuan
 * @since 2024.03
 */
public class SessionPair<T, U> {

    private T first;
    private U second;

    public SessionPair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "SessionPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
