Welcome to the drawing app!

The implemented functionality is documented through runnable test scenarios
in src/test/resources/scenarios and EndToEndTest class

This project is compatible with sbt 0.12.0, which is also bundled with it.

To build a runnable jar, run in the main directory:
./sbt assembly

Then to run:
cd target/scala-2.10
java -jar Drawing.jar

or run directly from sbt:
./sbt run

To compile and run the tests:
./sbt test