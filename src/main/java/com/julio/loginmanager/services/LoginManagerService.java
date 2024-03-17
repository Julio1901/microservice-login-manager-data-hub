package com.julio.loginmanager.services;

import com.julio.loginmanager.dtos.LoginDto;
import com.julio.loginmanager.models.LoginModel;
import com.julio.loginmanager.repositories.LoginManagerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginManagerService {

    @Autowired
    private LoginManagerRepository loginManagerRepository;

    public ResponseEntity<Object> saveLoginInfo(@Valid LoginDto loginKeeperDto) {
        var loginInfoModel = new LoginModel();
        BeanUtils.copyProperties(loginKeeperDto, loginInfoModel);
        if (loginKeeperDto.payDay() != null && (loginKeeperDto.payDay() < 1 || loginKeeperDto.payDay() > 31)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The payment must be in the range between 1 and 31");
        }
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(loginManagerRepository.save(loginInfoModel));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Unknown error when saving login info. Error: " + e.getMessage());
        }
    }

    public ResponseEntity<Object> getAllLoginsInfo() {
        try{
            List<LoginModel> loginInfoList = loginManagerRepository.findAll();
            //TODO: Implemente this before implement get login info by id
//            if(!loginInfoList.isEmpty()){
//                for(LoginKeeperModel loginInfo: loginInfoList){
//                    UUID id = loginInfo.getLoginId();
//                    loginInfo.add(linkTo(methodOn(LoginKeeperController.class)))
//                }
//            }
            return ResponseEntity.status(HttpStatus.OK).body(loginInfoList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error to get logins info. Error: " + e.getMessage());
        }
    }

        public ResponseEntity<Object> getLoginById(UUID loginId) {
            LoginModel result = loginManagerRepository.findByLoginId(loginId);

            if(result != null){
                return  ResponseEntity.status(HttpStatus.OK).body(result);
            }else {
                return  ResponseEntity.status(HttpStatus.OK).body("Register not found. Check the name of the service and the owner.");
            }

        }

    public  ResponseEntity<Object> updateLoginInfo(LoginDto loginKeeperDto) {
        Optional<LoginModel> result = loginManagerRepository.findById(loginKeeperDto.loginId());
        if(result.isPresent()){
            LoginModel loginKeeper = result.get();
            loginKeeper.setServiceName(loginKeeperDto.serviceName());
            loginKeeper.setPassword(loginKeeperDto.password());
            loginKeeper.setWebSiteLink(loginKeeperDto.webSiteLink());
            loginKeeper.setPayDay(loginKeeperDto.payDay());
            loginKeeper.setOwner(loginKeeperDto.owner());
            loginKeeper.setDescription(loginKeeperDto.description());

            return ResponseEntity.status(HttpStatus.OK).body(loginManagerRepository.save(loginKeeper));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data has not been modified");
        }
    }

    public ResponseEntity<Object> deleteLogin(UUID loginKeeperId) {
        try{
            loginManagerRepository.deleteById(loginKeeperId);
            return ResponseEntity.status(HttpStatus.OK).body("Data deleted successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when deleting data");
        }
    }




}
