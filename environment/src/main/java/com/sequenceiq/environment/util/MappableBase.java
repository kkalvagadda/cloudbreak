package com.sequenceiq.environment.util;

import java.util.Map;

import com.sequenceiq.environment.api.Mappable;

public abstract class MappableBase implements Mappable {

    @Override
    public Map<String, Object> asMap() {
        return defaultMap();
    }

}
