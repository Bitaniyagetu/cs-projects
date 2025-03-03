// This program calculates the heat index based on temperature and humidity input by the user. 
// It also provides a health risk message depending on the calculated heat index.

import java.util.Scanner;

public class HeatIndex {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user to input temperature in Fahrenheit
        System.out.print("Enter the air temperature in Fahrenheit (>=80°F): ");
        double temperature = input.nextDouble();

        // Validate temperature
        if (temperature < 80) {
            System.out.println("Heat index calculation is only valid for temperatures 80°F or higher.");
            input.close();
            return;
        }

        // Prompt user to input the relative humidity
        System.out.print("Enter the relative humidity (0-100%): ");
        double relativeHumidity = input.nextDouble();

        // Validate humidity
        if (relativeHumidity < 0 || relativeHumidity > 100) {
            System.out.println("Invalid humidity. Please enter a value between 0 and 100.");
            input.close();
            return;
        }

        double heatIndex = calculateHeatIndex(temperature, relativeHumidity);
        System.out.printf("\nThe calculated Heat Index is: %.2f°F\n", heatIndex);

        // Output health risk messages
        System.out.println(getHeatRiskMessage(heatIndex));
        
        input.close();
    }

    // Method to calculate the heat index
    public static double calculateHeatIndex(double temperature, double relativeHumidity) {
        return -42.379 + (2.04901523 * temperature) + (10.14333127 * relativeHumidity)
                - (0.22475541 * temperature * relativeHumidity) 
                - (6.83783 * Math.pow(10, -3) * Math.pow(temperature, 2))
                - (5.481717 * Math.pow(10, -2) * Math.pow(relativeHumidity, 2))
                + (1.22874 * Math.pow(10, -3) * Math.pow(temperature, 2) * relativeHumidity)
                + (8.5282 * Math.pow(10, -4) * temperature * Math.pow(relativeHumidity, 2))
                - (1.99 * Math.pow(10, -6) * Math.pow(temperature, 2) * Math.pow(relativeHumidity, 2));
    }

    // Method to return the heat risk message based on the heat index value
    public static String getHeatRiskMessage(double heatIndex) {
        if (heatIndex < 80) {
            return "No significant heat-related risk.";
        } else if (heatIndex <= 90) {
            return "Fatigue possible with prolonged exposure and/or physical activity.";
        } else if (heatIndex <= 105) {
            return "Sunstroke, heat cramps, and heat exhaustion possible with prolonged exposure and/or physical activity.";
        } else if (heatIndex <= 130) {
            return "Sunstroke, heat cramps, or heat exhaustion likely, and heat stroke possible with prolonged exposure and/or physical activity.";
        } else {
            return "Heatstroke/sunstroke highly likely with continued exposure.";
        }
    }
}
