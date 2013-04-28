package net.miklosovic.junit.example.test;

import net.miklosovic.junit.example.Adder;

import static org.hamcrest.CoreMatchers.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleTestCase {

    Adder adder;

    @Rule
    public Timeout globalTimeout = new Timeout(1000);
    
    @Before
    public void beforeTest() {
        adder = new Adder();
    }
    
    @AfterClass
    public static void afterClass() { }
    
    @Test
    public void addTwoAndTwo() {
        assertNotNull("adder is null!", adder);
        assertThat(adder.addPositiveNumbers(2, 2), is(4));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void notPositive() {
        adder.addPositiveNumbers(-1, -2);
        fail();
    }
    
    @Ignore @Test public void notPreparedYet() {}
}
