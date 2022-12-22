New-Item -Path . -Name "bin" -ItemType "directory"
javac -cp ".:./lib/ejml.jar" src/*.java -d bin/
