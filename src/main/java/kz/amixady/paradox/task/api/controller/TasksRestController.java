package kz.amixady.paradox.task.api.controller;



import kz.amixady.paradox.auth.CustomUserDetails;
import kz.amixady.paradox.task.api.dto.request.TaskDetailsCreateRequest;
import kz.amixady.paradox.task.api.dto.request.TaskSearchRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;
import kz.amixady.paradox.task.service.TasksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Slf4j
public class TasksRestController {

    private final TasksService tasksService;



    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody TaskDetailsCreateRequest request){
        log.info("Создание задачи пользователем с ID: {}", customUserDetails.getId());

        return ResponseEntity.ok(
                tasksService.
                        createTask(customUserDetails.getId(),request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        log.info("Получение всех задач пользователя с ID: {}", customUserDetails.getId());

        return ResponseEntity.
                ok(tasksService.
                        findAll(customUserDetails.getId()));
    }

    @PostMapping("/search")
    public ResponseEntity<List<TaskResponse>> search(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody TaskSearchRequest request){

        log.info("Поиск задач пользователя с ID: {}", customUserDetails.getId());

        return ResponseEntity.
                ok(tasksService.search(customUserDetails.getId(),request));
    }

}
