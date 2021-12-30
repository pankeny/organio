import {Component} from '@angular/core';
import {AuthenticationFormComponent} from "../authentication-form.component";
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['../authentication-form.component.scss']
})
export class LoginFormComponent extends AuthenticationFormComponent {

  constructor(protected fb: FormBuilder,
              protected authService: AuthService,
              protected route: ActivatedRoute,
              protected router: Router) {
    super(fb, authService, route, router);
    this.form = fb.group({
      login: '',
      password: ''
    })
  }

  async onSubmit() {
    const {login, password} = this.form.value
    let loginResponse = await this.authService.authenticate(login, password)

    if (loginResponse.success) {
      await this.router.navigate([this.returnUrl])
    } else {
      this.processErrorResponse(loginResponse.errors)
    }
  }
}
