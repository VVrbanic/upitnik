package com.example.demo;

public class ResultInfo<T, V> {
    T resultName;
    V date;

    public ResultInfo(T resultName, V date) {
        this.resultName = resultName;
        this.date = date;
    }

    public T getResultName() {
        return resultName;
    }

    public void setResultName(T resultName) {
        this.resultName = resultName;
    }

    public V getDate() {
        return date;
    }

    public void setDate(V date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return resultName + "\n"
            +"Datum: " +date;
    }
}
