package com.oo2.grupo01.Utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DatabaseUtils {
	private final DataSource dataSource;

	public void truncateAllTables() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");

		jdbcTemplate.execute("TRUNCATE TABLE sensor;");
		System.out.println("Truncated: sensor");
		jdbcTemplate.execute("TRUNCATE TABLE historial;");
		System.out.println("Truncated: historial");
		jdbcTemplate.execute("TRUNCATE TABLE aula;");
		System.out.println("Truncated: aula");
		jdbcTemplate.execute("TRUNCATE TABLE edificio;");
		System.out.println("Truncated: edificio");
		jdbcTemplate.execute("TRUNCATE TABLE estacionamiento;");
		System.out.println("Truncated: estacionamiento");
		jdbcTemplate.execute("TRUNCATE TABLE parking;");
		System.out.println("Truncated: parking");
		jdbcTemplate.execute("TRUNCATE TABLE espacio_verde;");
		System.out.println("Truncated: espacio_verde");
		jdbcTemplate.execute("TRUNCATE TABLE lugar;");
		System.out.println("Truncated: lugar");
		jdbcTemplate.execute("TRUNCATE TABLE user;");
		System.out.println("Truncated: user");

		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}
}
