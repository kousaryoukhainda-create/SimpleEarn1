#!/bin/bash

# Build SimpleEarn APK

echo "🔨 Building SimpleEarn APK..."
echo ""

if [ ! -f "gradlew" ]; then
    echo "❌ gradlew not found. Please run this script from project root."
    exit 1
fi

# Check for Android SDK
if [ -z "$ANDROID_SDK_ROOT" ] && [ -z "$ANDROID_HOME" ]; then
    echo "⚠️  Android SDK not found in environment."
    echo "   Set ANDROID_SDK_ROOT or ANDROID_HOME environment variable"
    echo ""
    echo "   Example:"
    echo "   export ANDROID_SDK_ROOT=/path/to/Android/Sdk"
    echo ""
    exit 1
fi

# Build debug APK
echo "📱 Building Debug APK..."
./gradlew assembleDebug

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ BUILD SUCCESSFUL!"
    echo ""
    echo "APK Location:"
    echo "  app/build/outputs/apk/debug/app-debug.apk"
    echo ""
else
    echo "❌ Build failed. Please check errors above."
    exit 1
fi

# Build release APK (optional)
read -p "Build Release APK? (y/n) " -n 1 -r
echo
if [[ $REPLY =~ ^[Yy]$ ]]; then
    echo "📱 Building Release APK..."
    ./gradlew assembleRelease
    
    if [ $? -eq 0 ]; then
        echo ""
        echo "✅ RELEASE BUILD SUCCESSFUL!"
        echo ""
        echo "APK Location:"
        echo "  app/build/outputs/apk/release/app-release.apk"
        echo ""
    fi
fi
