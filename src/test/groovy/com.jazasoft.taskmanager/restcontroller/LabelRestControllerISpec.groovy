package com.jazasoft.taskmanager.restcontroller

import com.jazasoft.taskmanager.BaseISpec
import com.jazasoft.taskmanager.utils.ApiUrls
import org.springframework.http.MediaType

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class LabelRestControllerISpec extends BaseISpec {

    def "Get one Label"() {
        expect:
        mvc.perform(get(ApiUrls.ROOT_URL_LABELS + ApiUrls.URL_LABELS_LABEL, 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$.name', is("Label 1")))
    }

    def "Get All Labels"() {
        expect:
        mvc.perform(get(ApiUrls.ROOT_URL_LABELS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$[0].name', is("Label 1")))
    }

    def "Create and Delete Label"() {
        given:
        def labels = [
                name: "Test Label"
        ]

        when:
        def mvcResult = mvc.perform(post(ApiUrls.ROOT_URL_LABELS)
                .content(mapper.writeValueAsString(labels))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isCreated())
                .andReturn()

        String locationUri = mvcResult.getResponse().getHeader("Location")
        assert locationUri.contains(ApiUrls.ROOT_URL_LABELS)
        int idx = locationUri.lastIndexOf("/")
        String id = locationUri.substring(idx + 1)

        then:
        1 == 1
        expect: "Check If Labels Were Created"
        mvc.perform(get(ApiUrls.ROOT_URL_LABELS + ApiUrls.URL_LABELS_LABEL, id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentTypeJson))
                .andExpect(jsonPath('$.name', is(labels.name)))

        and: "Delete Label"
        mvc.perform(delete(ApiUrls.ROOT_URL_LABELS + ApiUrls.URL_LABELS_LABEL, id))
                .andExpect(status().isNoContent())

        and: "Check if Labels Were Created"
        mvc.perform(get(ApiUrls.ROOT_URL_LABELS + ApiUrls.URL_LABELS_LABEL, id))
                .andExpect(status().isNotFound())
    }


}
