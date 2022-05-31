package ru.otus.hw;

class TestLogging implements MyClassInterface {
    @Log
    public void calculation(int param, int param2) {
        System.out.println(param);
    };
}


