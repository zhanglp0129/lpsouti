package com.lpsouti.admin.vo.api_record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lpsouti.common.entity.Api;
import com.lpsouti.common.entity.ApiKey;
import com.lpsouti.common.entity.ApiRecord;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRecordQueryVO extends ApiRecord implements Serializable {
    private ApiVO api;
    private ApiKey apiKey;
}

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
class ApiVO extends Api implements Serializable {
    @JsonIgnore
    private String description;
}
