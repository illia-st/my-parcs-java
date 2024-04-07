all: run

clean:
	rm -f out/TaskRunner.jar out/WordsCounter.jar

out/TaskRunner.jar: out/parcs.jar src/TaskRunner.java src/Words.java src/UniqueWords.java
	@javac -cp out/parcs.jar src/TaskRunner.java src/Words.java src/UniqueWords.java
	@jar cf out/TaskRunner.jar -C src TaskRunner.class -C src Words.class -C src UniqueWords.class
	@rm -f src/TaskRunner.class src/Words.class src/UniqueWords.class

out/WordsCounter.jar: out/parcs.jar src/WordsCounter.java src/Words.java src/UniqueWords.java
	@javac -cp out/parcs.jar src/WordsCounter.java src/Words.java src/UniqueWords.java
	@jar cf out/WordsCounter.jar -C src WordsCounter.class -C src Words.class -C src UniqueWords.class
	@rm -f src/WordsCounter.class src/Words.class src/UniqueWords.class

build: out/TaskRunner.jar out/WordsCounter.jar

run: out/TaskRunner.jar out/WordsCounter.jar
	@cd out && java -cp 'parcs.jar:TaskRunner.jar' TaskRunner