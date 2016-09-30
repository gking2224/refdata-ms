#!/bin/bash

# This script run as root

# Run database script
mysql mysql < ${remoteDir}/createDemoDatabaseAndUser.sql
