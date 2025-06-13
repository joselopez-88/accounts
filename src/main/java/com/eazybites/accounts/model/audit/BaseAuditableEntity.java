package com.eazybites.accounts.model.audit;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditableEntity {
    @Column(updatable = false)//Indica que el dato no se guarda en un update, solo en el insert
    @CreatedDate // Anotaciones necesarias para la auditoría
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @CreatedBy // Anotaciones necesarias para la auditoría
    private String createdBy;
    @Column(insertable = false)//Indica que el dato no se guarda en un insert sino en un update
    @LastModifiedDate // Anotaciones necesarias para la auditoría
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    @LastModifiedBy // Anotaciones necesarias para la auditoría
    private String updatedBy;
}
