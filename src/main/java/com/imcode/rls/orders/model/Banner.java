package com.imcode.rls.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner {

  private Long bannerId;
  private String bannerImgUrl;
  private String bannerTitle;
  private String bannerContent;



}
