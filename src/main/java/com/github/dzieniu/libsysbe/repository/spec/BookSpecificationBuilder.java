package com.github.dzieniu.libsysbe.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BookSpecificationBuilder<T> {

    private final List<SearchCriteria> params;

    public BookSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public Specification<T> buildSpecification(String searchCriteria) {
        BookSpecificationBuilder builder = new BookSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)([:<>%])(\\w+?),");
        Matcher matcher = pattern.matcher(searchCriteria + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        return builder.build();
    }

    private void with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
    }

    private Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification<T>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new BookSpecification(param));
        }

        Specification<T> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }
}