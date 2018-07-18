
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
    // this.CountDownTimer('07/18/2018 11:05 PM', 'countdown');
    this.CountDownTimer('12/20/2019 5:35 PM', 'newcountdown');
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

CountDownTimer(dt, id) {
    let end: any;
    end = new Date(dt);
    let _second: number;
    let _minute: number;
    let _hour: number;
    let _day: number;
    _second = 1000;
    _minute = _second * 60;
    _hour = _minute * 60;
    _day = _hour * 24;
    let timer;

    function showRemaining() {
      let now: any;
      now = new Date();

      let distance: any;
      distance = end - now;
        if (distance < 0) {
            clearInterval(timer);
            document.getElementById(id).innerHTML = 'EXPIRED!';
            return;
        }
        let days: any;
        days = Math.floor(distance / _day);
        let hours: any;
        hours = Math.floor((distance % _day) / _hour);
        let minutes: any;
        minutes = Math.floor((distance % _hour) / _minute);
        let seconds: any;
        seconds = Math.floor((distance % _minute) / _second);

        if (String(hours).length < 2) {
            hours = 0 + String(hours);
        }
        if (String(minutes).length < 2) {
            minutes = 0 + String(minutes);
        }
        if (String(seconds).length < 2) {
            seconds = 0 + String(seconds);
        }


        let datestr: any;
        datestr = days + ' days ' +
                      hours + ' hrs ' +
                      minutes + ' mins ' +
                      seconds + ' secs';

        document.getElementById(id).innerHTML = datestr;
    }

    timer = setInterval(showRemaining, 1000);
}


}
