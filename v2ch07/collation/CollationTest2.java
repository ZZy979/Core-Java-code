package collation;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import util.Choices;

/**
 * This program demonstrates collating strings under various locales.
 * @version 2.0 2021-09-23
 * @author Cay Horstmann
 */
public class CollationTest2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // var locales = (Locale[]) NumberFormat.getAvailableLocales().clone();
        var locales = new Locale[] {
                Locale.CANADA, Locale.CHINA, Locale.FRANCE, Locale.GERMANY, Locale.ITALY,
                Locale.JAPAN, Locale.KOREA, Locale.TAIWAN, Locale.UK, Locale.US, Locale.forLanguageTag("sv-SE")
        };
        Arrays.sort(locales, Comparator.comparing(Locale::getDisplayName));
        Locale loc = Choices.choose(in, locales, Locale::getDisplayName);

        Collator coll = Collator.getInstance(loc);
        int strength = Choices.choose(in, Collator.class, "Primary", "Secondary", "Tertiary", "Identical");
        int decomposition = Choices.choose(in, Collator.class, "Canonical Decomposition", "Full Decomposition", "No Decomposition");
        coll.setStrength(strength);
        coll.setDecomposition(decomposition);

        List<String> strings = new ArrayList<>();
        strings.add("America");
        strings.add("able");
        strings.add("Zulu");
        strings.add("zebra");
        strings.add("\u00C5ngstr\u00F6m");
        strings.add("A\u030angstro\u0308m");
        strings.add("Angstrom");
        strings.add("Able");
        strings.add("office");
        strings.add("o\uFB03ce");
        strings.add("Java\u2122");
        strings.add("JavaTM");

        strings.sort(coll);

        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (i > 0 && coll.compare(s, strings.get(i - 1)) == 0)
                System.out.print("= ");
            System.out.println(s);
        }
    }
}
