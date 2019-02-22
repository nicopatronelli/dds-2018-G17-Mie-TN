package commons;

import java.time.temporal.ChronoUnit;

import javax.print.attribute.standard.DialogTypeSelection;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {

	public static Long diasEntreFechas(String fechaDesde, String fechaHasta) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return ChronoUnit.DAYS.between(LocalDate.parse(fechaDesde, formatter), LocalDate.parse(fechaHasta, formatter));
	}
	
}
