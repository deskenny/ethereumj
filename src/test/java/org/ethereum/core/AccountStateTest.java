package org.ethereum.core;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

public class AccountStateTest {

	@Test
	public void testGetEncoded() {
		String expected = "f83e9a01000000000000000000000000000000000000000000000000008080a0c5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470";
		AccountState acct = new AccountState(BigInteger.ZERO, BigInteger.valueOf(2).pow(200));
		assertEquals(expected, Hex.toHexString(acct.getEncoded()));
	}

}
