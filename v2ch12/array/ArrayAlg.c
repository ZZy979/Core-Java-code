#include "ArrayAlg.h"

/** multiplies all elements in arr by scaleFactor */
JNIEXPORT void JNICALL Java_ArrayAlg_multiply(JNIEnv* env, jclass cl,
        jdoubleArray arr, jdouble scaleFactor) {
    double* a = (*env)->GetDoubleArrayElements(env, arr, NULL);
    jsize i;
    for (i = 0; i < (*env)->GetArrayLength(env, arr); i++)
        a[i] *= scaleFactor;
    (*env)->ReleaseDoubleArrayElements(env, arr, a, 0);
}
