package com.example.mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

public class ConvertFile {
	public static String toString(Blob blob) {
		return Base64.getEncoder().encodeToString(extractBytesFromBlob(blob));
	}
	public static Blob toBlob(String image) throws SerialException, SQLException {
		return bytesToBlob(Base64.getDecoder().decode(image));
	}
	
	private static Blob bytesToBlob(byte[] bytes) throws SerialException, SQLException {
		return new SerialBlob(bytes);
	}

	private static byte[] extractBytesFromBlob(Blob blob) {
		if (blob == null)
			return null;
		try (InputStream inputStream = blob.getBinaryStream()) {
			int blobLength = (int) blob.length();
			byte[] bytes = new byte[blobLength];
			inputStream.read(bytes, 0, blobLength);
			return bytes;
		} catch (SQLException | IOException e) {
			return null;
		}
	}
}
