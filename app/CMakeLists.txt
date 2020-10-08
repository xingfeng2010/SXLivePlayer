# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(
	PATH_TO_MEDIACORE
	CACHE STRING ""
)
set(PATH_TO_NATIVE ${PATH_TO_MEDIACORE}/src/main/cpp)
set(PATH_TO_JNI_LAYER ${PATH_TO_NATIVE}/video_player/jni)
set(PATH_TO_THIRDPARTY ${PATH_TO_NATIVE}/3rdparty)
set(PATH_TO_PRE_BUILT ${PATH_TO_THIRDPARTY}/prebuilt/${ANDROID_ABI})

add_definitions("
-DGL_GLEXT_PROTOTYPES
-DEGL_EGLEXT_PROTOTYPES
")

ADD_SUBDIRECTORY(src/main/cpp)

include_directories(BEFORE ${PATH_TO_THIRDPARTY}/ffmpeg/include/)
include_directories(${PATH_TO_NATIVE}/)
include_directories(${PATH_TO_NATIVE}/libcommon/)
include_directories(${PATH_TO_NATIVE}/live_show_video_player/)
include_directories(${PATH_TO_NATIVE}/video_player/)

file(GLOB FILES_JNI_LAYER "${PATH_TO_JNI_LAYER}/*.cpp")
file(GLOB FILES_LIVE_JNI_LAYER "${PATH_TO_NATIVE}/live_show_video_player/jni/*.cpp")


add_library(libsongstudio SHARED
        ${FILES_JNI_LAYER}
		${FILES_LIVE_JNI_LAYER}
        )

target_link_libraries( libsongstudio
                       log
                       android
                       GLESv2
                       EGL
                       z
                       OpenSLES
                       av-engine
                       # 引入ffmpeg相关静态库
                       ${PATH_TO_PRE_BUILT}/libavfilter.a
                       ${PATH_TO_PRE_BUILT}/libavformat.a
                       ${PATH_TO_PRE_BUILT}/libavcodec.a
                       ${PATH_TO_PRE_BUILT}/libpostproc.a
                       ${PATH_TO_PRE_BUILT}/libswresample.a
                       ${PATH_TO_PRE_BUILT}/libswscale.a
                       ${PATH_TO_PRE_BUILT}/libavutil.a
                       ${PATH_TO_PRE_BUILT}/libpostproc.a
                       ${PATH_TO_PRE_BUILT}/libfdk-aac.a
                       ${PATH_TO_PRE_BUILT}/libvo-aacenc.a
                       ${PATH_TO_PRE_BUILT}/libx264.a
                       )