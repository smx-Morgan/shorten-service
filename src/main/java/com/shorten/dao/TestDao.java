package com.shorten.dao;

import com.shorten.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<Test, Long> {
}
