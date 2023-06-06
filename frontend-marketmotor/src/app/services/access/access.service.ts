import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private storageService: StorageService) { }


  accessAdmin(){
    var decodedData = this.getDecodedTokenData();
    if(decodedData.cargo=='ROLE_ADMINISTRADOR'){
      return true
    }else{
      return false
    }
  }



  accessAsist(){

    var decodedData = this.getDecodedTokenData();
    if(decodedData.cargo=='ROLE_ASISTENTE'){
      return true
    }else{
      return false
    }
  }


  accessProv(){

    var decodedData = this.getDecodedTokenData();

    if(decodedData.cargo=='ROLE_PROVEEDOR'){
      return true
    }else{
      return false
    }
  }

  private getDecodedTokenData(): any{
    var loggintoken=this.storageService.getToken();

    if(loggintoken!=null){
      var decodedata=jwt_decode(loggintoken) as any;
      var decodedToJson= JSON.stringify(decodedata)
      return JSON.parse(decodedToJson);
    }
    return "";
  }


}

