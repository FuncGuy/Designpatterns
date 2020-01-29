/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.solution1;

public class ConverterExample {
    public static void main(String... args) {
        // the following code should run and produce the output:
        // HEINZ
        // 5.0
        // 4.0
        // INTERESTING
        // S\u00dcSS
//        /*
        Converter chain = new DoubleTrimmerConverter(
            new StringUpperCaseConverter(
                new StringTrimmerConverter(null)
            )
        );
        System.out.println(chain.handle("    heinz   "));
        System.out.println(chain.handle(4.5d));
        System.out.println(chain.handle(4.49999999999d));
        System.out.println(chain.handle("interesting"));
        System.out.println(chain.handle("s\u00fc\u00df"));
//        */
    }
}
