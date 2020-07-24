# Test Base

## Getting started

This is the test base to be used in projects as a starting point for writing tests. This test base utilises the Espresso and the robot pattern to make writing tests quicker and simpler.

There's some setup required when bringing this test base into the project.

- In the app build.gradle file there's a list of required dependencies for the test classes provided.
- An extended version of the Application case in the main project, set as an open class with a method to get the Base url for the API, this normally comes from the BuildConfig. When settings your base url, use this method to do so in retrofit. Make sure to set this in your manifest.
- In your app build.gradle file, under android { defaultConfig { .... } }, set the testInstrumentationRunner to the MockTestRunner in the Android Test folder. E.G  testInstrumentationRunner "com.app.testbaseutils.testutils.MockTestRunner". By setting this, it allows the tests to retrieve the TestApplication class which overrides the getBaseUrl() method to return the URL of the MockDispatcher.
- The MockWebDispatcher requires <uses-permission android:name="android.permission.INTERNET" /> (You'll probably already have this)

## Testing

There are two types of tests this projects supports, fragment and integration tests. They each do similar things;
A fragment test is designed to test a fragment in a more detailed way, down to text being displayed on the view, button states.
A integration test is there to test how the application flows, more of a high level test where the test runs through a flow the user might go through. For example a test could test; the user tries to login and it gives them an error, and then it returns fine, they then proceed through the home screen to order an item.
