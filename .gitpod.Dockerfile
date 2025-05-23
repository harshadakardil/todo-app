FROM gitpod/workspace-base

# Install Java 17
RUN sudo apt-get update && \
    sudo apt-get install -y openjdk-17-jdk && \
    sudo update-alternatives --set java /usr/lib/jvm/java-17-openjdk-amd64/bin/java && \
    sudo apt-get clean && \
    sudo rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Install Gradle
RUN SDKMAN_DIR="/home/gitpod/.sdkman" && \
    curl -s "https://get.sdkman.io" | bash && \
    bash -c "source $SDKMAN_DIR/bin/sdkman-init.sh && sdk install gradle 8.5"