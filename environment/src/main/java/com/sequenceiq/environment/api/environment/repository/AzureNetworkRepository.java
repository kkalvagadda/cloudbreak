package com.sequenceiq.environment.api.environment.repository;

import javax.transaction.Transactional;

import com.sequenceiq.environment.api.environment.AzureNetwork;

@Transactional(Transactional.TxType.REQUIRED)
public interface AzureNetworkRepository extends BaseNetworkRepository<AzureNetwork> {
}
