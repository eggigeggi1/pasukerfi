package vinnsla;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PasukerfiVinnsla {
    public int starfsmennAGolfi;
    public Map<String, LocalDate> starfsmennID;
    public String next;

    public PasukerfiVinnsla() {
        starfsmennID = new HashMap<>();
        starfsmennID.put("AE", LocalDate.of(2005, 10, 31));
        starfsmennID.put("AF", LocalDate.of(1999, 5, 20));
        starfsmennID.put("AL", LocalDate.of(1999, 2, 13));
        starfsmennID.put("ARB", LocalDate.of(1983, 4, 12));
        starfsmennID.put("AT", LocalDate.of(1983, 7, 3));
        starfsmennID.put("BA", LocalDate.of(1991, 5, 26));
        starfsmennID.put("DA", LocalDate.of(1996, 2, 20));
        starfsmennID.put("DE", LocalDate.of(1993, 9, 25));
        starfsmennID.put("EB", LocalDate.of(1972, 5, 30));
        starfsmennID.put("EO", LocalDate.of(1991, 12, 6));
        starfsmennID.put("ES", LocalDate.of(1982, 11, 13));
        starfsmennID.put("ET", LocalDate.of(1991, 5, 6));
        starfsmennID.put("FO", LocalDate.of(1991, 5, 17));
        starfsmennID.put("GV", LocalDate.of(1983, 1, 5));
        starfsmennID.put("HA", LocalDate.of(1977, 7, 17));
        starfsmennID.put("HH", LocalDate.of(1990, 10, 22));
        starfsmennID.put("HN", LocalDate.of(1988, 1, 15));
        starfsmennID.put("HO", LocalDate.of(1979, 8, 19));
        starfsmennID.put("HP", LocalDate.of(2001, 10, 24));
        starfsmennID.put("JB", LocalDate.of(1981, 3, 26));
        starfsmennID.put("JG", LocalDate.of(1990, 2, 26));
        starfsmennID.put("JH", LocalDate.of(1964, 4, 30));
        starfsmennID.put("JN", LocalDate.of(1989, 7, 28));
        starfsmennID.put("JO", LocalDate.of(1990, 12, 7));
        starfsmennID.put("JSJ", LocalDate.of(1980, 8, 26));
        starfsmennID.put("KA", LocalDate.of(1995, 1, 12));
        starfsmennID.put("KJ", LocalDate.of(1984, 3, 3));
        starfsmennID.put("KK", LocalDate.of(1987, 12, 17));
        starfsmennID.put("LGM", LocalDate.of(1987, 9, 28));
        starfsmennID.put("LH", LocalDate.of(1988, 4, 5));
        starfsmennID.put("LS", LocalDate.of(1991, 4, 28));
        starfsmennID.put("MA", LocalDate.of(1982, 5, 2));
        starfsmennID.put("PI", LocalDate.of(1998, 9, 24));
        starfsmennID.put("SC", LocalDate.of(1993, 3, 9));
        starfsmennID.put("ST", LocalDate.of(1994, 5, 8));
        starfsmennID.put("TE", LocalDate.of(1978, 10, 13));
        starfsmennID.put("TIO", LocalDate.of(1980, 5, 28));
        starfsmennID.put("TO", LocalDate.of(2001, 10, 14));
        starfsmennID.put("TR", LocalDate.of(1993, 4, 19));
        starfsmennID.put("TT", LocalDate.of(1992, 6, 11));

        starfsmennAGolfi = 0;
        next = "";
    }
}
