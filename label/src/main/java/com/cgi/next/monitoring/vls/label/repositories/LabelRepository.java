package com.cgi.next.monitoring.vls.label.repositories;

import com.cgi.next.monitoring.vls.core.domain.Label;
import org.springframework.data.repository.CrudRepository;

/**
 * Data access to for persisted labels
 */
public interface LabelRepository extends CrudRepository<Label, Long> {
}
