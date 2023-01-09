build:
	-mkdir bin
	javac -cp ".:./lib/ejml.jar:./lib/jchart2d.jar" src/*.java -d bin/