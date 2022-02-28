package com.gbm.challenge.gbmchallenge.model.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateAccountResponseDto {

    @JsonAlias("id") private String accountId;
    @JsonAlias("cash") private BigDecimal balance;
    @JsonAlias("issuers") private List<IssuerDto> issuers;

}
