package com.sahti.chezvous.service;

import com.sahti.chezvous.dto.TypeSoinsDto;
import com.sahti.chezvous.exceptions.SpringRedditException;
import com.sahti.chezvous.mapper.TypeSoinsMapper;
import com.sahti.chezvous.model.TypeSoins;
import com.sahti.chezvous.repository.TypeSoinsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TypeSoinsService {

    private final TypeSoinsRepository typeSoinsRepository;
    private final TypeSoinsMapper typeSoinsMapper;

    @Transactional
    public TypeSoinsDto save(TypeSoinsDto typeSoinsDto){
        TypeSoins typeSoins = typeSoinsRepository.save(typeSoinsMapper.mapDtoToTypeSoins(typeSoinsDto));
        typeSoinsDto.setId(typeSoins.getId());
        return typeSoinsDto;
    }

    @Transactional(readOnly = true)
    public List<TypeSoinsDto> getAll(){
        return typeSoinsRepository.findAll()
                .stream()
                .map(typeSoinsMapper::maptypeSoinsToDto)
                .collect(Collectors.toList());
    }

    public TypeSoinsDto getTypeSoins(Long id){
        TypeSoins typeSoins = typeSoinsRepository.findById(id)
                .orElseThrow(()-> new SpringRedditException("not found"));
        return typeSoinsMapper.maptypeSoinsToDto(typeSoins);
    }

/*    private TypeSoinsDto mapToDto(TypeSoins typeSoins) {
        return TypeSoinsDto.builder().name(typeSoins.getName())
                .id(typeSoins.getId())
                .numberOfPosts(typeSoins.getSoinss().size())
                .build();
    }

    private TypeSoins mapTypeSoinsDto(TypeSoinsDto typeSoinsDto) {
        return TypeSoins.builder().name(typeSoinsDto.getName())
                .description(typeSoinsDto.getDescription())
                .build();
    }*/



}
