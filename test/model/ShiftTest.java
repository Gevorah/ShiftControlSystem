package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShiftTest {

	@Test
	void test() {
		Shift s = new Shift("A00");
		assertEquals("A00",s.getCode(),"F");
		assertEquals(Shift.NOT_ATTENDED,s.getStatus(),"F");
		assertEquals(null,s.getUser(),"F");
	}

}
