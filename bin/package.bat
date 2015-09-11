@echo off



cd %~dp0


rem parent
cd ../parent
call mvn clean install -Dmaven.test.skip=true

rem commons
cd ../commons
call mvn clean install -Dmaven.test.skip=true

rem tools-memcached
cd ../tools-memcached
call mvn clean install -Dmaven.test.skip=true

rem core
cd ../core
call mvn clean install -Dmaven.test.skip=true

pause