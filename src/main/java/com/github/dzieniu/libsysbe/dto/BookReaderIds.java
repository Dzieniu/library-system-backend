package com.github.dzieniu.libsysbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReaderIds {

    private long bookId;

    private long readerId;
}
