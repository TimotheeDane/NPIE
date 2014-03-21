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
public class MontantTest {
    
    public MontantTest() {
    }

    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of convert method, of class Montant.
     */
    @Test
    public void testConvert() {
        System.out.println("convert");
        Montant valDep = null;
        Montant instance = new Montant();
        instance.convert(valDep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValeur method, of class Montant.
     */
    @Test
    public void testGetValeur() {
        System.out.println("getValeur");
        Montant instance = new Montant();
        double expResult = 0.0;
        double result = instance.getValeur();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValeur method, of class Montant.
     */
    @Test
    public void testSetValeur() {
        System.out.println("setValeur");
        double valeur = 0.0;
        Montant instance = new Montant();
        instance.setValeur(valeur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnite method, of class Montant.
     */
    @Test
    public void testGetUnite() {
        System.out.println("getUnite");
        Montant instance = new Montant();
        Unite expResult = null;
        Unite result = instance.getUnite();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnite method, of class Montant.
     */
    @Test
    public void testSetUnite() {
        System.out.println("setUnite");
        Unite unite = null;
        Montant instance = new Montant();
        instance.setUnite(unite);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
