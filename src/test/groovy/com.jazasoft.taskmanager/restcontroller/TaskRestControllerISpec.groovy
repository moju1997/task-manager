package com.jazasoft.taskmanager.restcontroller

import com.jazasoft.taskmanager.BaseISpec
import com.jazasoft.taskmanager.utils.ApiUrls
import org.springframework.http.MediaType

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class TaskRestControllerISpec extends BaseISpec {

    def "Get one Task"() {
        expect:
        mvc.perform(get(ApiUrls.ROOT_URL_TASKS + ApiUrls.URL_TASKS_TASK, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$.name', is("Task 1")))
    }

    def "Get All Tasks"() {
        expect:
        mvc.perform(get(ApiUrls.ROOT_URL_TASKS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$[0].name', is("Task 1")))
    }

    def "Create and Delete Task"() {
        given:
        def tasks = [
                name : "Test Task",
                label: [
                        id: 1
                ]
        ]

        when:
        def mvcResult = mvc.perform(post(ApiUrls.ROOT_URL_TASKS)
                .content(mapper.writeValueAsString(tasks))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isCreated())
                .andReturn()

        String locationUri = mvcResult.getResponse().getHeader("Location")
        assert locationUri.contains(ApiUrls.ROOT_URL_TASKS)
        int idx = locationUri.lastIndexOf("/")
        String id = locationUri.substring(idx + 1)

        then:
        1 == 1
        expect: "Check If Labels Were Created"
        mvc.perform(get(ApiUrls.ROOT_URL_TASKS + ApiUrls.URL_TASKS_TASK, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$.name', is(tasks.name)))

        and: "Delete Label"
        mvc.perform(delete(ApiUrls.ROOT_URL_TASKS + ApiUrls.URL_TASKS_TASK, id))
                .andExpect(status().isNoContent())

        and: "Check if Labels Were Created"
        mvc.perform(get(ApiUrls.ROOT_URL_TASKS + ApiUrls.URL_TASKS_TASK, id))
                .andExpect(status().isNotFound())
    }

}
