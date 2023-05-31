import { Injectable } from '@angular/core';
import { StorageService } from '../storage/storage.service';
import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class ShortinfouserService {

  constructor(private storageService: StorageService) { }



  getNombreCargo() : string{
    var decodedData = this.getDecodedTokenData();
    console.log(decodedData)
    if(decodedData!=null){
      return decodedData.cargo.substring(5)
    }else{
      return "Sin Cargo - Error"
    }
  }


  getAlias() : string{
    var decodedData = this.getDecodedTokenData();
    if(decodedData!=null){
      return decodedData.sub
    }else{
      return "Sin Cargo - Error"
    }
  }

  private getDecodedTokenData(): any{
    var loggintoken=this.storageService.getToken();

    if(loggintoken!=null){
      var decodedata=jwt_decode(loggintoken) as any;
      var decodedToJson= JSON.stringify(decodedata)
      return JSON.parse(decodedToJson);
    }
  }
}
