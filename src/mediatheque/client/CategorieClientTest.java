package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.document.Audio;

public class CategorieClientTest {
	
	CategorieClient categorieclientCourt, categorieclientLong;
	
	@Before
	public void setUp() throws Exception {
        categorieclientCourt = new CategorieClient("categorieclientCourt");
        categorieclientLong = new CategorieClient("categorieclientLong", 10, 10, 10, 10, false);
	}
	
	@After
	public void tearDown() throws Exception {
		categorieclientCourt = null;
		categorieclientLong = null;
	}
	
	

	@Test
	public void testConstructeurCategorieClientString() {
		assertNotNull(categorieclientCourt);
		assertTrue(categorieclientCourt.getNom().equals("categorieclientCourt"));
		assertEquals(0, categorieclientCourt.getNbEmpruntMax());
		assertEquals(0, categorieclientCourt.getCotisation(), 0.001);
		assertEquals(0, categorieclientCourt.getCoefDuree(), 0.001);
		assertEquals(0, categorieclientCourt.getCoefTarif(), 0.001);
		assertEquals(false, categorieclientCourt.getCodeReducUtilise());
	}
	
	@Test
	public void testConstructeurCategorieClientStringIntDoubleDoubleDoubleBoolean() {
		assertNotNull(categorieclientLong);
		assertTrue(categorieclientLong.getNom().equals("categorieclientLong"));
		assertEquals(10, categorieclientLong.getNbEmpruntMax());
		assertEquals(10, categorieclientLong.getCotisation(), 0.001);
		assertEquals(10, categorieclientLong.getCoefDuree(), 0.001);
		assertEquals(10, categorieclientLong.getCoefTarif(), 0.001);
		assertEquals(false, categorieclientLong.getCodeReducUtilise());
	}

	@Test
	public void testModifierNom() {
		categorieclientCourt.modifierNom("nouveau");
		assertTrue(categorieclientCourt.getNom().equals("nouveau"));
	}

	@Test
	public void testModifierMax() {
		categorieclientCourt.modifierMax(20);
		assertTrue(categorieclientCourt.getNbEmpruntMax() == 20);
	}

	@Test
	public void testModifierCotisation() {
		categorieclientCourt.modifierCotisation(20);
		assertEquals(20, categorieclientCourt.getCotisation(), 0.001);
		// Erreur repérée, cotisation = 4; au lieu de cotisation = cot;
	}

	@Test
	public void testModifierCoefDuree() {
		categorieclientCourt.modifierCoefDuree(20);
		assertEquals(20, categorieclientCourt.getCoefDuree(), 0.001);
	}

	@Test
	public void testModifierCoefTarif() {
		categorieclientCourt.modifierCoefTarif(20);
		assertEquals(20, categorieclientCourt.getCoefTarif(),0.001);
	}

	@Test
	public void testModifierCodeReducActif() {
		categorieclientCourt.modifierCodeReducActif(true);
		assertEquals(true, categorieclientCourt.getCodeReducUtilise());
	}

}
