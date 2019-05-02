package com.sequenceiq.environment.api.environment.repository;

import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.sequenceiq.environment.api.environment.Environment;

@Transactional(TxType.REQUIRED)
public interface EnvironmentRepository extends CrudRepository<Environment, Long> {

    Set<Environment> findAllByNameInAndWorkspaceId(Set<String> names, Long workspaceId);

}
