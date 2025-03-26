package org.apache.fineract.portfolio.loanaccount.rescheduleloan.data.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoanRescheduleCreationReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String graceOnPrincipal;
    private String extraTerms;
    private String graceOnInterest;
    private String emi;
    private String endDate;
    private boolean recalculateInterest;
    private String rescheduleFromDate;
    private String dateFormat;
    private String rescheduleReasonId;
    private String submittedOnDate;
    private String locale;
    private String loanId;

}
