all: run

clean:
	rm -f out/TaskRunner.jar out/PalindromeCounter.jar

out/TaskRunner.jar: out/parcs.jar src/TaskRunner.java src/Words.java
	@javac -cp out/parcs.jar src/TaskRunner.java src/Words.java
	@jar cf out/TaskRunner.jar -C src TaskRunner.class -C src Words.class
	@rm -f src/TaskRunner.class src/Words.class

out/PalindromeCounter.jar: out/parcs.jar src/PalindromeCounter.java src/Words.java
	@javac -cp out/parcs.jar src/PalindromeCounter.java src/Words.java
	@jar cf out/PalindromeCounter.jar -C src PalindromeCounter.class -C src Words.class
	@rm -f src/PalindromeCounter.class src/Words.class

build: out/TaskRunner.jar out/PalindromeCounter.jar

run: out/TaskRunner.jar out/PalindromeCounter.jar
	@cd out && java -cp 'parcs.jar:TaskRunner.jar' TaskRunner