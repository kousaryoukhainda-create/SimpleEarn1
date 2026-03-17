#!/bin/bash

# Gradle wrapper script
# This will download and use Gradle to build the project

set -e

APP_NAME="SimpleEarn"
APP_HOME="$(cd "$(dirname "$0")" && pwd)"

GRADLE_VERSION="8.4"
GRADLE_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
GRADLE_HOME="${APP_HOME}/.gradle/wrapper/gradle-${GRADLE_VERSION}"

# Download Gradle if not present
if [ ! -d "$GRADLE_HOME" ]; then
    echo "Downloading Gradle ${GRADLE_VERSION}..."
    mkdir -p "${APP_HOME}/.gradle/wrapper"
    cd "${APP_HOME}/.gradle/wrapper"

    if command -v curl &> /dev/null; then
        curl -# -L -o gradle-${GRADLE_VERSION}-bin.zip "$GRADLE_URL"
    elif command -v wget &> /dev/null; then
        wget -q -O gradle-${GRADLE_VERSION}-bin.zip "$GRADLE_URL"
    else
        echo "Error: Neither curl nor wget found. Please install one of them."
        exit 1
    fi

    unzip -q gradle-${GRADLE_VERSION}-bin.zip
    rm gradle-${GRADLE_VERSION}-bin.zip
fi

# Return to project root before running gradle
cd "$APP_HOME"

# Run gradle
exec "$GRADLE_HOME/bin/gradle" "$@"
