package org.koherent.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.koherent.json.JSON.FormatException;

public class JSONTest {
	private static final double DELTA = 1.0e-10;

	@Test
	public void testBasic() throws FormatException {
		JSON json = new JSON(
				"{\"a\":123,\"b\":\"abc\",\"c\":true,\"d\":[1,2.0],\"e\":{\"x\":999},\"f\":null}");

		JSON a = json.get("a");
		assertEquals(123, a.getIntValue());
		assertEquals(123L, a.getLongValue());
		assertEquals(123.0, a.getDoubleValue(), DELTA);

		JSON b = json.get("b");
		assertEquals("abc", b.getStringValue());

		JSON c = json.get("c");
		assertEquals(true, c.getBooleanValue());

		JSON d = json.get("d");
		assertEquals(1, d.get(0).getIntValue());
		assertEquals(1L, d.get(0).getLongValue());
		assertEquals(1.0, d.get(0).getDoubleValue(), DELTA);
		assertEquals(2, d.get(1).getIntValue());
		assertEquals(2L, d.get(1).getLongValue());
		assertEquals(2.0, d.get(1).getDoubleValue(), DELTA);

		List<JSON> dList = d.getListValue();
		assertEquals(1, dList.get(0).getIntValue());
		assertEquals(1L, dList.get(0).getLongValue());
		assertEquals(1.0, dList.get(0).getDoubleValue(), DELTA);
		assertEquals(2, dList.get(1).getIntValue());
		assertEquals(2L, dList.get(1).getLongValue());
		assertEquals(2.0, dList.get(1).getDoubleValue(), DELTA);

		JSON e = json.get("e");
		assertEquals(999, e.get("x").getIntValue());
		assertEquals(999L, e.get("x").getLongValue());
		assertEquals(999.0, e.get("x").getDoubleValue(), DELTA);

		JSON f = json.get("f");
		assertNull(f.getNullValue());

		try {
			a.getStringValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getBooleanValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getListValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getNullValue();
			fail();
		} catch (FormatException exception) {
		}

		try {
			b.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getLongValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getDoubleValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getBooleanValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getListValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getNullValue();
			fail();
		} catch (FormatException exception) {
		}

		try {
			c.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getLongValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getDoubleValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getStringValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getListValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getNullValue();
			fail();
		} catch (FormatException exception) {
		}

		try {
			d.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getLongValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getDoubleValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getStringValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getBooleanValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getNullValue();
			fail();
		} catch (FormatException exception) {
		}

		try {
			e.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getLongValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getDoubleValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getStringValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getBooleanValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getListValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getNullValue();
			fail();
		} catch (FormatException exception) {
		}

		try {
			f.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getLongValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getDoubleValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getStringValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getBooleanValue();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getListValue();
			fail();
		} catch (FormatException exception) {
		}

		JSON none = json.get("xxx").get("yyy").get(999);
		try {
			none.getIntValue();
			fail();
		} catch (FormatException exception) {
		}
	}
}
