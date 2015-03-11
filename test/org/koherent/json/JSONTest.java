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
	public void testBasic() {
		JSON json = new JSON(
				"{\"a\":123,\"b\":\"abc\",\"c\":true,\"d\":[1,2.0],\"e\":{\"x\":999},\"f\":null}");

		JSON a = json.get("a");
		assertEquals(123, a.getInt());
		assertEquals(123L, a.getLong());
		assertEquals(123.0, a.getDouble(), DELTA);

		JSON b = json.get("b");
		assertEquals("abc", b.getString());

		JSON c = json.get("c");
		assertEquals(true, c.getBoolean());

		JSON d = json.get("d");
		assertEquals(1, d.get(0).getInt());
		assertEquals(1L, d.get(0).getLong());
		assertEquals(1.0, d.get(0).getDouble(), DELTA);
		assertEquals(2, d.get(1).getInt());
		assertEquals(2L, d.get(1).getLong());
		assertEquals(2.0, d.get(1).getDouble(), DELTA);

		List<JSON> dList = d.getList();
		assertEquals(1, dList.get(0).getInt());
		assertEquals(1L, dList.get(0).getLong());
		assertEquals(1.0, dList.get(0).getDouble(), DELTA);
		assertEquals(2, dList.get(1).getInt());
		assertEquals(2L, dList.get(1).getLong());
		assertEquals(2.0, dList.get(1).getDouble(), DELTA);

		JSON e = json.get("e");
		assertEquals(999, e.get("x").getInt());
		assertEquals(999L, e.get("x").getLong());
		assertEquals(999.0, e.get("x").getDouble(), DELTA);

		JSON f = json.get("f");
		assertNull(f.getNull());

		try {
			a.getString();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getBoolean();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getList();
			fail();
		} catch (FormatException exception) {
		}
		try {
			a.getNull();
			fail();
		} catch (FormatException exception) {
		}

		try {
			b.getInt();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getLong();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getDouble();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getBoolean();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getList();
			fail();
		} catch (FormatException exception) {
		}
		try {
			b.getNull();
			fail();
		} catch (FormatException exception) {
		}

		try {
			c.getInt();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getLong();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getDouble();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getString();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getList();
			fail();
		} catch (FormatException exception) {
		}
		try {
			c.getNull();
			fail();
		} catch (FormatException exception) {
		}

		try {
			d.getInt();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getLong();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getDouble();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getString();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getBoolean();
			fail();
		} catch (FormatException exception) {
		}
		try {
			d.getNull();
			fail();
		} catch (FormatException exception) {
		}

		try {
			e.getInt();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getLong();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getDouble();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getString();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getBoolean();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getList();
			fail();
		} catch (FormatException exception) {
		}
		try {
			e.getNull();
			fail();
		} catch (FormatException exception) {
		}

		try {
			f.getInt();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getLong();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getDouble();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getString();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getBoolean();
			fail();
		} catch (FormatException exception) {
		}
		try {
			f.getList();
			fail();
		} catch (FormatException exception) {
		}

		JSON none = json.get("xxx").get("yyy").get(999);
		try {
			none.getInt();
			fail();
		} catch (FormatException exception) {
		}
	}
}
