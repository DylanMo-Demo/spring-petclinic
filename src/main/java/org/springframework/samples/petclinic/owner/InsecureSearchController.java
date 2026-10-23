package org.springframework.samples.petclinic.owner;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Intentionally vulnerable controller used only to test Frogbot SAST scanning (CWE-89:
 * SQL Injection). Do not merge.
 */
@RestController
class InsecureSearchController {

	private final EntityManager entityManager;

	InsecureSearchController(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@GetMapping("/insecure/owners")
	@SuppressWarnings("unchecked")
	List<Object[]> searchOwners(@RequestParam String lastName) {
		// User input is concatenated directly into the SQL statement
		String sql = "SELECT id, first_name, last_name FROM owners WHERE last_name = '" + lastName + "'";
		return entityManager.createNativeQuery(sql).getResultList();
	}

}
