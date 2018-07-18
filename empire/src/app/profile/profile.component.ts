
import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ModalService } from '../services/modal.service';
import { UserinformationComponent } from '../userinformation/userinformation.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  form: FormGroup;
  loading: boolean;
  showImageChange: boolean;



  @ViewChild('fileInput') fileInput: ElementRef;
  constructor(private fb: FormBuilder,  private _modal: ModalService) {
    this.createForm();
    this.showImageChange = false;
  }

  initAboutModal() {
    const inputs = {
      isMobile: false,
    };
    this._modal.init(UserinformationComponent, inputs, {});
  }

  createForm() {
    this.form = this.fb.group({
      // name: ['', Validators.required],
      profile: ['', Validators.required]
    });
  }

  showOption() {
    this.showImageChange = true;
  }

  hideOption() {
    this.showImageChange = false;
  }

  onSubmit() {
    const formModel = this.form.value;
    this.loading = true;
    // In a real-world app you'd have a http request / service call here like
    // this.http.post('apiUrl', formModel)
    setTimeout(() => {
      console.log(formModel);
      alert('Your image has been uploaded.');
      this.loading = false;
    }, 1000);
    this.fileInput.nativeElement.value = '';

  }

  clearFile() {
    this.form.get('profile').setValue(null);
    this.fileInput.nativeElement.value = '';
  }

  ngOnInit() {
  }

  onFileChange(event) {
    const read = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      const file = event.target.files[0];
      read.readAsDataURL(file);
      read.onload = () => {
        this.form.get('profile').setValue({
          filename: file.name,
          filetype: file.type,
          value: read.result.split(',')[1]
        });
      };
    }
  }
}
