package it.training.scala;

public class SampleImpl implements SampleInterface {
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public String hello(String in) {
        return "Hello world";
    }


}
