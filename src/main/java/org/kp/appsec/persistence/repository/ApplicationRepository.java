package org.kp.appsec.persistence.repository;

import org.kp.appsec.persistence.model.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tonte on 7/6/15.
 */
@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
