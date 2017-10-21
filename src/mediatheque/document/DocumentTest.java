package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
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
	}
	
	@Test
	public void testMetEmpruntable2() throws OperationImpossible, InvariantBroken {
		aa.metEmpruntable();
	}

	@Test
	public void testMetConsultable() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmprunter() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestituer() {
		fail("Not yet implemented");
	}

	@Test
	public void testInvariant() {
		fail("Not yet implemented");
	}

}
