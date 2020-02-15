package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void userTest() {
		User usr = new User(User.CC,"123","Adolf","G.L.","3693","agl");
		assertEquals(User.CC,usr.getDocumentType(),"F");
		assertEquals("123",usr.getId(),"F");
		assertEquals("Adolf",usr.getNames(),"F");
		assertEquals("G.L.",usr.getLastNames(),"F");
		assertEquals("3693",usr.getPhone(),"F");
		assertEquals("agl",usr.getAddress(),"F");
	}

}
