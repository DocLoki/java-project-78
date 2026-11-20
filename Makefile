.PHONY: build test clean

build:
	cd app && ./gradlew build

test:
	cd app && ./gradlew test

clean:
	cd app && ./gradlew clean
