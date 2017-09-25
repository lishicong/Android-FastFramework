//
// Created by lishicong on 2017/3/9.
//

#include <jni.h> 
#include "MyLog.h"
#include "com_fast_framework_ndk_AESHelper.h"  

const char *aes_key = "1234567890abcdef";

JNIEXPORT jstring JNICALL Java_com_fast_framework_ndk_AESHelper_getAESKey(
        JNIEnv *env, jclass jclass1) {

    LOGI("jni log :%s", " hello");

    return env->NewStringUTF(aes_key);
}