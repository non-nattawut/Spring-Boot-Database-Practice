package com.springBootPractice.database.mappers;

public interface Mapper<A,B> {

    A mapToEntity(B b);

    B mapToDto(A a);
}
