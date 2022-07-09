package au.nab.productservice.dtos;

import au.nab.productservice.constants.enums.FilterOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilterCondition {
    private final String field;
    private final FilterOperationEnum operator;
    private final Object value;
}
