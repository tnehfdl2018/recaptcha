package com.soobineey.recaptcha.service;


import com.soobineey.recaptcha.dto.MainDto;


public interface MainService {

  public int insertMember(MainDto dto);

  public MainDto selectMember(MainDto dto);

//  public boolean verifyRecaptcha(String response);


}