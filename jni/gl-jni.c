#include <jni.h>
#include <GLES2/gl2.h>
#include "org_gleason_superhockey_screens_HockeyScreen.h"


JNIEXPORT jstring JNICALL Java_org_gleason_superhockey_screens_HockeyScreen_resetScreen(JNIEnv* env, jobject obj){
	glClear( GL_COLOR_BUFFER_BIT );
	return (*env)->NewStringUTF(env, "Hello World!");
}



