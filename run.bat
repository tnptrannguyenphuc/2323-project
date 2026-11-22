@echo off
chcp 65001 >nul
echo Dang bien dich ma nguon Java...
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin src\interfaces\*.java src\model\*.java src\lists\*.java src\controllers\*.java src\*.java
if %errorlevel% neq 0 (
    echo Co loi khi bien dich!
    pause
    exit /b %errorlevel%
)
echo Bien dich thanh cong! Dang khoi chay chuong trinh...
echo.
java -cp bin Main
pause
