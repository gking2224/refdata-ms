#!/bin/bash

script=$1

mysql ${databaseName} < ${to}/$1
