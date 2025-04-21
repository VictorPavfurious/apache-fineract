package org.apache.fineract.portfolio.client.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.apache.fineract.portfolio.address.data.AddressData;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@FieldNameConstants
@NoArgsConstructor
public class ClientRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String incorpValidityTillDate;
    private String firstname;
    private String dateFormat;
    private Long groupId;
    private String savingsProductId;
    private String incorpNumber;
    private String locale;
    private String familyMembers;
    private String emailAddress;
    private Long officeId;
    private List<ClientDataTableDTO> datatables;
    private String accountNo;
    private Long constitutionId;
    private String submittedOnDate;
    private Long clientClassificationId;
    private AddressData address;
    private Boolean isStaff;
    private Long mainBusinessLineId;
    private Long genderId;
    private String externalId;
    private String middlename;
    private Boolean active;
    private String dateOfBirth;
    private String mobileNo;
    private Long legalFormId;
    private String lastname;
    private Long clientTypeId;
    private String clientNonPersonDetails;
    private String displayname;
    private String fullname;
    private String activationDate;
    private Long staffId;
    private String remarks;
    private String closureDate;
    private String closureReasonId;
    private String reactivationDate;
}
