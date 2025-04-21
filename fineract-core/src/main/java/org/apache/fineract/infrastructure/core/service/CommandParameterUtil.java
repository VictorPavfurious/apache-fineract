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
package org.apache.fineract.infrastructure.core.service;

import org.apache.commons.lang3.StringUtils;

public final class CommandParameterUtil {

    public static final String INTERMEDIARY_SALE_COMMAND_VALUE = "intermediarySale";
    public static final String SALE_COMMAND_VALUE = "sale";
    public static final String BUY_BACK_COMMAND_VALUE = "buyback";
    public static final String CANCEL_COMMAND_VALUE = "cancel";
    public static final String UPDATE_COMMAND_VALUE = "update";
    public static final String DELETE_COMMAND_VALUE = "delete";
    public static final String CLOSE_COMMAND_VALUE = "close";
    public static final String ACCEPT_TRANSFER_COMMAND_VALUE = "acceptTransfer";
    public static final String REJECT_COMMAND_VALUE = "reject";
    public static final String WITHDRAW_COMMAND_VALUE = "withdraw";
    public static final String ACTIVE_COMMAND_VALUE = "activate";
    public static final String ASSIGN_STAFF_COMMAND_VALUE = "assignStaff";
    public static final String UNASSIGN_STAFF_COMMAND_VALUE = "unassignStaff";
    public static final String PROPOSE_TRANSFER_COMMAND_VALUE = "proposeTransfer";
    public static final String PROPOSE_ACCEPT_TRANSFER_COMMAND_VALUE = "proposeAndAcceptTransfer";
    public static final String WITHDRAW_TRANSFER_COMMAND_VALUE = "withdrawTransfer";
    public static final String REJECT_TRANSFER_COMMAND_VALUE = "rejectTransfer";
    public static final String UPDATE_SAVING_ACC_COMMAND_VALUE = "updateSavingsAccount";
    public static final String REACTIVE_COMMAND_VALUE = "reactivate";
    public static final String UNDO_COMMAND_VALUE = "undoRejection";
    public static final String UNDO_WITHDRAWAL_COMMAND_VALUE = "undoWithdrawal";

    private CommandParameterUtil() {}

    public static boolean is(final String commandParam, final String commandValue) {
        return StringUtils.isNotBlank(commandParam) && commandParam.trim().equalsIgnoreCase(commandValue);
    }

}
