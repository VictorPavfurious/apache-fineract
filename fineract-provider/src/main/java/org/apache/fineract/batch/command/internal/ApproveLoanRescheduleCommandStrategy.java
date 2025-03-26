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
package org.apache.fineract.batch.command.internal;

import static org.apache.fineract.batch.command.CommandStrategyUtils.COMMAND_VALUE_APPROVE;
import static org.apache.fineract.batch.command.CommandStrategyUtils.relativeUrlWithoutVersion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.fineract.batch.command.CommandStrategy;
import org.apache.fineract.batch.domain.BatchRequest;
import org.apache.fineract.batch.domain.BatchResponse;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.portfolio.loanaccount.rescheduleloan.api.RescheduleLoansApiResource;
import org.apache.fineract.portfolio.loanaccount.rescheduleloan.data.request.LoanRescheduleUpdateReq;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApproveLoanRescheduleCommandStrategy implements CommandStrategy {

    private final RescheduleLoansApiResource rescheduleLoansApiResource;
    private final ObjectMapper objectMapper;

    @Override
    public BatchResponse execute(BatchRequest request, final UriInfo uriInfo) {
        final BatchResponse response = new BatchResponse();
        CommandProcessingResult responseBody;

        response.setRequestId(request.getRequestId());
        response.setHeaders(request.getHeaders());

        final List<String> pathParameters = Splitter.on('/').splitToList(relativeUrlWithoutVersion(request));
        final Long scheduleId = Long.parseLong(pathParameters.get(1).substring(0, pathParameters.get(1).indexOf("?")));

        // Calls 'approve' function from 'Loans reschedule Request' to
        // approve a
        // loan
        try {
            LoanRescheduleUpdateReq loanRescheduleUpdateReq = objectMapper.readValue(request.getBody(), LoanRescheduleUpdateReq.class);
            responseBody = rescheduleLoansApiResource.updateLoanRescheduleRequest(scheduleId, COMMAND_VALUE_APPROVE, loanRescheduleUpdateReq);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error deserialize JSON to LoanRescheduleUpdateReq object", e);
        }

        response.setStatusCode(HttpStatus.SC_OK);
        // Sets the body of the response after the successful approval of a
        // Loans reschedule Request
        try {
            response.setBody(objectMapper.writeValueAsString(responseBody));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializing request to JSON", e);
        }

        return response;
    }

}
