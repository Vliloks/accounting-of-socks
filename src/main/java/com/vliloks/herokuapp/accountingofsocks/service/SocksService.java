package com.vliloks.herokuapp.accountingofsocks.service;

import com.vliloks.herokuapp.accountingofsocks.entity.Socks;

public interface SocksService {

    void saveOrUpdateSocks(Socks userSocks);

    boolean deleteSocks(Socks userSocks);

    int findTheNumberOfMatchingSocks(String color, String operation, int cottonPart);

}
