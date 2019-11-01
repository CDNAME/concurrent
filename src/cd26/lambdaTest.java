package cd26;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class LambdaTest {

    @Test
    public void find() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.stream().forEach(a -> System.out.println(a));
        features.stream().forEach(System.out::println);
    }

    @Test
    public void find1() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        filter(languages, (str)->String.valueOf(str).length() > 4);
    }

    @Test
    public void find2() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;

        languages.stream().filter(startWithJ.and(fourLetterLong)).forEach(System.out::println);
    }

    @Test
    public void find3() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map(n -> n + .12*n).forEach(System.out::println);
    }

    @Test
    public void find4() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Double aDouble = costBeforeTax.stream().map(n -> n + .12 * n).reduce((sum, n) -> sum + n).get();
        System.out.println(aDouble);
    }

    @Test
    public void find5() {
        List<String> str = Arrays.asList("abc", "bcd", "defg", "jk");
        str.stream().filter(s -> String.valueOf(s).length() > 2).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void find6() {
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String collect = G7.stream().map(s -> s.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(collect);
    }

    @Test
    public void find7() {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        numbers.stream().map(n -> n*n).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void find8() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getAverage());
    }

    public static void filter(List names, Predicate condition) {
        names.forEach((name) -> {
                if(condition.test(name)) {
                    System.out.println(name + " ");
                }
        });
    }
}
