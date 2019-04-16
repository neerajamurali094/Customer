package com.diviso.graeshoppe.repository.search;

import com.diviso.graeshoppe.domain.State;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the State entity.
 */
public interface StateSearchRepository extends ElasticsearchRepository<State, Long> {
}
