package com.sequenceiq.environment.api.environment.repository;

import javax.transaction.Transactional;

import com.sequenceiq.environment.api.environment.BaseNetwork;

@Transactional(Transactional.TxType.REQUIRED)
public interface BaseNetworkRepository<T extends BaseNetwork> {
}
