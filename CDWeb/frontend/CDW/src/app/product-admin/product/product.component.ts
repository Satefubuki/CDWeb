import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import { UploadService } from 'src/app/services/upload.service';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  public user: any;
  selectedFiles: FileList;
  currentFile: File;
  ortherFile: File;
  fileInfos: Observable<any>;


  constructor(private userService: UserService, private uploadService: UploadService ) { }

  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();
    this.userService.get().subscribe(res => {
      console.log(res);
      this.user = res;
    });
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    console.log( event.target.files.item(0));
    
  }
  selectFile2(event) {
    this.ortherFile = event.target.files;
  }

  upload() {
    this.currentFile = this.selectedFiles.item(0);
    console.log(this.selectedFiles)
    console.log(this.currentFile);
    
    
    this.uploadService.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
        } else if (event instanceof HttpResponse) {
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }
}
