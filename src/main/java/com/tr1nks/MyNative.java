package com.tr1nks;

public class MyNative {
    static {
        JarResourcesUtil.unpackLoadLib("libclib");
    }

    public static void main(String[] args) {
        staticVoidMethod();
    }

    public native void voidMethod();

    public static native void staticVoidMethod();

    public native int intMethod(int a);

    public static native int staticIntMethod(int a);

    public native String stringMethod(String a);

    public static native String staticStringMethod(String a);

    public native int[] intArrayMethod(int[] arr);

    public static native int[] staticIntArrayMethod(int[] arr);

    public native String stringArrayMethod(String[] arr);

    public static native String staticStringArrayMethod(String[] arr);
}
