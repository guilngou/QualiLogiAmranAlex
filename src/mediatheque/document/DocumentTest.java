package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import mediatheque.document.Document;
import util.InvariantBroken;

public class DocumentTest {
	
	Audio aa;
	
	@Before
	//public void setUp() throws OperationImpossible, InvariantBroken {
	public void setUp() throws Exception {
        Localisation ll = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
        aa = new Audio("007", ll, "titre1", "auteur1", "annee1", gg, "classification1" );
	}
	
	@After
	public void tearDown() throws Exception {
		aa = null;
	}

	@Test
	public void testConstructeurDocument() {
		assertNotNull(aa);
		assertTrue(aa.getCode().equals("007"));
		assertEquals(new Localisation("salle1", "rayon1"), aa.getLocalisation());
		assertTrue(aa.getTitre().equals("titre1"));
		assertTrue(aa.getAuteur().equals("auteur1"));
		assertTrue(aa.getAnnee().equals("annee1"));
		assertEquals(new Genre("genre1"), aa.getGenre());
		assertTrue(aa.getClassification().equals("classification1")); 
		assertEquals(false, aa.estEmpruntable());
		assertEquals(false, aa.estEmprunte());
		assertEquals(0, aa.getNbEmprunts());
	}

	@Test
	public void testMetEmpruntable() throws OperationImpossible, InvariantBroken {
		aa.metEmpruntable();
		assertEquals(true, aa.estEmpruntable());
	}
	
	@Test (expected = OperationImpossible.class)
	public void testMetEmpruntableException1() throws OperationImpossible, InvariantBroken {
		aa.metEmpruntable();
		aa.metEmpruntable();
	}
	
	@Test (expected = InvariantBroken.class)
	public void testMetEmpruntableException2() throws OperationImpossible, InvariantBroken {
		//TODO : test
		//aa.emprunter();
		//aa.metEmpruntable();
		/**
         * Safety property - emprunte => empruntable
         * 
         * @return if the document is in a safe state, i.e respects the invariant
         */
        /*public boolean invariant() {
                return !(emprunte && !empruntable);
        }*/
	}

	@Test
	public void testMetConsultable() throws OperationImpossible, InvariantBroken {
		aa.metEmpruntable();
		assertEquals(true, aa.estEmpruntable());
		aa.metConsultable();
		assertEquals(false, aa.estEmpruntable());
	}
	
	@Test (expected = OperationImpossible.class)
	public void testMetConsultableException1() throws OperationImpossible, InvariantBroken {
		aa.metConsultable();
	}
	
	@Test (expected = OperationImpossible.class)
	public void testMetConsultableException2() throws OperationImpossible, InvariantBroken {
		aa.metEmpruntable();
		aa.emprunter();
		aa.metConsultable();
	}
	

	@Test
	public void testEmprunter() throws InvariantBroken, OperationImpossible {
		aa.metEmpruntable();
		//System.out.println("nbEmprunts = " + aa.getNbEmprunts());
		int nbEmpruntsInitial = aa.getNbEmprunts();
		aa.emprunter();
		//System.out.println("nbEmprunts = " + aa.getNbEmprunts());
		assertEquals(aa.getNbEmprunts(), nbEmpruntsInitial + 1);
		assertEquals(true, aa.estEmpruntable());
		assertEquals(true, aa.estEmprunte());
	}

	@Test
	public void testRestituer() throws InvariantBroken, OperationImpossible {
		aa.metEmpruntable();
		aa.emprunter();
		aa.restituer();
		assertEquals(false, aa.estEmprunte());
	}
	
	@Test
	public void testRestituerException() throws InvariantBroken, OperationImpossible {
		try{
			aa.metEmpruntable();
			aa.restituer();
	    } catch(OperationImpossible e) {
	    	System.out.println("OperationImpossible : " + e);
	    	assertTrue(e.toString().equals("mediatheque.OperationImpossible: Impossible de restituer un document non emprunte"));
	    } 
	}
	
	@Test
	public void testRestituerException2() throws InvariantBroken, OperationImpossible {
		try{
			aa.restituer();
	    } catch(OperationImpossible e) {
	    	System.out.println("OperationImpossible : " + e);
	    	assertTrue(e.toString().equals("mediatheque.OperationImpossible: Impossible de restituer un document non empruntable"));
	    } 
	}

	@Test
	public void testInvariant() throws InvariantBroken, OperationImpossible {
		assertTrue(aa.invariant());
	}

}
