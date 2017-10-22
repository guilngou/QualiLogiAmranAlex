package mediatheque.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashClientTest {
	
	HashClient hashClient;
	
	@Before
	public void setUp() throws Exception {
		hashClient = new HashClient("nom1", "prenom1");
	}
	
	@After
	public void tearDown() throws Exception {
		hashClient = null;
	}

	@Test
	public void testConstructeurHashClient() {
		assertTrue("nom1".equals(hashClient.getNom()));
		assertTrue("prenom1".equals(hashClient.getPrenom()));
	}

}
