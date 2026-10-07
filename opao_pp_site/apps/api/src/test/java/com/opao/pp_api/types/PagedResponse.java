package com.opao.pp_api.types;

import java.util.List;

public record PagedResponse<T>(
    List<T> content,
    boolean empty,
    boolean first,
    boolean last,
    int number,
    int numberOfElements,
    int size,
    long totalElements,
    int totalPages
) {}