package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class LivreTest {
	
	Livre liv;
	
	@Before
	public void setUp() throws Exception {
        Localisation loc = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
        liv = new Livre("007", loc, "titre1", "auteur1", "annee1", gg, 100);
	}
	
	@After
	public void tearDown() throws Exception {
		liv = null;
	}
	
	@Test
	public void testConstructeurLivre() {
		assertNotNull(liv);
		assertTrue(liv.getCode().equals("007"));
		assertEquals(new Localisation("salle1", "rayon1"), liv.getLocalisation());
		assertTrue(liv.getTitre().equals("titre1"));
		assertTrue(liv.getAuteur().equals("auteur1"));
		assertTrue(liv.getAnnee().equals("annee1"));
		assertEquals(new Genre("genre1"), liv.getGenre());
		// Manque getter nombrePages
		assertEquals(false, liv.estEmpruntable());
		assertEquals(false, liv.estEmprunte());
		assertEquals(0, liv.getNbEmprunts());
		assertEquals(0, liv.getStat());
	}
	
	@Test (expected = OperationImpossible.class)
	public void testConstructeurLivreException() throws InvariantBroken, OperationImpossible{
		liv = new Livre("007", new Localisation("salle1", "rayon1"), "titre1", "auteur1", "annee1", new Genre("genre1"), -1);
	}

	@Test
	public void testInvariantLivre() throws InvariantBroken, OperationImpossible {
		assertTrue(liv.invariant());
	}
	
	@Test
	public void testEmprunter() throws InvariantBroken, OperationImpossible {
		liv.metEmpruntable();
		System.out.println("nbEmpruntsTotal = " + liv.getStat());
		int nbEmpruntsTotalInitial = liv.getStat();
		liv.emprunter();
		System.out.println("nbEmpruntsTotal = " + liv.getStat());
		assertEquals(liv.getStat(), nbEmpruntsTotalInitial + 1);
		assertEquals(true, liv.estEmpruntable());
		assertEquals(true, liv.estEmprunte());
	}

}
