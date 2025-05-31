package kz.amixady.paradox.task.api.controller;



import kz.amixady.paradox.auth.CustomUserDetails;
import kz.amixady.paradox.task.api.dto.request.UpdateTaskDetailsRequest;
import kz.amixady.paradox.task.api.dto.response.TaskResponse;
import kz.amixady.paradox.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks/{taskId}")
@Slf4j
public class TaskRestController {

    private final TaskService tasksService;

    @GetMapping
    public ResponseEntity<TaskResponse> findTaskById(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("taskId") UUID taskId
    ){
        log.info("Пользователь с ID: {} запрашивает задачу с ID: {}", customUserDetails.getId(), taskId);

        return ResponseEntity.ok(
                tasksService.findTaskById(customUserDetails.getId(),taskId));
    }

    @PutMapping
    public ResponseEntity<TaskResponse> updateTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("taskId") UUID taskId,
            @RequestBody UpdateTaskDetailsRequest request){

        log.info("Пользователь с ID: {} обновляет задачу с ID: {}", customUserDetails.getId(), taskId);

        return ResponseEntity.ok(
                tasksService.updateTaskDetails(customUserDetails.getId(),taskId,request));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteTask(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("taskId") UUID taskId){

        log.info("Пользователь с ID: {} удаляет задачу с ID: {}", customUserDetails.getId(), taskId);

        tasksService.
                deleteTask(customUserDetails.getId(),taskId);
        return ResponseEntity.noContent().build();
    }
}
