# Weather Forecast Application

A Spring Boot web application that provides weather forecasts based on user-entered locations using the OpenWeatherMap API.

## Features

- **Location-based Weather**: Enter any city name to get current weather information
- **Temperature Display**: Shows current, minimum, and maximum temperatures in Celsius
- **Error Handling**: Displays user-friendly error messages for invalid locations
- **Responsive UI**: Clean, modern web interface
- **Synchronous Processing**: Real-time weather data retrieval and display

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or higher
- **Gradle 8.5** or higher (or use the included Gradle wrapper)
- **Internet connection** (for API calls)

## Setup Instructions

### 1. Clone the Repository

```bash
git clone <repository-url>
cd weather-app
```

### 2. Get OpenWeatherMap API Key

1. Visit [OpenWeatherMap](https://openweathermap.org/api)
2. Sign up for a free account
3. Generate an API key from your account dashboard
4. Copy the API key for the next step

### 3. Configure API Key

Open `src/main/resources/application.properties` and replace `your_openweathermap_api_key_here` with your actual API key:

```properties
weather.api.key=your_actual_api_key_here
```

**Important**: Never commit your actual API key to version control. For production deployments, use environment variables or external configuration.

### 4. Build the Application

Using Gradle wrapper (recommended):
```bash
./gradlew build
```

Or using system Gradle:
```bash
gradle build
```

### 5. Run the Application

Using Gradle wrapper:
```bash
./gradlew bootRun
```

Or using system Gradle:
```bash
gradle bootRun
```

Alternatively, you can run the JAR file directly:
```bash
java -jar build/libs/weather-app-0.0.1-SNAPSHOT.jar
```

### 6. Access the Application

Open your web browser and navigate to:
```
http://localhost:8080
```

## Usage

1. **Enter Location**: Type a city name (e.g., "London", "New York", "Tokyo") in the text input field
2. **Submit**: Click the "Get Weather Forecast" button
3. **View Results**: The weather information will be displayed on the same page, including:
   - Current temperature
   - Minimum temperature
   - Maximum temperature
   - A read-only text area with detailed temperature information

## Error Handling

The application handles various error scenarios:

- **Invalid Location**: If the entered location is not found, an error message will be displayed
- **Empty Input**: If no location is entered, a validation error will be shown
- **API Issues**: If the weather service is unavailable, a user-friendly error message will be displayed
- **Network Problems**: Connection issues are handled gracefully with appropriate error messages

## Project Structure

```
weather-app/
├── src/
│   ├── main/
│   │   ├── java/com/weather/app/
│   │   │   ├── WeatherApplication.java          # Main Spring Boot application
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java               # Application configuration
│   │   │   ├── controller/
│   │   │   │   └── WeatherController.java       # Web controller
│   │   │   ├── model/
│   │   │   │   ├── WeatherData.java             # Weather data model
│   │   │   │   └── WeatherResponse.java         # API response model
│   │   │   └── service/
│   │   │       └── WeatherService.java          # Weather API service
│   │   └── resources/
│   │       ├── application.properties           # Application configuration
│   │       └── templates/
│   │           └── index.html                   # Main web page template
│   └── test/
│       └── java/com/weather/app/
│           ├── WeatherApplicationTests.java     # Application tests
│           └── controller/
│               └── WeatherControllerTest.java   # Controller tests
├── build.gradle                                 # Gradle build configuration
├── settings.gradle                              # Gradle settings
├── gradlew                                      # Gradle wrapper (Unix)
├── gradlew.bat                                  # Gradle wrapper (Windows)
└── README.md                                    # This file
```

## API Configuration

The application uses the OpenWeatherMap API with the following default configuration:

- **Base URL**: `https://api.openweathermap.org/data/2.5/weather`
- **Units**: Metric (Celsius)
- **Response Format**: JSON

You can modify these settings in `application.properties` if needed.

## Testing

Run the test suite using:

```bash
./gradlew test
```

The application includes:
- Unit tests for the web controller
- Integration tests for the Spring Boot application context

## Troubleshooting

### Common Issues

1. **"API key is invalid"**
   - Verify your OpenWeatherMap API key is correct
   - Ensure the API key is active (new keys may take a few minutes to activate)

2. **"Location not found"**
   - Check the spelling of the location
   - Try using different variations (e.g., "NYC" vs "New York")

3. **Application won't start**
   - Verify Java 17+ is installed: `java -version`
   - Check if port 8080 is available
   - Review the console logs for specific error messages

4. **Build failures**
   - Ensure you have internet connectivity for dependency downloads
   - Try cleaning the build: `./gradlew clean build`

### Logs

The application logs are configured to show DEBUG level information for the weather package. Check the console output for detailed information about API calls and responses.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [OpenWeatherMap](https://openweathermap.org/) for providing the weather API
- [Spring Boot](https://spring.io/projects/spring-boot) for the application framework
- [Thymeleaf](https://www.thymeleaf.org/) for the templating engine
