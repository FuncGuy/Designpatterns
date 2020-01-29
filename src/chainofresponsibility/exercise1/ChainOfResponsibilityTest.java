/*
 * This class forms part of the Design Patterns Course by
 * Dr Heinz Kabutz from JavaSpecialists.eu and may not be
 * distributed without written consent.
 *
 * Copyright 2001-2018, Heinz Kabutz, All rights reserved.
 */
package chainofresponsibility.exercise1;

import org.junit.*;

import java.lang.reflect.*;

import static org.junit.Assert.*;

//DON'T CHANGE
public class ChainOfResponsibilityTest {
    @Test
    public void testChainOfResponsibilityMethods() throws Exception {
        test(makeChain(DoubleTrimmerConverter.class, StringUpperCaseConverter.class, StringTrimmerConverter.class));
        test(makeChain(DoubleTrimmerConverter.class, StringTrimmerConverter.class, StringUpperCaseConverter.class));
        test(makeChain(StringUpperCaseConverter.class, DoubleTrimmerConverter.class, StringTrimmerConverter.class));
        test(makeChain(StringUpperCaseConverter.class, StringTrimmerConverter.class, DoubleTrimmerConverter.class));
        test(makeChain(StringTrimmerConverter.class, StringUpperCaseConverter.class, DoubleTrimmerConverter.class));
        test(makeChain(StringTrimmerConverter.class, DoubleTrimmerConverter.class, StringUpperCaseConverter.class));
    }

    private void test(Converter chain) {
        assertEquals("HEINZ", chain.handle("    heinz   "));
        assertEquals("INTERESTING", chain.handle("interesting"));
        assertEquals("S\u00dcSS", chain.handle("s\u00fc\u00df"));
        assertEquals(5.0, (Double) chain.handle(4.5d), 0.000000000001);
        assertEquals(4.0, (Double) chain.handle(4.49999999999d), 0.000000000001);
    }

    private Converter makeChain(Class<?>... converterClasses) throws Exception {
        Converter converter = null;
        for (int i = converterClasses.length - 1; i >= 0; i--) {
            converter = make(converterClasses[i], converter);
        }
        return converter;
    }

    private Converter make(Class<?> converterClass, Converter next) throws Exception {
        try {
            return Converter.class.cast(converterClass.getDeclaredConstructor(Converter.class).newInstance(next));
        } catch (InvocationTargetException e) {
            throw e;
        } catch (NoSuchMethodException e) {
            fail(converterClass + " needs a constructor taking the next Converter as a parameter");
            return null;
        }
    }
}
