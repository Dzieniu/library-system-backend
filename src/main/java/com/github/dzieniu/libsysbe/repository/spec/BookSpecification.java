package com.github.dzieniu.libsysbe.repository.spec;

import com.github.dzieniu.libsysbe.exception.NoSuchFieldException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
@NoArgsConstructor
public class BookSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        String key = criteria.getKey();
        String operation = criteria.getOperation();
        String value = criteria.getValue().toString();
        Path<String> objectPath;

        try {
            objectPath = root.get(key);
        } catch (IllegalArgumentException e) {
            throw new NoSuchFieldException("Search field [" + key + "] does not exists");
        }

        switch (operation) {
            case ">":
                return builder.greaterThan(objectPath, value);
            case "<":
                return builder.lessThan(objectPath, value);
            case ":":
                return builder.equal(objectPath, value);
            case "%":
                return builder.like(objectPath, "%" + value + "%");
            default:
                return null;
        }
    }
}