# base
FROM 839324330571.dkr.ecr.eu-west-1.amazonaws.com/me.gking2224/ms-base:v3

# details
MAINTAINER Graham King <gking2224@gmail.com>

# labels

# build args
#ARG version
	
# environment variables
ENV SERVICE       refdatams
ENV DEBUG_OPTS    -Xdebug \
                  -Xrunjdwp:server=y,transport=dt_socket,suspend=y,address=8081
ENV JAVA_OPTS     ${DEBUG:+${DEBUG_OPTS}}
ENV JAVA_PROPS    -Dlogging.config=${LOGBACK_XML}
ENV JAR           $WORK_DIR/service.jar

# mount points

# create directories

# run as user
USER $user

# fetch code
#COPY build/libs/${SERVICE}-${version}-boot.jar $WORK_DIR/service.jar
#COPY logback.xml $WORK_DIR/logback.xml

RUN aws s3 sync s3://gk-microservices/env-properties $PROPS_DIR

# executable
WORKDIR ${WORK_DIR}
CMD PROPS_DIR=${PROPS_DIR} \
    java $JAVA_OPTS $JVM_ARGS $JAVA_PROPS \
         -jar $JAR \
         -a $APP \
         -e $ENV \
         $JAVA_ARGS



