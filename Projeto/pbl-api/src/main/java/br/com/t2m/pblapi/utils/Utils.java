package br.com.t2m.pblapi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateFormat(Date date) {
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyyy");
		return dt1.format(date);

	}

}
