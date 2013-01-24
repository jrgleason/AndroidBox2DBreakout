LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := gl-jni
LOCAL_SRC_FILES := gl-jni.c
LOCAL_LDLIBS := -lGLESv2
include $(BUILD_SHARED_LIBRARY)
include $(CLEAR_VARS)
LOCAL_MODULE    := game-gl
LOCAL_SRC_FILES := game-gl.c
LOCAL_LDLIBS := -lGLESv2
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libandroidgl20
LOCAL_SRC_FILES := libandroidgl20.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := libgdx
LOCAL_SRC_FILES := libgdx.so
include $(PREBUILT_SHARED_LIBRARY)
