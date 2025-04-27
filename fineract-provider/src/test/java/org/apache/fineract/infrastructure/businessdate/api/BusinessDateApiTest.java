/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.businessdate.api;

import java.time.LocalDate;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.fineract.TestConfiguration;
import org.apache.fineract.command.core.CommandPipeline;
import org.apache.fineract.infrastructure.businessdate.data.BusinessDateResponse;
import org.apache.fineract.infrastructure.businessdate.domain.BusinessDateType;
import org.apache.fineract.infrastructure.businessdate.service.BusinessDateReadPlatformService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestConfiguration.class)
class BusinessDateApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private BusinessDateReadPlatformService readPlatformService;

    @Mock
    private CommandPipeline commandPipeline;

    @InjectMocks
    private BusinessDateApiResource businessDateApiResource;

    private BusinessDateResponse mockResponse;

    @BeforeEach
    void setUp() {
        mockResponse = new BusinessDateResponse();
        mockResponse.setDescription("Transaction date");
        mockResponse.setType(BusinessDateType.BUSINESS_DATE);
        mockResponse.setDate(LocalDate.of(2025, 5, 14));
        mockResponse.setChanges(Map.of(BusinessDateType.BUSINESS_DATE, LocalDate.of(2025, 5, 15)));
    }

    // TODO: After migration to SPRING WEB MVC will add
    // @Test
    // void shouldReturnAllBusinessDates() {
    // when(readPlatformService.findAll()).thenReturn(List.of(mockResponse));
    // HttpHeaders httpHeaders = new HttpHeaders();
    // httpHeaders.setBearerAuth(authentication);
    // httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    //
    // ResponseEntity<String> response = restTemplate.exchange(BASE_URL_BUSINESS_DATE, GET, new
    // HttpEntity<>(httpHeaders), String.class);
    //
    // assertThat(response.getStatusCode()).isEqualTo(200);
    //
    // List<BusinessDateResponse> responseBody = mapper.convertValue(response.getBody(), new TypeReference<>() {});
    // assertThat(responseBody).isNotEmpty();
    // assertThat(responseBody.getFirst().getDescription()).isEqualTo("Transaction date");
    // assertThat(responseBody.getFirst().getType()).isEqualTo(BusinessDateType.BUSINESS_DATE);
    // }
    //
    // @Test
    // void shouldReturnSpecificBusinessDateByType() {
    // String type = "BUSINESS_DATE";
    // when(readPlatformService.findByType(type)).thenReturn(mockResponse);
    //
    // HttpHeaders httpHeaders = new HttpHeaders();
    // httpHeaders.setBearerAuth(authentication);
    // httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    //
    // ResponseEntity<BusinessDateResponse> response = restTemplate.exchange(BASE_URL_BUSINESS_DATE + "/" + type, GET,
    // new HttpEntity<>(httpHeaders), BusinessDateResponse.class);
    //
    // assertThat(response.getStatusCode()).isEqualTo(200);
    //
    // BusinessDateResponse responseBody = response.getBody();
    // assertThat(responseBody).isNotNull();
    // assertThat(responseBody.getType()).isEqualTo(BusinessDateType.BUSINESS_DATE);
    // }
    //
    // @Test
    // void shouldUpdateBusinessDate() {
    // BusinessDateUpdateRequest updateRequest = new BusinessDateUpdateRequest();
    // updateRequest.setDateFormat("yyyy-MM-dd");
    // updateRequest.setType(BusinessDateType.BUSINESS_DATE);
    // updateRequest.setDate("2025-05-14");
    // updateRequest.setLocale("en");
    //
    // BusinessDateResponse updatedResponse = new BusinessDateResponse();
    // updatedResponse.setDescription("Updated transaction date");
    // updatedResponse.setType(BusinessDateType.BUSINESS_DATE);
    // updatedResponse.setDate(LocalDate.of(2025, 5, 16));
    //
    // when(commandPipeline.send(any(BusinessDateCommand.class))).thenReturn(() -> updatedResponse);
    //
    // ResponseEntity<BusinessDateResponse> response = restTemplate.exchange(BASE_URL_BUSINESS_DATE, HttpMethod.POST,
    // new HttpEntity<>(updateRequest), BusinessDateResponse.class);
    //
    // assertThat(response.getStatusCode()).isEqualTo(200);
    //
    // BusinessDateResponse responseBody = response.getBody();
    // Assertions.assertNotNull(responseBody);
    // assertThat(responseBody.getDescription()).isEqualTo("Updated transaction date");
    // assertThat(responseBody.getDate()).isEqualTo(LocalDate.of(2025, 5, 16));
    // }

    // @Test
    // void getBusinessDatesAPIHasPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // List<BusinessDateResponse> response = Mockito.mock(List.class);
    // given(readPlatformService.findAll()).willReturn(response);
    // // given
    // Mockito.doNothing().when(appUser).validateHasReadPermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // // when
    // underTest.getBusinessDates();
    // // then
    // verify(readPlatformService, Mockito.times(1)).findAll();
    // }
    //
    // @Test
    // void getBusinessDatesAPIHasNoPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // // given
    // Mockito.doThrow(NoAuthorizationException.class).when(appUser).validateHasReadPermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // // when
    // assertThatThrownBy(() -> underTest.getBusinessDates()).isInstanceOf(NoAuthorizationException.class);
    // // then
    // verifyNoInteractions(readPlatformService);
    // }
    //
    // @Test
    // void getBusinessDateByTypeAPIHasPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // BusinessDateResponse response = Mockito.mock(BusinessDateResponse.class);
    // given(readPlatformService.findByType("type")).willReturn(response);
    // // given
    // Mockito.doNothing().when(appUser).validateHasReadPermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // // when
    // underTest.getBusinessDate("type");
    // // then
    // verify(readPlatformService, Mockito.times(1)).findByType("type");
    // }
    //
    // @Test
    // void getBusinessDateByTypeAPIHasNoPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // // given
    // Mockito.doThrow(NoAuthorizationException.class).when(appUser).validateHasReadPermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // // when
    // assertThatThrownBy(() -> underTest.getBusinessDate("type")).isInstanceOf(NoAuthorizationException.class);
    // // then
    // verifyNoInteractions(readPlatformService);
    // }
    //
    // @Test
    // void postBusinessDateAPIHasPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // BusinessDateResponse response = Mockito.mock(BusinessDateResponse.class);
    // // given
    // Mockito.doNothing().when(appUser).validateHasUpdatePermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // Supplier<Object> responseSupplier = () -> response;
    // given(commandPipeline.send(Mockito.any())).willReturn(responseSupplier);
    // // when
    // underTest.updateBusinessDate(new BusinessDateRequest());
    // // then
    // verify(commandPipeline, Mockito.times(1)).send(Mockito.any());
    // }
    //
    // @Test
    // void postBusinessDateAPIHasNoPermission() {
    // AppUser appUser = Mockito.mock(AppUser.class);
    // // given
    // Mockito.doThrow(NoAuthorizationException.class).when(appUser).validateHasUpdatePermission("BUSINESS_DATE");
    // given(securityContext.authenticatedUser()).willReturn(appUser);
    // // when
    // assertThatThrownBy(() -> underTest.updateBusinessDate(new
    // BusinessDateRequest())).isInstanceOf(NoAuthorizationException.class);
    // // then
    // verifyNoInteractions(readPlatformService);
    // }
}
