package edu.sjsu.android.temperatureconverterbyagrikagupta;

public class ConverterUtil {
    // converts to celsius
    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }
    // converts to fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

    // Fahrenheit to Kelvin
    public static float convertFahrenheitToKelvin(float fahrenheit) {
        return (fahrenheit - 32) * 5 / 9 + 273.15f;
    }

    // Kelvin to Fahrenheit
    public static float convertKelvinToFahrenheit(float kelvin) {
        return (kelvin - 273.15f) * 9 / 5 + 32;
    }

    // Celsius to Kelvin
    public static float convertCelsiusToKelvin(float celsius) {
        return celsius + 273.15f;
    }

    // Kelvin to Celsius
    public static float convertKelvinToCelsius(float kelvin) {
        return kelvin - 273.15f;
    }
}
