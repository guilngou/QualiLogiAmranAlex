package mediatheque.document;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mediatheque.Genre;
import mediatheque.Localisation;
import mediatheque.OperationImpossible;
import util.InvariantBroken;

public class VideoTest {
	
Video video;
	
	@Before
	public void setUp() throws Exception {
        Localisation loc = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
        video = new Video("007", loc, "titre1", "auteur1", "annee1", gg, 100, "mentionlegale1" );
	}
	
	@After
	public void tearDown() throws Exception {
		video = null;
	}
	
	@Test
	public void testConstructeurVideo() {
		assertNotNull(video);
		assertTrue(video.getCode().equals("007"));
		assertEquals(new Localisation("salle1", "rayon1"), video.getLocalisation());
		assertTrue(video.getTitre().equals("titre1"));
		assertTrue(video.getAuteur().equals("auteur1"));
		assertTrue(video.getAnnee().equals("annee1"));
		assertEquals(new Genre("genre1"), video.getGenre());
		assertTrue(video.getDureeFilm() == 100);
		assertEquals(false, video.estEmpruntable());
		assertEquals(false, video.estEmprunte());
		assertEquals(0, video.getNbEmprunts());
		assertEquals(0, video.getStat());
	}
	
	@Test (expected = OperationImpossible.class)
	public void testConstructeurVideoException()  throws OperationImpossible, InvariantBroken {
		Localisation loc = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
		 video = new Video("007", loc, "titre1", "auteur1", "annee1", gg, 0, "mentionlegale1" );
	}
	
	@Test (expected = OperationImpossible.class)
	public void testConstructeurVideoException2()  throws OperationImpossible, InvariantBroken {
		Localisation loc = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
		 video = new Video("007", loc, "titre1", "auteur1", "annee1", gg, 100, null );
	}
	
	@Test (expected = InvariantBroken.class)
	public void testConstructeurVideoException3()  throws OperationImpossible, InvariantBroken {
		Localisation loc = new Localisation("salle1", "rayon1");
        Genre gg = new Genre("genre1");
		 video = new Video("007", loc, "titre1", "auteur1", "annee1", gg, -1, "mentionlegale1");
	}

	@Test
	public void testEmprunter() throws OperationImpossible, InvariantBroken {
		video.metEmpruntable();
		System.out.println("nbEmpruntsTotal = " + video.getStat());
		int nbEmpruntsTotalInitial = video.getStat();
		video.emprunter();
		System.out.println("nbEmpruntsTotal = " + video.getStat());
		assertEquals(video.getStat(), nbEmpruntsTotalInitial + 1);
		assertEquals(true, video.estEmpruntable());
		assertEquals(true, video.estEmprunte());
	}

	@Test
	public void testInvariantVideo() throws InvariantBroken, OperationImpossible {
		assertTrue(video.invariant());
	}

}
