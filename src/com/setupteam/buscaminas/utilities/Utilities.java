package com.setupteam.buscaminas.utilities;

/**
 * 
 * @author Andrés Felipe Chaparro Rosas
 * @version 1
 * @date 14/09/2019
 */
public class Utilities {
	
	public static byte[] toBytes(String string) {
		byte[] b = new byte[string.length()];
		for (int i = 0; i < string.length(); i++) {
			b[i] = (byte) string.charAt(i);
		}
		return b;
	}

	public static String bytesToString(byte[] b) {
		String string = "";
		for (int i = 0; i < b.length; i++) {
			string += getCharTo(b[i]);
		}
		return string;
	}

	public static int setBytesTo(byte[] data, byte[] aux, int beginIndex) {
		for (int i = 0; i < aux.length; i++) {
			data[beginIndex + i] = aux[i];
		}
		return aux.length;
	}

	public static char getCharTo(byte b) {
		return (char) (b < 0 ? b + 256 : b);
	}

	public static void fillBytes(byte[] bytes, byte[] auxBytes, int indexInit, int indexEnd) {
		for (int i = indexInit; i < indexEnd; i++) {
			bytes[i] = auxBytes[i - indexInit];
		}
	}

	public static void fillBytes(byte[] bytes, byte[] auxBytes, int indexInit) {
		for (int i = indexInit; i < indexInit + auxBytes.length; i++) {
			bytes[i] = auxBytes[i - indexInit];
		}
	}

	public static byte[] addSizeTo(byte[] currentData, byte[] addData) {
		byte[] data = new byte[currentData.length + addData.length];
		fillBytes(data, currentData, 0);
		fillBytes(data, addData, currentData.length);
		return data;
	}

	public static byte[] getFractionOf(byte[] bytes, int indexInit, int size) {
		byte[] aux = new byte[size];
		for (int i = 0; i < aux.length; i++)
			aux[i] = bytes[i + indexInit];
		return aux;
	}
}
