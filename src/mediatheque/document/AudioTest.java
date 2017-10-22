package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Localisation;
import mediatheque.Genre;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class AudioTest {
	
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

	@Test (expected = OperationImpossible.class)
	public void testEmprunterException() throws OperationImpossible, InvariantBroken {
		aa.emprunter();
	}

	@Test
	public void testConstructeurAudio() throws OperationImpossible, InvariantBroken {
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
	
	
	@Test (expected = OperationImpossible.class)
	public void testConstructeurAudioException() throws OperationImpossible, InvariantBroken {
		/* Test de : if (classification == null) {
        throw new OperationImpossible("Ctr Audio classification = "
                + classification);*/
		
		aa = new Audio("007",  new Localisation("salle1", "rayon1"), "titre1", "auteur1", "annee1", new Genre("genre1"), null );
		
	}

}
