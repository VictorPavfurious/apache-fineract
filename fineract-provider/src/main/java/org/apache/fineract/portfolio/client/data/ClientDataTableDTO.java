package org.apache.fineract.portfolio.client.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class ClientDataTableDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String registeredTableName;
    private Map<String, String> data;
}
