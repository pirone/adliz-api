package com.example.easynotes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import br.com.pirone.adliz.util.DateUtils;

public class DateUtilTests {

	@Test
	public void timeStampToDateTest() throws ParseException {
		String timeStampDate = "2020/04/15";
		Date dataFormatada = DateUtils.getFormatedStringAsDate(timeStampDate);
		assertEquals("15/04/2020", dataFormatada);
	}

}
