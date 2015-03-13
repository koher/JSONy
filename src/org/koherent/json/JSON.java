package org.koherent.json;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	private JSON parent;
	private String name;
	private int index;

	public JSON(String jsonString) throws FormatException {
		try {
			jsonObject = new JSONObject(jsonString);
		} catch (JSONException e) {
			try {
				jsonArray = new JSONArray(jsonString);
			} catch (JSONException e2) {
				throw new FormatException(
						"Given string cannot be parsed as JSON.", e);
			}
		}
	}

	public JSON(InputStream in) throws IOException, FormatException {
		this(toString(in));
	}

	private static String toString(InputStream in) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new BufferedInputStream(in)));
		for (String line; (line = reader.readLine()) != null;) {
			builder.append(line);
		}

		return builder.toString();
	}

	private JSON(JSON parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	private JSON(JSON parent, int index) {
		this.parent = parent;
		this.index = index;
	}

	public JSON get(String name) {
		return new JSON(this, name);
	}

	public JSON get(int index) {
		return new JSON(this, index);
	}

	private JSON getParent() throws FormatException {
		if (parent == null) {
			throw new FormatException();
		}

		return parent;
	}

	private JSONObject getJSONObject() throws FormatException {
		try {
			if (jsonObject == null) {
				if (name == null) {
					jsonObject = getParent().getJSONArray()
							.getJSONObject(index);
				} else {
					jsonObject = getParent().getJSONObject()
							.getJSONObject(name);
				}
			}

			return jsonObject;
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	private JSONArray getJSONArray() throws FormatException {
		try {
			if (jsonArray == null) {
				if (name == null) {
					jsonArray = getParent().getJSONArray().getJSONArray(index);
				} else {
					jsonArray = getParent().getJSONObject().getJSONArray(name);
				}
			}

			return jsonArray;
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public String getString() {
		try {
			return getStringValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public Integer getInt() {
		try {
			return getIntValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public Long getLong() {
		try {
			return getLongValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public Double getDouble() {
		try {
			return getDoubleValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public Boolean getBoolean() {
		try {
			return getBooleanValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public List<JSON> getList() {
		try {
			return getListValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public Map<String, JSON> getMap() {
		try {
			return getMapValue();
		} catch (FormatException e) {
			return null;
		}
	}

	public String getStringValue() throws FormatException {
		try {
			return name != null ? getParent().getJSONObject().getString(name)
					: getParent().getJSONArray().getString(index);
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public int getIntValue() throws FormatException {
		try {
			return name != null ? getParent().getJSONObject().getInt(name)
					: getParent().getJSONArray().getInt(index);
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public long getLongValue() throws FormatException {
		try {
			return name != null ? getParent().getJSONObject().getLong(name)
					: getParent().getJSONArray().getLong(index);
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public double getDoubleValue() throws FormatException {
		try {
			return name != null ? getParent().getJSONObject().getDouble(name)
					: getParent().getJSONArray().getDouble(index);
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public boolean getBooleanValue() throws FormatException {
		try {
			return name != null ? getParent().getJSONObject().getBoolean(name)
					: getParent().getJSONArray().getBoolean(index);
		} catch (JSONException e) {
			throw new FormatException(e);
		}
	}

	public List<JSON> getListValue() throws FormatException {
		int length = getJSONArray().length();
		List<JSON> result = new ArrayList<JSON>(length);
		for (int index = 0; index < length; index++) {
			result.add(new JSON(this, index));
		}

		return Collections.unmodifiableList(result);
	}

	public Map<String, JSON> getMapValue() throws FormatException {
		Map<String, JSON> result = new HashMap<String, JSON>();
		for (Iterator<String> names = getJSONObject().keys(); names.hasNext();) {
			String name = names.next();
			result.put(name, get(name));
		}

		return Collections.unmodifiableMap(result);
	}

	public <T> T getNullValue() throws FormatException {
		if (name != null ? getParent().getJSONObject().isNull(name)
				: getParent().getJSONArray().isNull(index)) {
			return null;
		} else {
			throw new FormatException();
		}
	}

	public static class FormatException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public FormatException() {
		}

		public FormatException(Throwable cause) {
			super(cause);
		}

		public FormatException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
