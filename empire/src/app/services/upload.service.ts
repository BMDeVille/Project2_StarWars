import { Injectable } from '@angular/core';
import * as AWS from 'aws-sdk/global';
import * as S3 from 'aws-sdk/clients/s3';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
 add: any;

  FOLDER = 'profileimage/';
  constructor(private _userService: UserService) {}



  uploadfile(file) {

    const bucket = new S3(
      {
        accessKeyId: 'AKIAIBVPEWRMOGPPDL6A',
        secretAccessKey: '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',
        region: 'us-east-1'
      }
    );

    const params = {
      Bucket: 'qconlon',
      Key: this.FOLDER + file.name,
      Body: file
    };

  bucket.upload(params, function (err, data) {
      if (err) {
        // console.log('There was an error uploading your file: ', err);
        return false;
      }

      // console.log('Successfully uploaded file.', data);
      return data;
    });
  }



}
