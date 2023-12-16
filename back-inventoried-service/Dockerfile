# Simple Dockerfile adding Maven and GraalVM Native Image compiler to the standard
# https://github.com/graalvm/container/pkgs/container/graalvm-ce image
FROM ghcr.io/graalvm/graalvm-ce:ol7-java17-22.3.0-b2

ADD . /build
WORKDIR /build

# For SDKMAN to work we need unzip & zip
RUN yum install -y unzip zip

RUN \
    # Install SDKMAN
    curl -s "https://get.sdkman.io" | bash; \
    source "$HOME/.sdkman/bin/sdkman-init.sh"; \
    sdk install maven; \
    # Install GraalVM Native Image
    gu install native-image;

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn --version

RUN native-image --version

RUN source "$HOME/.sdkman/bin/sdkman-init.sh" && mvn -B clean package -P native --no-transfer-progress


# We use a Docker multi-stage build here in order that we only take the compiled native Spring Boot App from the first build container
FROM oraclelinux:7-slim

# Add Spring Boot Native app back-login-service to Container
COPY --from=0 "/build/target/back-tokens-service" back-tokens-service

# Fire up our Spring Boot Native app by default
CMD [ "sh", "-c", "./back-tokens-service -Dserver.port=$PORT" ]