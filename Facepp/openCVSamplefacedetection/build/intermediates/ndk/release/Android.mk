LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := detection_based_tracker
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	F:\ydxDemo\Facepp\openCVSamplefacedetection\src\main\jni\Android.mk \
	F:\ydxDemo\Facepp\openCVSamplefacedetection\src\main\jni\Application.mk \
	F:\ydxDemo\Facepp\openCVSamplefacedetection\src\main\jni\DetectionBasedTracker_jni.cpp \

LOCAL_C_INCLUDES += F:\ydxDemo\Facepp\openCVSamplefacedetection\src\main\jni
LOCAL_C_INCLUDES += F:\ydxDemo\Facepp\openCVSamplefacedetection\src\release\jni

include $(BUILD_SHARED_LIBRARY)
