package org.apache.fineract.portfolio.loanaccount.rescheduleloan.data.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoanRescheduleUpdateReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String approvedOnDate;
    private String rejectedOnDate;
    private String dateFormat;
    private String locale;
}
