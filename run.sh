#!/bin/zsh

cd "$(dirname "$0")"

echo "Dang bien dich ma nguon Java..."

if [ ! -d "bin" ]; then
    mkdir bin
fi

javac -encoding UTF-8 -d bin src/interfaces/*.java src/model/*.java src/lists/*.java src/controllers/*.java src/*.java

if [ $? -ne 0 ]; then
    echo "Co loi khi bien dich!"
    echo "Nhan phim bat ky de thoat..."
    read -k 1
    exit 1
fi

echo "Bien dich thanh cong! Dang khoi chay chuong trinh..."
echo ""

java -cp bin Main

echo ""
echo "Nhan phim bat ky de thoat..."
read -k 1