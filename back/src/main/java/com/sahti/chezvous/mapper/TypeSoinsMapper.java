package com.sahti.chezvous.mapper;



import com.sahti.chezvous.dto.TypeSoinsDto;
import com.sahti.chezvous.model.Soins;
import com.sahti.chezvous.model.TypeSoins;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TypeSoinsMapper {


    @Mapping(target = "numberOfPosts", expression = "java(mapSoins(typeSoins.getSoinss()))")
    TypeSoinsDto maptypeSoinsToDto(TypeSoins typeSoins);

    default Integer mapSoins(List<Soins> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "soinss", ignore = true)
    TypeSoins mapDtoToTypeSoins(TypeSoinsDto typeSoinsDto);
}
