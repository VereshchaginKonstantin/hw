package ru.otus.hw;

class TestLogging implements MyClassInterface {
    @Log
    public void calculation(int param) {
        System.out.println(param);
    };
}


