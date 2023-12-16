import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { SuplierComponent } from './suplier.component';



@NgModule({
  declarations: [],
  imports: [RouterModule.forChild([
    { path: '', component: SuplierComponent }
])
  ]
})
export class SuplierModule { }
