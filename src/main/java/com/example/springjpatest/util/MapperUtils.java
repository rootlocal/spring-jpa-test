package com.example.springjpatest.util;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperUtils {
    private static final ModelMapper modelMapper;

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Hide from public usage.
     */
    private MapperUtils() {
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param source      list of entities that needs to be mapped
     * @param targetCLass class of result list element
     * @param <T>         target type of objects in result list
     * @param <S>         source type of entity in <code>entityList</code>
     * @return list of mapped object with <code><T></code> type.
     */
    public static <S, T> List<T> mapAll(final @NotNull Collection<S> source, Class<T> targetCLass) {
        return source.stream()
                .map(entity -> map(entity, targetCLass))
                .collect(Collectors.toList());
    }

    public static <S, T> Set<T> mapSet(@NotNull Set<S> source, Class<T> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toSet());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source      object to map from
     * @param destination object to map to
     */
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}
