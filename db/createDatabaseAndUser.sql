delimiter ;

drop database if exists ${task.appDatabaseName};

create database ${task.appDatabaseName};

create user '${task.appDatabaseUser}'@'%'  identified with mysql_native_password;
create user '${task.appDatabaseUser}'@'localhost'  identified with mysql_native_password;

set password for '${task.appDatabaseUser}'@'%' = PASSWORD('${task.appDatabasePassword}');
set password for '${task.appDatabaseUser}'@'localhost' = PASSWORD('${task.appDatabasePassword}');

grant ALL on ${task.appDatabaseName}.* to '${task.appDatabaseUser}'@'%';
grant ALL on ${task.appDatabaseName}.* to '${task.appDatabaseUser}'@'localhost';

flush privileges;
