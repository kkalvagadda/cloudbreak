package com.sequenceiq.environment.api.environment.repository;

import javax.transaction.Transactional;

import com.sequenceiq.environment.api.environment.AwsNetwork;

@Transactional(Transactional.TxType.REQUIRED)
public interface AwsNetworkRepository extends BaseNetworkRepository<AwsNetwork> {
}
