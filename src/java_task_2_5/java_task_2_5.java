package java_task_2_5;

import javax.swing.*;
import java.util.Arrays;

public class java_task_2_5 {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void method1(float[] arr, String method, int startCounter){

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int k = 0; k < arr.length; k++) {
            int i = startCounter + k;
            arr[k] = (float)(arr[k] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(method + ", расчет:" + (System.currentTimeMillis() - a));
    }

    public static void method2()
    {
        float[] arr = new float[size];
        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        System.out.println("Метод 2, время разделения массива: " + (System.currentTimeMillis() - a));
        Thread t1 = new Thread(() -> method1(a1, "Метод 2", 0));
        Thread t2 = new Thread(() -> method1(a2, "Метод 2", h));
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        a = System.currentTimeMillis();
        System.out.println("Метод 2, начало склеивания: " + System.currentTimeMillis());
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Метод 2, время склеивания массива: " + (System.currentTimeMillis() - a));
        //System.out.println(Arrays.toString(arr));

    }

    public static void main(String[] args) {
        float[] arr = new float[size];
        method1(arr, "Метод 1", 0);
        //System.out.println(Arrays.toString(arr));
        method2();
    }
}
