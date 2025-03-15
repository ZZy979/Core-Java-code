package collecting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @version 1.01 2021-09-06
 * @author Cay Horstmann
 */
public class DownstreamCollectors {
    public record City(String name, String state, int population) {}
    public record Pair<S, T>(S first, T second) {}

    public static Stream<City> readCities(String filename) throws IOException {
        return Files.lines(Path.of(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException {
        // Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Locale.setDefault(Locale.US);
        Stream<Locale> locales = Stream.of(Util.getAvailableLocales());
        Map<String, Set<Locale>> countryToLocaleSet = locales.collect(
                groupingBy(Locale::getCountry, toSet()));
        System.out.println("countryToLocaleSet: " + countryToLocaleSet);

        locales = Stream.of(Util.getAvailableLocales());
        Map<String, Long> countryToLocaleCounts = locales.collect(
                groupingBy(Locale::getCountry, counting()));
        System.out.println("\ncountryToLocaleCounts: " + countryToLocaleCounts);

        String citiesFilename = "../../v2ch01/cities.txt";
        Stream<City> cities = readCities(citiesFilename);
        Map<String, Integer> stateToCityPopulation = cities.collect(
                groupingBy(City::state, summingInt(City::population)));
        System.out.println("\nstateToCityPopulation: " + stateToCityPopulation);

        cities = readCities(citiesFilename);
        Map<String, Optional<City>> stateToLargestCity = cities.collect(
                groupingBy(City::state, maxBy(Comparator.comparing(City::population))));
        System.out.println("\nstateToLargestCity: " + stateToLargestCity);

        cities = readCities(citiesFilename);
        Map<String, Optional<String>> stateToLongestCityName = cities.collect(
                groupingBy(City::state, mapping(City::name, maxBy(Comparator.comparing(String::length)))));
        System.out.println("\nstateToLongestCityName: " + stateToLongestCityName);

        locales = Stream.of(Util.getAvailableLocales());
        Map<String, Set<String>> countryToLanguages = locales.collect(
                groupingBy(Locale::getDisplayCountry, mapping(Locale::getDisplayLanguage, toSet())));
        System.out.println("\ncountryToLanguages: " + countryToLanguages);

        cities = readCities(citiesFilename);
        Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities.collect(
                groupingBy(City::state, summarizingInt(City::population)));
        System.out.println("\n" + stateToCityPopulationSummary.get("NY"));

        cities = readCities(citiesFilename);
        Map<String, String> stateToCityNames = cities.collect(groupingBy(
                City::state,
                reducing("", City::name, (s, t) -> s.isEmpty() ? t : s + ", " + t)));
        System.out.println("\nstateToCityNames: " + stateToCityNames);

        cities = readCities(citiesFilename);
        stateToCityNames = cities.collect(
                groupingBy(City::state, mapping(City::name, joining(", "))));
        System.out.println("\nstateToCityNames: " + stateToCityNames);

        cities = readCities(citiesFilename);
        Pair<List<String>, Double> result = cities.filter(c -> c.state().equals("NV"))
                .collect(teeing(
                        mapping(City::name, toList()),
                        averagingDouble(City::population),
                        Pair::new));
        System.out.println("\nresult: " + result);
    }
}
