# Number Speller

Simple library to spell a number in English. Negative numbers are spelled with "Negative" prefix.


For example given 123, returns One hundred and twenty three.

The library is thread safe, and carries no state.

##Building

To test the library

`$ mvn test`

To build, and install it to local repo

`$mvn install`

Remote installation is not configured yet.


## Repl
Library comes with a Repl tester. To ues it, build the library and run

`java -jar target/number-speller-${version}.jar`

Entering a number, it will print the english spelling of it in response.

Use CTRL+C to terminate the REPL.

Please note that the REPL is tested under mac bash, and zsh. Please let us know if it didn't work in your terminal

