
import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

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
  constructor(private fb: FormBuilder) {
    this.createForm();
    this.showImageChange = false;
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
      alert('done!');
      this.loading = false;
    }, 1000);

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
