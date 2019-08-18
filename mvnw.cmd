@echo off
for /f "tokens=1,2 delims=" %%a in ('jps') do (if "%%b"=="break-point_test.jar" (set pid=%%a))
if DEFINEND pid  taskkill /pid %pid% /f
@echo off
start javaw -jar .\target\break-point_test.jar
exit

