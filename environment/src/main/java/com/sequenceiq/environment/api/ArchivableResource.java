package com.sequenceiq.environment.api;

public interface ArchivableResource {

    void setDeletionTimestamp(Long timestampMillisecs);

    void setArchived(boolean archived);

    void unsetRelationsToEntitiesToBeDeleted();
}
