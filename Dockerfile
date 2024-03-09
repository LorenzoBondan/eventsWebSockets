FROM openjdk:17-jdk-alpine

ENV MUSL_LOCALE_DEPS cmake make musl-dev gcc gettext-dev libintl
ENV MUSL_LOCPATH /usr/share/i18n/locales/musl
ENV TERM=xterm TZ=America/Sao_Paulo

RUN apk update \
    apk upgrade \
    apk add ca-certificates && update-ca-certificates \
    apk add --update tzdata

RUN apk add --no-cache \
    $MUSL_LOCALE_DEPS \
    && wget https://gitlab.com/rilian-la-te/musl-locales/-/archive/master/musl-locales-master.zip \
    && unzip musl-locales-master.zip \
      && cd musl-locales-master \
      && cmake -DLOCALE_PROFILE=OFF -D CMAKE_INSTALL_PREFIX:PATH=/usr . && make && make install \
      && cd .. && rm -r musl-locales-master

ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR:br
ENV LC_ALL pt_BR.UTF-8


ARG JAR_FILE=target/eventswebsocket-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eventswebsocket.jar
ENTRYPOINT ["java","-jar","/eventswebsocket.jar"]