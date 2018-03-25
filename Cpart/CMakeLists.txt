cmake_minimum_required(VERSION 3.9)
project(Cpart)

set(CMAKE_CXX_STANDARD 17)

find_package(JNI REQUIRED)

include_directories(${JNI_INCLUDE_DIRS})

link_directories(${JAVA_JVM_LIBRARY})

set(SOURCE_FILES
        start.h
        implementation.cpp
        )
add_library(clib SHARED ${SOURCE_FILES})
