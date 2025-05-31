package kz.amixady.paradox.task.service.impl;


import kz.amixady.paradox.task.api.dto.request.TaskSearchRequest;
import kz.amixady.paradox.task.persistence.entities.Task;
import kz.amixady.paradox.task.service.TaskFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;


//Класс строит динамический фильтр (Specification) для поиска задач пользователя
// по параметрам: userId, названию, описанию и статусу на уровне бд.
@Component
public class TaskSpecificationBuilder implements TaskFilter {

    @Override
    public Specification<Task> build(UUID userId, TaskSearchRequest request) {
        // Проверяем, что userId не null, иначе выбрасываем исключение

        if (userId == null) {
            throw new IllegalArgumentException("User ID не может быть null");
        }

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Фильтр по userId, чтобы искать задачи конкретного пользователя
            predicates.add(cb.equal(root.get("userId"), userId));

            // Если задан title, фильтруем по названию (регистр не важен)
            if (request.title() != null && !request.title().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("taskDetails").get("title")),
                        "%" + request.title().toLowerCase() + "%"
                ));
            }

            // Если указан статус, фильтруем по статусу задачи
            if (request.description() != null && !request.description().isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("taskDetails").get("description")),
                        "%" + request.description().toLowerCase() + "%"
                ));
            }

            if (request.status() != null) {
                predicates.add(cb.equal(
                        root.get("taskDetails").get("status"),
                        request.status()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
