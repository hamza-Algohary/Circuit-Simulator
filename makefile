build:
	-mkdir bin
	javac -cp ".:./lib/ejml.jar" src/*.java -d bin/
run:
	java -cp ".:./lib/ejml.jar" bin/Main
