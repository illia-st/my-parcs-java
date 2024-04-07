all: run

clean:
	rm -f out/TaskRunner.jar out/PrimeSumCounter.jar

out/TaskRunner.jar: out/parcs.jar src/TaskRunner.java src/Interval.java
	@javac -cp out/parcs.jar src/TaskRunner.java src/Interval.java
	@jar cf out/TaskRunner.jar -C src TaskRunner.class -C src Interval.class
	@rm -f src/TaskRunner.class src/Words.class

out/PrimeSumCounter.jar: out/parcs.jar src/PrimeSumCounter.java src/Interval.java
	@javac -cp out/parcs.jar src/PrimeSumCounter.java src/Interval.java
	@jar cf out/PrimeSumCounter.jar -C src PrimeSumCounter.class -C src Interval.class
	@rm -f src/PrimeSumCounter.class src/Interval.class

build: out/TaskRunner.jar out/PrimeSumCounter.jar

run: out/TaskRunner.jar out/PrimeSumCounter.jar
	@cd out && java -cp 'parcs.jar:TaskRunner.jar' TaskRunner