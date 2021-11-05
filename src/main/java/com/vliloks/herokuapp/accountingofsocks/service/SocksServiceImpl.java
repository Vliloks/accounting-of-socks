package com.vliloks.herokuapp.accountingofsocks.service;

import com.vliloks.herokuapp.accountingofsocks.entity.Socks;
import com.vliloks.herokuapp.accountingofsocks.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {
    @Autowired
    private SocksRepository socksRepository;


    @Override
    @Transactional
    public void saveOrUpdateSocks(Socks userSocks) {
        String color = userSocks.getColor();
        int cottonPart = userSocks.getCottonPart();
        Optional<Socks> optional = socksRepository.findByColorIgnoreCaseAndCottonPart(color, cottonPart);
        if (optional.isPresent()){
            Socks dataSocks = optional.get();
            dataSocks.setQuantity(dataSocks.getQuantity()+userSocks.getQuantity());
            socksRepository.save(dataSocks);
        }else {
            socksRepository.save(userSocks);
        }

    }

    @Override
    @Transactional
    public boolean deleteSocks(Socks userSocks) {
        String color = userSocks.getColor();
        int cottonPart = userSocks.getCottonPart();
        Optional<Socks> optional = socksRepository.findByColorIgnoreCaseAndCottonPart(color, cottonPart);
        if (optional.isPresent() && optional.get().getQuantity()>=userSocks.getQuantity()){
            Socks dataSocks = optional.get();
            dataSocks.setQuantity(dataSocks.getQuantity()-userSocks.getQuantity());
            socksRepository.save(dataSocks);
            return true;
        }else return false;

    }

    @Override
    @Transactional
    public int findTheNumberOfMatchingSocks(String color, String operation, int cottonPart) {
        List<Socks> socksList;
        switch (operation) {
            case "moreThan":
                socksList = socksRepository.findAllByColorIgnoreCaseAndCottonPartIsGreaterThan(color, cottonPart);
                break;
            case "lessThan":
                socksList = socksRepository.findAllByColorIgnoreCaseAndCottonPartIsLessThan(color, cottonPart);
                break;
            case "equal":
                socksList = socksRepository.findAllByColorIgnoreCaseAndCottonPartEquals(color, cottonPart);
                break;
            default:
                throw new HttpMessageNotReadableException("");
        }

        int counter = 0;
        for (Socks socks:socksList) {
            counter += socks.getQuantity();
        }


        return counter;
    }
}

