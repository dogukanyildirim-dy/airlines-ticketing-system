package com.dogukanyildirim.airlinesticketingsystem.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectMapper extends ModelMapper {

    private static ObjectMapper instance;

    public static ObjectMapper getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ObjectMapper();
        }
        return instance;
    }

    private ObjectMapper() {
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <D, S> List<D> mapAll(final Collection<S> sourceList, Class<D> outCLass) {
        if (Objects.isNull(sourceList)) return null;
        return sourceList.stream()
                .map(source -> map(source, outCLass))
                .collect(Collectors.toList());
    }
}
