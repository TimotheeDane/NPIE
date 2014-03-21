/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.at;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author E110062H
 */
public class ConversionTest {
    
    /**
     * Test of getValeur method, of class Conversion.
     */
    @Test
    public void testGetValeur() {
        System.out.println("getValeur");
        Conversion instance = null;
        double expResult = 0.0;
        double result = instance.getValeur();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValeur method, of class Conversion.
     */
    @org.junit.Test
    public void testSetValeur() {
        System.out.println("setValeur");
        double valeur = 0.0;
        Conversion instance = null;
        instance.setValeur(valeur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Conversion.
     */
    @org.junit.Test
    public void testGetType() {
        System.out.println("getType");
        Conversion instance = null;
        TypeConversion expResult = null;
        TypeConversion result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Conversion.
     */
    @org.junit.Test
    public void testSetType() {
        System.out.println("setType");
        TypeConversion type = null;
        Conversion instance = null;
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
