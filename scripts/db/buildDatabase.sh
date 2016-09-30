#!/bin/bash

mysql ${task.databaseName} < ${task.remoteDir}/model.sql > ${task.remoteDir}/model.sql.out

mysql ${task.databaseName} < ${task.remoteDir}/storedProcedures.sql > ${task.remoteDir}/storedProcedures.sql.out

mysql ${task.databaseName} < ${task.remoteDir}/grants.sql > ${task.remoteDir}/grants.sql.out
