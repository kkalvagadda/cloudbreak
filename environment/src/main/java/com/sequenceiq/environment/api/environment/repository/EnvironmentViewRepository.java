package com.sequenceiq.environment.api.environment.repository;

import static com.sequenceiq.cloudbreak.authorization.ResourceAction.READ;

import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.sequenceiq.cloudbreak.aspect.workspace.CheckPermissionsByWorkspaceId;
import com.sequenceiq.cloudbreak.aspect.workspace.DisableCheckPermissions;
import com.sequenceiq.environment.api.environment.EnvironmentView;

@Transactional(TxType.REQUIRED)
public interface EnvironmentViewRepository extends CrudRepository<EnvironmentView, Long> {

    @CheckPermissionsByWorkspaceId(action = READ, workspaceIdIndex = 1)
    Set<EnvironmentView> findAllByNameInAndWorkspaceId(Collection<String> names, Long workspaceId);

    @DisableCheckPermissions
    Set<EnvironmentView> findAllByCredentialId(Long credentialId);

    @CheckPermissionsByWorkspaceId(action = READ, workspaceIdIndex = 1)
    Long getIdByNameAndWorkspaceId(String name, Long workspaceId);
}
