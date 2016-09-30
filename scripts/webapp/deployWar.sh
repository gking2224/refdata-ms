#!/bin/bash

service ${project.tomcatServiceName} stop

cp ${task.remoteDir}/${task.warFileName} ${project.remoteWebappsStagingDir}/${task.warFileName}

chown tomcat:tomcat ${project.remoteWebappsStagingDir}/${task.warFileName}

if [ -e ${project.remoteWebappsDir}/${project.name}.war ]
then
  rm ${project.remoteWebappsDir}/${project.name}.war
fi

if [ -d ${project.remoteWebappsDir}/${project.name} ]
then
  rm -rf ${project.remoteWebappsDir}/${project.name}
fi

ln -s ${project.remoteWebappsStagingDir}/${task.warFileName} ${project.remoteWebappsDir}/${project.name}.war

chown tomcat:tomcat ${project.remoteWebappsDir}/${project.name}.war

service ${project.tomcatServiceName} start
