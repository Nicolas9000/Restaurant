import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MenuService } from '../menu.service';
import { Menu } from 'src/app/interfaces/MenuInterface';

@Component({
  selector: 'app-form-menu',
  templateUrl: './form-menu.component.html',
  styleUrls: ['./form-menu.component.css']
})
export class FormMenuComponent implements OnInit {
  constructor(private fb: FormBuilder, private menuService: MenuService) {}

  menuForm: FormGroup;

  ngOnInit(): void {
      this.menuForm = this.fb.group({
          name: [null, Validators.required],
      });
  }

  onSubmit(): void {
      if (this.menuForm.valid) {
          const menuData: Menu = {
              name: this.menuForm.value.name,
          };
          this.menuService.createMenu(menuData).subscribe({
              next: (menuResponse) => {},
              error: (err) => {},
          });
      }
  }
}
