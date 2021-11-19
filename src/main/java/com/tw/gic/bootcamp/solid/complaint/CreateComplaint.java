package com.tw.gic.bootcamp.solid.complaint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateComplaint {
    private String complaint = "something";
    private int orderId ;
}
