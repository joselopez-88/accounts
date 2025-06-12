package com.eazybites.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
@MappedSuperclass// Esto indica que la clase BaseEntity será una superclase para otras entidades
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BaseEntity {
    @Column(updatable = false)//Indica que el dato no se guarda en un update, solo en el insert
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private LocalDateTime createdBy;
    @Column(insertable = false)//Indica que el dato no se guarda en un insert sino en un update
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    private LocalDateTime updatedBy;
}
