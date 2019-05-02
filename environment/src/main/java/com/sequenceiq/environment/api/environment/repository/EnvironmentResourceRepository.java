package com.sequenceiq.environment.api.environment.repository;

import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sequenceiq.environment.api.environment.EnvironmentView;

@NoRepositoryBean
@Transactional(TxType.REQUIRED)
public interface EnvironmentResourceRepository extends CrudRepository<EnvironmentView, Long> {

    Set<EnvironmentView> findAllByWorkspaceIdAndEnvironments(Long workspaceId, EnvironmentView environment);

    Set<EnvironmentView> findAllByWorkspaceIdAndEnvironmentsIsNull(Long workspaceId);

    Set<EnvironmentView> findAllByWorkspaceIdAndEnvironmentsIsNotNull(Long workspaceId);

    Set<EnvironmentView> findAllByNameInAndWorkspaceId(Collection<String> names, Long workspaceId);

}
