package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ShiftTest {

	@Test
	void test() {
		Shift sft = new Shift("A00");
		assertEquals("A00",sft.getCode(),"F 1");
		assertEquals(Shift.NOT_ATTENDED,sft.getStatus(),"F 2");
		assertEquals(null,sft.getUser(),"F 3");
	}

}
