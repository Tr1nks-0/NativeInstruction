#include <iostream>
#include "start.h"

JNIEXPORT void JNICALL Java_com_tr1nks_MyNative_voidMethod(JNIEnv *env, jobject obj) {
    std::cout << "native" << std::endl;
}

JNIEXPORT void JNICALL Java_com_tr1nks_MyNative_staticVoidMethod(JNIEnv *env, jclass jcl) {
    std::cout << "static native" << std::endl;
}

JNIEXPORT jint JNICALL Java_com_tr1nks_MyNative_intMethod(JNIEnv *env, jobject jobj, jint jin) {
    std::cout << "int native -" << jin << std::endl;
    return 2;
}

JNIEXPORT jint JNICALL Java_com_tr1nks_MyNative_staticIntMethod(JNIEnv *env, jclass jcl, jint jin) {
    std::cout << "static int native -" << jin << std::endl;
    return 3;
}

JNIEXPORT jstring JNICALL Java_com_tr1nks_MyNative_stringMethod(JNIEnv *env, jobject jobj, jstring jstr) {

}

JNIEXPORT jstring JNICALL Java_com_tr1nks_MyNative_staticStringMethod(JNIEnv *env, jclass jcl, jstring jstr) {

}

JNIEXPORT jintArray JNICALL Java_com_tr1nks_MyNative_intArrayMethod(JNIEnv *env, jobject jobj, jintArray jintarr) {

}

JNIEXPORT jintArray JNICALL Java_com_tr1nks_MyNative_staticIntArrayMethod(JNIEnv *env, jclass jcl, jintArray jintarr) {

}

JNIEXPORT jstring JNICALL Java_com_tr1nks_MyNative_stringArrayMethod(JNIEnv *env, jobject jobj, jobjectArray jobjarr) {

}

JNIEXPORT jstring JNICALL Java_com_tr1nks_MyNative_staticStringArrayMethod(JNIEnv *env, jclass jcl, jobjectArray jobjarr) {

}